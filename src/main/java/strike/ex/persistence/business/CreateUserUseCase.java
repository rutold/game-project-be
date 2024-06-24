package strike.ex.persistence.business;
import strike.ex.persistence.domain.CreateUserRequest;
import strike.ex.persistence.domain.CreateUserResponse;
public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
