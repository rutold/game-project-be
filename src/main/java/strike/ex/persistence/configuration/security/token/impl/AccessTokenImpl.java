package strike.ex.persistence.configuration.security.token.impl;

import strike.ex.persistence.configuration.security.token.AccessToken;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Set<String> roles;
    private final long id;

    public AccessTokenImpl(String subject,Long id, Collection<String> roles) {
        this.subject = subject;
        this.id = id;
        this.roles = roles != null ? Set.copyOf(roles) : Collections.emptySet();
    }

    @Override
    public boolean hasRole(String roleName) {
        return this.roles.contains(roleName);
    }
    @Override
    public Long getId() {
        return this.id;
    }
}
