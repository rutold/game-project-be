package strike.ex.persistence.business;
import strike.ex.persistence.domain.UserUpgradeDTO;

import java.util.List;

public interface UserUpgradesGetAllUseCase {
    List<UserUpgradeDTO> getAllUserUpgrades(long id);
}