package gribanov.lab1.api;

import gribanov.lab1.models.SessionData;
import gribanov.lab1.repository.SessionDataDao;
import gribanov.lab1.repository.SessionDataDao;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@WebServlet
@Path("/session-data")
public class SessionDataApi {

    @EJB
    private SessionDataDao sessionDataDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionData() {
        List<SessionData> sessionDataList = this.sessionDataDao.getAll();
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(sessionDataList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSessionData(SessionData sessionData) {
        this.sessionDataDao.save(sessionData);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PUT
    @Path("/{sessionDataId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSessionData(@PathParam("sessionDataId") String sessionDataId, SessionData updatedSessionData) {
        Optional<SessionData> optionalSessionData = this.sessionDataDao.getById(Integer.valueOf(sessionDataId));
        if (!optionalSessionData.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("SessionData with id %s not found", sessionDataId))
                    .build();
        }
        SessionData sessionData = optionalSessionData.get();
        sessionData.setMark(updatedSessionData.getMark());
        this.sessionDataDao.update(sessionData);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{sessionDataId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSessionData(@PathParam("sessionDataId") String sessionDataId) {
        Optional<SessionData> optionalSessionData = this.sessionDataDao.getById(Integer.valueOf(sessionDataId));
        if (!optionalSessionData.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("SessionData with id %s not found", sessionDataId))
                    .build();
        }
        this.sessionDataDao.delete(optionalSessionData.get());
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
