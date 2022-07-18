package competition.service;

import competition.model.Tournament;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class TournamentService {

    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject params){
        Tournament tournament = new Tournament();
        tournament.setName(params.getString("name"));
        tournament.persist();

        return Response.ok().build();
    }

    public Response getAll(){
        List<Tournament> countries = Tournament.findAll().list();
        return Response.ok().entity(countries).build();
    }

    @Transactional
    public Response update(Long id, JsonObject params){
        Tournament tournament = Tournament.findById(id);
        if(tournament == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        tournament.setName(params.getString("name"));
        tournament.persist();

        return Response.ok().entity(tournament).build();
    }

    @Transactional
    public Response delete(Long id){
        Tournament tournament = Tournament.findById(id);
        if(tournament == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        tournament.delete();
        return Response.ok().build();
    }
    
}
