import { defineStore } from "pinia";
import { TeacherDto } from "~/dtos/teacher.dto";
import { BaseService } from "~/services/base.service";
import { ServiceFactory, SERVICE_TEACHER } from "~/services/base.service.factory";

export type TeacherInitState = {
  elements: TeacherDto[];
  isLoading: boolean;
};


const teacherService = ServiceFactory.getService(SERVICE_TEACHER) as BaseService<TeacherDto>;

export const useTeacherStore = defineStore({
  id: "teacherStore",
  state: () =>
    ({
      elements: [],
      isLoading: false
    } as TeacherInitState),

  actions: {
    async updateElementsArray() {
      this.isLoading = true;

      this.elements = await teacherService.getAll();

      this.isLoading = false;
    },

    async createNewElement(element: TeacherDto) {
      if (!element) return;
      this.isLoading = true;
      
      const createdElement = await teacherService.createEntity(element);
      this.elements.push(createdElement);
      
      this.isLoading = false;
      return createdElement;
    },

    async updateElement(id: string, update: TeacherDto) {
      if (!id || !update) return;
      this.isLoading = true;      

      const index = await this.findIndexByIdInArray(id);
      if (index === -1) return;
      await teacherService.updateEntity(id, update);
      this.elements[index] = { ...update, id } as TeacherDto;
      
      this.isLoading = false;
    },

    async deleteElement(id: string) {
      this.isLoading = true;
      
      const index = await this.findIndexByIdInArray(id);
      if (index === -1) return;
      await teacherService.deleteEntity(id);
      this.elements.splice(index, 1);
      
      this.isLoading = false;
    },

    async findIndexByIdInArray(id: string) {
      return this.elements.findIndex((element) => +element.id === +id);
    },
  },
});