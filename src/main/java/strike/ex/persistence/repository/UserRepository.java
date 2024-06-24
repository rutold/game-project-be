package strike.ex.persistence.repository;

import strike.ex.persistence.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> getUserByUsername(String username);
    boolean existsByUsername(String username);

    UserEntity save(UserEntity User);

    void deleteById(long userID);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(long userID);
}
