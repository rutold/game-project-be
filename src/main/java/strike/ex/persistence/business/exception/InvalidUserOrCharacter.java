package strike.ex.persistence.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserOrCharacter extends ResponseStatusException {
    public InvalidUserOrCharacter() {
        super(HttpStatus.BAD_REQUEST, "User or Character not found");
    }
}