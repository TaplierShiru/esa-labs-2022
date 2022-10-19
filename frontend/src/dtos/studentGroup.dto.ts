import { BaseDto } from "./base.dto";
import { SpecializationDto } from "./specialization.dto";
import { StudentDto } from "./student.dto";


export class StudentGroupDto extends BaseDto {
    code: string;
    course: number;
    studentSet: StudentDto[] | null = null;
    specialization: SpecializationDto | null = null;
}