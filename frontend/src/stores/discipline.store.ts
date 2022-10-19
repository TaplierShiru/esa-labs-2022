import { defineStore } from "pinia";
import { DisciplineDto } from "~/dtos/discipline.dto";
import { BaseService } from "~/services/base.service";
import { ServiceFactory, SERVICE_DISCIPLINE } from "~/services/base.service.factory";

export type DisciplineInitState = {
  elements: DisciplineDto[];
  isLoading: boolean;
};

const disciplineService = ServiceFactory.getService(SERVICE_DISCIPLINE) as BaseService<DisciplineDto>;

export const useDisciplineStore = defineStore({
  id: "disciplineStore",
  state: () =>
      ({
      elements: [],
      isLoading: false
      } as DisciplineInitState),

  actions: {
      async updateElementsArray() {
      this.isLoading = true;

      this.elements = await disciplineService.getAll();

      this.isLoading = false;
      },

      async createNewElement(element: DisciplineDto) {
      if (!element) return;
      this.isLoading = true;
      
      const createdElement = await disciplineService.createEntity(element);
      this.elements.push(createdElement);
      
      this.isLoading = false;
      return createdElement;
      },

      async updateElement(id: string, update: DisciplineDto) {
      if (!id || !update) return;
      this.isLoading = true;      

      const index = await this.findIndexByIdInArray(id);
      if (index === -1) return;
      await disciplineService.updateEntity(id, update);
      this.elements[index] = { ...update, id } as DisciplineDto;
      
      this.isLoading = false;
      },

      async deleteElement(id: string) {
      this.isLoading = true;
      
      const index = await this.findIndexByIdInArray(id);
      if (index === -1) return;
      await disciplineService.deleteEntity(id);
      this.elements.splice(index, 1);
      
      this.isLoading = false;
      },

      async findIndexByIdInArray(id: string) {
      return this.elements.findIndex((element) => +element.id === +id);
      },
  },
});