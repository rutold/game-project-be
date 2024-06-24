package strike.ex.persistence.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserOrUpgrade extends ResponseStatusException {
    public InvalidUserOrUpgrade() {
        super(HttpStatus.BAD_REQUEST, "User or Upgrade not found");
    }
}