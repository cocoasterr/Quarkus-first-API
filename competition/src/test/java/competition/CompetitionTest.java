package competition;

import competition.controller.CountryController;
import competition.controller.TeamController;
import competition.model.Country;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompetitionTest {
    @Inject
    CountryController countryController;

    @Inject
    TeamController teamController;

    @Test
    @Order(1)
    public void addCountry(){
        JsonObject request = new JsonObject();
        request.put("name", "Indonesia");

        Response response = countryController.add(request);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void getAllCountry(){
        Response response = countryController.getAll();
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assertions.assertNotNull(response.getEntity());

        List<Country> entity = (List<Country>)response.getEntity();
        Assertions.assertEquals(1, entity.size());
        Assertions.assertEquals(1L, entity.get(0).getId());
    }

    @Test
    @Order(3)
    public void addTeam(){
        JsonObject request = new JsonObject();
        request.put("name", "Persib");
        request.put("countryId", 1L);

        Response response = teamController.add(request);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        request = new JsonObject();
        request.put("name", "Arema");
        request.put("countryId", 1L);

        response = teamController.add(request);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
