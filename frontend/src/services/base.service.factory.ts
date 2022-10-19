import { BaseDto } from "~/dtos/base.dto";
import { DisciplineDto } from "~/dtos/discipline.dto";
import { SessionDataDto } from "~/dtos/sessionData.dto";
import { SpecializationDto } from "~/dtos/specialization.dto";
import { StudentDto } from "~/dtos/student.dto";
import { StudentGroupDto } from "~/dtos/studentGroup.dto";
import { TeacherDto } from "~/dtos/teacher.dto";
import { BaseService } from "./base.service";


// Name of the service --> service instance
export const ALL_NAME2SERVICE_OBJ: { [key: string]: BaseService<BaseDto>} = {}


export const ENDPOINT_TEACHER = 'teachers';
export const ENDPOINT_DISCIPLINES = 'disciplines';
export const ENDPOINT_SESSION_DATA = 'session-data';
export const ENDPOINT_SPECIALIZATION = 'specializations';
export const ENDPOINT_STUDENT = 'students';
export const ENDPOINT_STUDENT_GROUP = 'student-groups';


export const SERVICE_TEACHER = 'SERVICE_TEACHER';
ALL_NAME2SERVICE_OBJ[SERVICE_TEACHER] = new BaseService<TeacherDto>(ENDPOINT_TEACHER);

export const SERVICE_DISCIPLINE = 'SERVICE_DISCIPLINE';
ALL_NAME2SERVICE_OBJ[SERVICE_DISCIPLINE] = new BaseService<DisciplineDto>(ENDPOINT_TEACHER);

export const SERVICE_SESSION_DATA = 'SERVICE_SESSION_DATA';
ALL_NAME2SERVICE_OBJ[SERVICE_SESSION_DATA] = new BaseService<SessionDataDto>(ENDPOINT_TEACHER);

export const SERVICE_SPECIALIZATION = 'SERVICE_SPECIALIZATION';
ALL_NAME2SERVICE_OBJ[SERVICE_SPECIALIZATION] = new BaseService<SpecializationDto>(ENDPOINT_TEACHER);

export const SERVICE_STUDENT = 'SERVICE_STUDENT';
ALL_NAME2SERVICE_OBJ[SERVICE_STUDENT] = new BaseService<StudentDto>(ENDPOINT_TEACHER);

export const SERVICE_STUDENT_GROUP = 'SERVICE_STUDENT_GROUP';
ALL_NAME2SERVICE_OBJ[SERVICE_STUDENT_GROUP] = new BaseService<StudentGroupDto>(ENDPOINT_TEACHER);


export class ServiceFactory {

    static getService(serviceName: string): BaseService<BaseDto> {
        const service = ALL_NAME2SERVICE_OBJ[serviceName];
        if (service){
            return service;
        }
        throw Error(`Unknown service name=${serviceName}`);
    }
}