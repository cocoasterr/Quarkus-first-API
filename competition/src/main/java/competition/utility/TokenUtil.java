package competition.utility;

import competition.model.User;
import io.smallrye.jwt.build.Jwt;

public class TokenUtil {
    public static String generate(User user){
        return Jwt.issuer("http://kawahedukasi/issuer")
                .expiresIn(600L)
                .upn(user.getUsername())
                .groups(user.getPermissions())
                .claim("email", "ananda@email.com")
                .sign();
    }
}
