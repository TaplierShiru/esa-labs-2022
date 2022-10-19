import { defineStore } from "pinia";
import { DisciplineDto } from "~/dtos/discipline.dto";
import { wait } from "~/utils/wait.helpers";
import { DisciplineInitState } from "./discipline.store";
import { FAKE_TEACHERS } from "./teacher.store.fake";


export const useDisciplineFakeStore = defineStore({
  id: "disciplineStore",
  state: () =>
    ({
      elements: [],
      isLoading: false
    } as DisciplineInitState),

  actions: {
    async updateElementsArray() {
      this.isLoading = true;

      this.elements = [
        {id: '0', code: '322-D', name: 'Math', typePass: 'Exam', teacher: FAKE_TEACHERS[0]} as DisciplineDto,
        {id: '1', code: '912-D', name: 'Math', typePass: 'Exam', teacher: null} as DisciplineDto,
        {id: '2', code: '425-D', name: 'Xe',   typePass: 'Test', teacher: null} as DisciplineDto,
        {id: '3', code: '952-D', name: 'Ke',   typePass: 'Test', teacher: FAKE_TEACHERS[2]} as DisciplineDto,
        {id: '4', code: '452-D', name: 'Rss',  typePass: 'End',  teacher: null} as DisciplineDto,
        {id: '5', code: '692-D', name: 'Eng',  typePass: 'Test', teacher: null} as DisciplineDto,
        {id: '6', code: '621-D', name: 'Rus',  typePass: 'Exam', teacher: FAKE_TEACHERS[4]} as DisciplineDto,
        {id: '7', code: '122-D', name: 'Phus', typePass: 'Exam', teacher: null} as DisciplineDto,
      ];

      this.isLoading = false;
    },

    async createNewElement(element: DisciplineDto) {
      if (!element) return;
      this.isLoading = true;
      await wait(3);

      element.id = this.elements.length.toString();
      this.elements.push(element);
      this.isLoading = false;
      return element;
    },

    async updateElement(id: string, update: DisciplineDto) {
      if (!id || !update) return;
      this.isLoading = true;
      await wait(3);
      
      const index = await this.findIndexById(id);
      this.elements[index] = { ...update, id } as DisciplineDto;
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