package strike.ex.persistence.business.impl.upgrades;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.UserUpgradesGetAllUseCase;
import strike.ex.persistence.domain.UserUpgradeDTO;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.ex.persistence.repository.UserUpgradesRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserUpgradesGetAllUseCaseImpl implements UserUpgradesGetAllUseCase {

    private final UserUpgradesRepository userUpgradesRepository;

    @Override
    public List<UserUpgradeDTO> getAllUserUpgrades(long userId) {
        List<UserUpgradesEntity> userUpgradesEntities = userUpgradesRepository.findAllByUser_Id(userId);
        return userUpgradesEntities.stream()
                .map(UpgradeDToConverter::mapToUserUpgradeDTO)
                .collect(Collectors.toList());
    }


}
