import { BaseDto } from "./base.dto";
import { SessionDataDto } from "./sessionData.dto";
import { StudentGroupDto } from "./studentGroup.dto";

export class StudentDto extends BaseDto {
    fio: string;
    numberRecordBook: string;
    sessionSet: SessionDataDto[] | null = null;
    studentGroup: StudentGroupDto;
}