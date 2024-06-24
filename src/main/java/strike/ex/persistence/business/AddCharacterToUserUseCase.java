package strike.ex.persistence.business;


public interface AddCharacterToUserUseCase {
    void addCharacterToUser(Long userId, Long characterId);
}

