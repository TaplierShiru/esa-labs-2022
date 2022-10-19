import { BaseDto } from "./base.dto";
import { StudentGroupDto } from "./studentGroup.dto";


export class SpecializationDto extends BaseDto {
    code: string;
    name: string;
    groupSet: StudentGroupDto[] | null = null;
}