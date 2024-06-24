package strike.ex.persistence.configuration.security.auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import strike.ex.persistence.configuration.exceptionhandler.RestCustomExceptionHandler;
import strike.ex.persistence.configuration.security.token.AccessToken;


@Service
public class AuthorizationService {

    public boolean isCurrentUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccessToken accessToken = (AccessToken) authentication.getDetails();
        if (!accessToken.getId().equals(userId)) {
            throw new RestCustomExceptionHandler.UnauthorizedException("You cannot access or change resources of another user");
        }
        return accessToken.getId().equals(userId);
    }
}