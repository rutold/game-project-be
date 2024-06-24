package strike.ex.persistence.business.impl.ability;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.AbilityGetAllUseCase;
import strike.ex.persistence.entity.AbilityEntity;
import strike.ex.persistence.repository.AbilityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AbilitiesGetAllUseCaseImpl implements AbilityGetAllUseCase {

    private final AbilityRepository gameDataRepository;

    @Override
    public List<AbilityEntity> getAllAbilities() {
        return gameDataRepository.findAll();
    }
}



