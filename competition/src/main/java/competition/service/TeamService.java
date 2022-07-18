package competition.service;

import competition.model.Country;
import competition.model.Team;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class TeamService {
    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject params){
        Team team = new Team();

        Country country = Country.findById(params.getLong("countryId"));

        if(country == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        team.setName(params.getString("name"));
        team.setCountry(country);
        team.persist();

        return Response.ok().build();
    }

    public Response getAll(){
        List<Team> teams = Team.findAll().list();
        return Response.ok().entity(teams).build();
    }

    @Transactional
    public Response update(Long id, JsonObject params){
        Team team = Team.findById(id);
        if(team == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Country country = Country.findById(params.getLong("countryId"));

        if(country == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        team.setName(params.getString("name"));
        team.setCountry(country);
        team.persist();

        return Response.ok().entity(team).build();
    }

    @Transactional
    public Response delete(Long id){
        Team team = Team.findById(id);
        if(team == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        team.delete();
        return Response.ok().build();
    }
}
