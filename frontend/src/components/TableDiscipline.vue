<script setup lang="ts">
import { Ref, ref } from '@vue/reactivity';
import { onMounted } from '@vue/runtime-core';
import { ElMessageBox, FormInstance } from 'element-plus';
import { storeToRefs } from 'pinia';
import { TeacherDto } from '~/dtos/teacher.dto';
import { EditModeEnum } from '~/utils/editMode.enum';
import { useDisciplineFakeStore as useDisciplineStore} from '~/stores/discipline.store.fake'
import { DisciplineDto } from '~/dtos/discipline.dto';
import { useTeacherFakeStore as useTeacherStore } from '~/stores/teacher.store.fake';

const editMode = ref<EditModeEnum>(EditModeEnum.Update);
const dialogVisible = ref<boolean>(false);
const disciplineStore = useDisciplineStore();
const teacherStore = useTeacherStore();
const handleClose = (done: () => void) => {
  ElMessageBox.confirm('Are you sure to close this dialog?', 'Warning', 
  { confirmButtonText: 'OK', cancelButtonText: 'Cancel', type: 'warning' })
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
};
const form = ref({
  id: undefined,
  code: '',
  name: '',
  typePass: '',
  teacherId: null
});
const formRef = ref<FormInstance>();
const {elements, isLoading} = storeToRefs(disciplineStore);
const teachers: Ref<TeacherDto[]> = ref([]);

onMounted(async () => {
  await disciplineStore.updateElementsArray();
  elements.value = disciplineStore.elements;
  isLoading.value = disciplineStore.isLoading; 
});

function editRow(index: number) {
  editMode.value = EditModeEnum.Update;
  dialogVisible.value = true;
  teacherStore.updateElementsArray()
  teachers.value = [...teacherStore.elements];
  const { teacher, ...partElement }  = elements.value[index];
  form.value = {...partElement, teacherId: teacher?.id ?? null};
}

function createRow() {
  form.value = {
    id: undefined,
    code: '',
    name: '',
    typePass: '',
    teacherId: null
  };
  editMode.value = EditModeEnum.Create;
  dialogVisible.value = true;
}

async function submitRow() {
  console.log(form.value);
  const { teacherId, ...partElement } = {...form.value};
  const element: DisciplineDto = {
    ...partElement, 
    teacher: teacherId ? teachers.value.find(value => value.id === teacherId)! : null
  };
  if (editMode.value === EditModeEnum.Create) {
    // Create new
    await disciplineStore.createNewElement(element);
  } else {
    await disciplineStore.updateElement(element.id, element);
    const pos = elements.value.findIndex((elem) => elem.id === element.id);
    elements.value[pos] = element;
  }
  formRef.value?.resetFields();
  
  dialogVisible.value = false;
}

async function deleteRow(index: number) {
  await disciplineStore.deleteElement(elements.value[index].id);
}


</script>
<template lang="">
  <div class="d-flex">
    <el-row justify="space-between" align="middle">
      <h2>Discipline table</h2>
      <el-button type="primary" @click="createRow()" :loading="isLoading">Add</el-button>
    </el-row>
    <el-table :data="elements">
      <el-table-column prop="code" label="Code" width="300"/>
      <el-table-column prop="name" label="Name" width="100"/>
      <el-table-column prop="typePass" label="Type pass" width="100"/>
      <el-table-column prop="actions" label="Actions" width="400" header-align="center">
        <template #default="scope">
          <div class="d-flex">
            <el-button @click.prevent="editRow(scope.$index)" :loading="isLoading">Edit</el-button>
            <el-button class="delete-button" @click.prevent="deleteRow(scope.$index)" :loading="isLoading">Remove</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

  <el-dialog
    v-model="dialogVisible"
    title="Discipline dialog"
    width="30%"
    :before-close="handleClose"
  >
    <el-form :model="form" :ref="formRef">
      <el-form-item label="Code">
        <el-input v-model="form.code" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Name">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Type pass">
        <el-input v-model="form.typePass" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Teacher">
        <el-select v-model="form.teacherId" clearable placeholder="Select teacher">
          <el-option
            v-for="item in teachers"
            :key="item.id"
            :label="item.fio"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false" :loading="isLoading">Cancel</el-button>
        <el-button type="primary" @click="submitRow()" :loading="isLoading"
          >Save</el-button
        >
      </span>
    </template>
  </el-dialog>
  </div>
</template>

<style scoped>
.delete-button:hover {
  color: rgb(129, 17, 17);
  background-color: rgb(214, 135, 135);
}
</style>