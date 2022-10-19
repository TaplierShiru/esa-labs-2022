import { defineStore } from "pinia";
import { TeacherDto } from "~/dtos/teacher.dto";
import { wait } from "~/utils/wait.helpers";
import { TeacherInitState } from "./teacher.store";


export const FAKE_TEACHERS = [
  {id: '0', fio: 'Michel Ma', gender: 'M', age: 22} as TeacherDto,
  {id: '1', fio: 'Jear Xe',   gender: 'M', age: 11} as TeacherDto,
  {id: '2', fio: 'Michel Se', gender: 'M', age: 32} as TeacherDto,
  {id: '3', fio: 'Michel Ze', gender: 'W', age: 42} as TeacherDto,
  {id: '4', fio: 'Michel Be', gender: 'E', age: 51} as TeacherDto,
  {id: '5', fio: 'Michel Ke', gender: 'W', age: 23} as TeacherDto,
  {id: '6', fio: 'Michel kk', gender: 'M', age: 46} as TeacherDto,
  {id: '7', fio: 'Michel OO', gender: 'W', age: 28} as TeacherDto,
  {id: '8', fio: 'Michel Pe', gender: 'Q', age: 25} as TeacherDto,
];

export const useTeacherFakeStore = defineStore({
  id: "teacherStore",
  state: () =>
    ({
      elements: [],
      isLoading: false
    } as TeacherInitState),

  actions: {
    async updateElementsArray() {
      this.isLoading = true;

      this.elements = FAKE_TEACHERS;

      this.isLoading = false;
    },

    async createNewElement(element: TeacherDto) {
      if (!element) return;
      this.isLoading = true;
      await wait(3);

      element.id = this.elements.length.toString();
      this.elements.push(element);
      this.isLoading = false;
      return element;
    },

    async updateElement(id: string, update: TeacherDto) {
      if (!id || !update) return;
      this.isLoading = true;
      await wait(3);
      
      const index = await this.findIndexById(id);
      this.elements[index] = { ...update, id } as TeacherDto;
      this.isLoading = false;
    },

    async deleteElement(id: string) {
      this.isLoading = true;
      await wait(3);

      const index = await this.findIndexById(id);
      if (index === -1) return;
      this.elements.splice(index, 1);
      this.isLoading = false;
    },

    async findIndexById(id: string) {
      return this.elements.findIndex((element) => element.id === id);
    },
  },
});