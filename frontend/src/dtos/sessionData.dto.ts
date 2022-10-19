import { BaseDto } from "./base.dto";
import { DisciplineDto } from "./discipline.dto";
import { StudentDto } from "./student.dto";


export class SessionDataDto extends BaseDto {
    mark: number;
    student: StudentDto | null = null;
    discipline: DisciplineDto | null = null;
}