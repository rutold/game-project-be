package strike.ex.persistence.business;

import strike.ex.persistence.domain.User;


import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long userID);
}
