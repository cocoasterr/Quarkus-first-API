package competition.service;

import competition.model.Country;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class CountryService {

    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject params){
        Country country = new Country();
        country.setName(params.getString("name"));
        country.persist();

        return Response.ok().build();
    }

    public Response getAll(){
        List<Country> countries = Country.findAll().list();
        return Response.ok().entity(countries).build();
    }

    @Transactional
    public Response update(Long id, JsonObject params){
        Country country = Country.findById(id);
        if(country == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        country.setName(params.getString("name"));
        country.persist();

        return Response.ok().entity(country).build();
    }

    @Transactional
    public Response delete(Long id){
        Country country = Country.findById(id);
        if(country == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        country.delete();
        return Response.ok().build();
    }
}