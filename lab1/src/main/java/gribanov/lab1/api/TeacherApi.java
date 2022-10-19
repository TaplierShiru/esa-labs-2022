package gribanov.lab1.api;

import gribanov.lab1.models.Teacher;
import gribanov.lab1.repository.TeacherDao;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@WebServlet
@Path("/teachers")
public class TeacherApi {

    @EJB
    private TeacherDao teacherDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachers() {
        List<Teacher> teacherList = this.teacherDao.getAll();
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(teacherList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTeacher(Teacher teacher) {
        this.teacherDao.save(teacher);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{teacherId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(@PathParam("teacherId") String teacherId, Teacher updatedTeacher) {
        Optional<Teacher> optionalTeacher = this.teacherDao.getById(Integer.valueOf(teacherId));
        if (!optionalTeacher.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Teacher with id %s not found", teacherId))
                    .build();
        }
        Teacher teacher = optionalTeacher.get();
        if (!updatedTeacher.getFio().isEmpty()) {
            teacher.setFio(updatedTeacher.getFio());
        }
        teacher.setAge(updatedTeacher.getAge());
        if (!updatedTeacher.getGender().isEmpty()) {
            teacher.setGender(updatedTeacher.getGender());
        }
        this.teacherDao.update(teacher);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{teacherId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(@PathParam("teacherId") String teacherId) {
        Optional<Teacher> optionalTeacher = this.teacherDao.getById(Integer.valueOf(teacherId));
        if (!optionalTeacher.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Teacher with id %s not found", teacherId))
                    .build();
        }
        this.teacherDao.delete(optionalTeacher.get());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
