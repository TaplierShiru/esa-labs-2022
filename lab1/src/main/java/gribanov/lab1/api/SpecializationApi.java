package gribanov.lab1.api;

import gribanov.lab1.models.Specialization;
import gribanov.lab1.repository.SpecializationDao;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@WebServlet
@Path("/specializations")
public class SpecializationApi {

    @EJB
    private SpecializationDao specializationDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecializations() {
        List<Specialization> specializationList = this.specializationDao.getAll();
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(specializationList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSpecialization(Specialization specialization) {
        this.specializationDao.save(specialization);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{specializationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSpecialization(@PathParam("specializationId") String specializationId, Specialization updatedSpecialization) {
        Optional<Specialization> optionalSpecialization = this.specializationDao.getById(Integer.valueOf(specializationId));
        if (!optionalSpecialization.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Specialization with id %s not found", specializationId))
                    .build();
        }
        Specialization specialization = optionalSpecialization.get();
        if (!updatedSpecialization.getCode().isEmpty()) {
            specialization.setCode(updatedSpecialization.getCode());
        }
        if (!updatedSpecialization.getName().isEmpty()) {
            specialization.setName(updatedSpecialization.getName());
        }
        this.specializationDao.update(specialization);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{specializationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSpecialization(@PathParam("specializationId") String specializationId) {
        Optional<Specialization> optionalSpecialization = this.specializationDao.getById(Integer.valueOf(specializationId));
        if (!optionalSpecialization.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Specialization with id %s not found", specializationId))
                    .build();
        }
        this.specializationDao.delete(optionalSpecialization.get());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
