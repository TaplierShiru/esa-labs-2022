package gribanov.lab1.api;

import gribanov.lab1.models.StudentGroup;
import gribanov.lab1.repository.StudentGroupDao;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@WebServlet
@Path("/student-groups")
public class StudentGroupApi {
    @EJB
    private StudentGroupDao studentGroupDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentGroupGroups() {
        List<StudentGroup> studentList = this.studentGroupDao.getAll();
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(studentList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudentGroup(StudentGroup studentGroup) {
        this.studentGroupDao.save(studentGroup);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{studentGroupId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudentGroup(@PathParam("studentGroupId") String studentGroupId, StudentGroup updatedStudentGroup) {
        Optional<StudentGroup> optionalStudentGroup = this.studentGroupDao.getById(Integer.valueOf(studentGroupId));
        if (!optionalStudentGroup.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("StudentGroup with id %s not found", studentGroupId))
                    .build();
        }
        StudentGroup studentGroup = optionalStudentGroup.get();
        if (!updatedStudentGroup.getCode().isEmpty()) {
            studentGroup.setCode(updatedStudentGroup.getCode());
        }
        if (!updatedStudentGroup.getCode().isEmpty()) {
            studentGroup.setCourse(updatedStudentGroup.getCourse());
        }
        this.studentGroupDao.update(studentGroup);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{studentGroupId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudentGroup(@PathParam("studentGroupId") String studentGroupId) {
        Optional<StudentGroup> optionalStudentGroup = this.studentGroupDao.getById(Integer.valueOf(studentGroupId));
        if (!optionalStudentGroup.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("StudentGroup with id %s not found", studentGroupId))
                    .build();
        }
        this.studentGroupDao.delete(optionalStudentGroup.get());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
