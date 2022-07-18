package competition.service;

import competition.model.User;
import competition.utility.TokenUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class UserService {

    public Response login(JsonObject request){
        String username = request.getString("username");
        String password = request.getString("password");

        User user = User.find("username = ?1", username).singleResult();
        if(user == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(!user.getPassword().equalsIgnoreCase(Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)))){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        JsonObject result = new JsonObject();
        result.put("token", TokenUtil.generate(user));
        return Response.ok().entity(result).build();
    }

    @Transactional
    public Response register(JsonObject request){
        String username = request.getString("username");
        String password = request.getString("password");
        String name = request.getString("name");
        Set<String> permissions = new HashSet<>(request.getJsonArray("permissions").getList());
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)));
        user.setPermissions(permissions);
        user.persist();

        return Response.ok().build();
    }
}
