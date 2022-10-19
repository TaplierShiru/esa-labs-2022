package gribanov.lab1.api;

import gribanov.lab1.models.Discipline;
import gribanov.lab1.repository.DisciplineDao;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@WebServlet
@Path("/disciplines")
public class DisciplineApi {

    @EJB
    private DisciplineDao disciplineDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscipline() {
        List<Discipline> disciplineDataList = this.disciplineDao.getAll();
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(disciplineDataList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDiscipline(Discipline sessionData) {
        this.disciplineDao.save(sessionData);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{sessionDataId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDiscipline(@PathParam("sessionDataId") String sessionDataId, Discipline updatedDiscipline) {
        Optional<Discipline> optionalDiscipline = this.disciplineDao.getById(Integer.valueOf(sessionDataId));
        if (!optionalDiscipline.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Discipline with id %s not found", sessionDataId))
                    .build();
        }
        Discipline disciplineData = optionalDiscipline.get();
        if (!updatedDiscipline.getCode().isEmpty()) {
            disciplineData.setCode(updatedDiscipline.getCode());
        }
        if (!updatedDiscipline.getName().isEmpty()) {
            disciplineData.setName(updatedDiscipline.getName());
        }if (!updatedDiscipline.getTypePass().isEmpty()) {
            disciplineData.setTypePass(updatedDiscipline.getTypePass());
        }
        this.disciplineDao.update(disciplineData);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{sessionDataId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDiscipline(@PathParam("sessionDataId") String sessionDataId) {
        Optional<Discipline> optionalDiscipline = this.disciplineDao.getById(Integer.valueOf(sessionDataId));
        if (!optionalDiscipline.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Discipline with id %s not found", sessionDataId))
                    .build();
        }
        this.disciplineDao.delete(optionalDiscipline.get());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
