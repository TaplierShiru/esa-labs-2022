package gribanov.lab1.api;

import gribanov.lab1.models.Student;
import gribanov.lab1.repository.StudentDao;
import gribanov.lab1.repository.StudentDao;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@WebServlet
@Path("/students")
public class StudentApi {
    @EJB
    private StudentDao studentDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        List<Student> studentList = this.studentDao.getAll();
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(studentList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) {
        this.studentDao.save(student);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("studentId") String studentId, Student updatedStudent) {
        Optional<Student> optionalStudent = this.studentDao.getById(Integer.valueOf(studentId));
        if (!optionalStudent.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Student with id %s not found", studentId))
                    .build();
        }
        Student student = optionalStudent.get();
        if (!updatedStudent.getFio().isEmpty()) {
            student.setFio(updatedStudent.getFio());
        }
        if (!updatedStudent.getNumberRecordBook().isEmpty()) {
            student.setNumberRecordBook(updatedStudent.getNumberRecordBook());
        }
        this.studentDao.update(student);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("studentId") String studentId) {
        Optional<Student> optionalStudent = this.studentDao.getById(Integer.valueOf(studentId));
        if (!optionalStudent.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Student with id %s not found", studentId))
                    .build();
        }
        this.studentDao.delete(optionalStudent.get());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
