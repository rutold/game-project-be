package strike.ex.persistence.business;
import strike.ex.persistence.entity.UpgradesEntity;

import java.util.List;

public interface UpgradesGetAllUseCase {
    List<UpgradesEntity> getAllUpgrades();
}
