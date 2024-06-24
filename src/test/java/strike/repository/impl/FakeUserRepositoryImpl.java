package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.UserRepository;
import strike.repository.IFakeUserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserRepositoryImpl implements IFakeUserRepository {
    private static long NEXT_ID = 1;
    private final List<UserEntity> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    public boolean existsByEmail(String email) {
        return this.savedUsers
                .stream()
                .anyMatch(UserEntity -> UserEntity.getEmail().equals(email));
    }
    public boolean isCredentialsCorrect(String username, String password) {
        return this.savedUsers.stream()
                .anyMatch(userEntity -> userEntity.getUsername().equals(username) &&
                        userEntity.getPassword().equals(password));
    }
    public Optional<UserEntity> getUserByUsername(String username) {
        return this.savedUsers.stream()
                .filter(UserEntity -> UserEntity.getUsername().equals(username))
                .findFirst();
    }
    @Override
    public boolean existsByUsername(String username) {
        return this.savedUsers
                .stream()
                .anyMatch(UserEntity -> UserEntity.getUsername().equals(username));
    }


    @Override
    public UserEntity save(UserEntity user) {
        if (user.getId() == null) {
            user.setId(NEXT_ID);
            NEXT_ID++;
            this.savedUsers.add(user);
        }
        return user;
    }

    @Override
    public void deleteById(long userID) {
        this.savedUsers.removeIf(UserEntity -> UserEntity.getId().equals(userID));
    }

    @Override
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(this.savedUsers);
    }

    @Override
    public Optional<UserEntity> findById(long userID) {
        return this.savedUsers.stream()
                .filter(UserEntity -> UserEntity.getId().equals(userID))
                .findFirst();
    }
}
