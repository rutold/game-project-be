package strike.ex.persistence.business;

import strike.ex.persistence.domain.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(String email, UpdateUserRequest request);
}
