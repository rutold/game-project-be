package strike.ex.persistence.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
