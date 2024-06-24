package strike.ex.persistence.configuration.security.token;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Long getId();


    Set<String> getRoles();

    boolean hasRole(String roleName);
}
