import { BaseDto } from "./base.dto";
import { TeacherDto } from "./teacher.dto";


export class DisciplineDto extends BaseDto {
    code: string;
    name: string;
    typePass: string;
    teacher: TeacherDto | null = null;
}