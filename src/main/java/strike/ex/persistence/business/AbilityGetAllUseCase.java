package strike.ex.persistence.business;

import strike.ex.persistence.entity.AbilityEntity;

import java.util.List;

public interface AbilityGetAllUseCase {
    List<AbilityEntity> getAllAbilities();
}

