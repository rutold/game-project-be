package strike.ex.persistence.business.impl.upgrades;

import strike.ex.persistence.domain.UserUpgradeDTO;
import strike.ex.persistence.entity.UpgradesEntity;
import strike.ex.persistence.entity.UserUpgradesEntity;

public class UpgradeDToConverter {
    public static UserUpgradeDTO mapToUserUpgradeDTO(UserUpgradesEntity userUpgradesEntity) {
        UpgradesEntity upgradesEntity = userUpgradesEntity.getUpgrade();
        return new UserUpgradeDTO(
                userUpgradesEntity.getId(),
                userUpgradesEntity.getUser().getId(),
                upgradesEntity.getId(),
                userUpgradesEntity.getTier(),
                upgradesEntity.getName(),
                upgradesEntity.getUpgradeMultiplier(),
                upgradesEntity.getNumberOfTiers(),
                upgradesEntity.getBaseCost(),
                upgradesEntity.getIncreasePerTier(),
                upgradesEntity.getImageUrl()

        );
    }
}
