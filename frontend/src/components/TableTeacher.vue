<script setup lang="ts">
import { ref } from '@vue/reactivity';
import { onMounted } from '@vue/runtime-core';
import { ElMessageBox, FormInstance } from 'element-plus';
import { storeToRefs } from 'pinia';
import { TeacherDto } from '~/dtos/teacher.dto';
import { useTeacherFakeStore as useTeacherStore} from '~/stores/teacher.store.fake';
import { EditModeEnum } from '~/utils/editMode.enum';

const editMode = ref<EditModeEnum>(EditModeEnum.Update);
const dialogVisible = ref<boolean>(false);
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
  fio: '',
  age: null,
  gender: ''
});
const formRef = ref<FormInstance>();
const {elements, isLoading} = storeToRefs(teacherStore);

onMounted(async () => {
  await teacherStore.updateElementsArray();
  elements.value = teacherStore.elements;
  isLoading.value = teacherStore.isLoading; 
});

function editRow(index: number) {
  editMode.value = EditModeEnum.Update;
  dialogVisible.value = true;
  const element: TeacherDto = elements.value[index];
  form.value = {...element};
}

function createRow() {
  form.value = {
    id: undefined,
    fio: '',
    age: null,
    gender: ''
  }
  editMode.value = EditModeEnum.Create;
  dialogVisible.value = true;
}

async function submitRow() {
  console.log(editMode.value);
  const element: TeacherDto = {...form.value};
  if (editMode.value === EditModeEnum.Create) {
    // Create new
    await teacherStore.createNewElement(element);
  } else {
    await teacherStore.updateElement(element.id, element);
    const pos = elements.value.findIndex((elem) => elem.id === element.id);
    elements.value[pos] = element;
  }
  formRef.value?.resetFields();
  
  dialogVisible.value = false;
}

async function deleteRow(index: number) {
  await teacherStore.deleteElement(elements.value[index].id);
}


</script>
<template lang="">
  <div class="d-flex">
    <el-row justify="space-between" align="middle">
      <h2>Teacher table</h2>
      <el-button type="primary" @click="createRow()" :loading="isLoading">Add</el-button>
    </el-row>
    <el-table :data="elements">
      <el-table-column prop="fio" label="Fio" width="300"/>
      <el-table-column prop="age" label="Age" width="100"/>
      <el-table-column prop="gender" label="Gender" width="100"/>
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
    title="Teacher dialog"
    width="30%"
    :before-close="handleClose"
  >
    <el-form :model="form" :ref="formRef">
      <el-form-item label="Fio">
        <el-input v-model="form.fio" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Age">
        <el-input-number v-model="form.age" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Gender">
        <el-input v-model="form.gender" autocomplete="off" />
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