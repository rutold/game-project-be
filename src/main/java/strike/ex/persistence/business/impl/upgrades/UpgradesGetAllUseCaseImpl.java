package strike.ex.persistence.business.impl.upgrades;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.UpgradesGetAllUseCase;
import strike.ex.persistence.entity.UpgradesEntity;
import strike.ex.persistence.repository.UpgradesRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UpgradesGetAllUseCaseImpl implements UpgradesGetAllUseCase {

    private final UpgradesRepository upgradesRepository;

    @Override
    public List<UpgradesEntity> getAllUpgrades() {
        return upgradesRepository.findAll();
    }
}



