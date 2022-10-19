import { BaseDto } from './base.dto';

export class TeacherDto extends BaseDto {
    fio: string;
    age: number;
    gender: string;
}