package competition.controller;

import competition.service.TeamService;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/competition/team")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamController {

    @Inject
    TeamService teamService;

    @POST
    @Operation(summary = "Add Team")
    @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "200", description = "OK",content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response add(JsonObject params){
        return teamService.add(params);
    }

    @GET
    @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "200", description = "OK",content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response getAll(){
        return teamService.getAll();
    }

    @PUT
    @Path("/{id}")
    @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "200", description = "OK",content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response updateCountries(@PathParam("id") Long id, JsonObject params) {
        return teamService.update(id, params);
    }

    @DELETE
    @Path("/{id}")
    @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "200", description = "OK",content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response delete(@PathParam("id") Long id) {
        return teamService.delete(id);
    }
}
