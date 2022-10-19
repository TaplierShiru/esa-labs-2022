import axios from "axios";
import { BaseDto } from "~/dtos/base.dto";
import { BACKEND_URL } from "~/utils/config";


export class BaseService<D extends BaseDto> {

    constructor(protected endpointContest: string) {}

    async getAll(): Promise<D[]> {
        const request = await axios.get(`${BACKEND_URL}/${this.endpointContest}`);
        return request.data as D[];
    }

    async createEntity(entityDto: D): Promise<any> {
        const request = await axios.post(`${BACKEND_URL}/${this.endpointContest}`, entityDto);
        return request.data;
    }

    async updateEntity(id: string, updateEntityDto: D): Promise<D> {
        const request = await axios.put(`${BACKEND_URL}/${this.endpointContest}/${id}`, updateEntityDto);
        return request.data as D;
    }

    async deleteEntity(id: string): Promise<any> {
        const request = await axios.delete(`${BACKEND_URL}/${this.endpointContest}/${id}`);
        return request.data;
    }
}