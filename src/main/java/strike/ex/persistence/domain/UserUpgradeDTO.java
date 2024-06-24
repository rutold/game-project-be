package strike.ex.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpgradeDTO {
    private Long id;
    private Long userId;
    private Long upgradeId;
    private int tier;

    private String upgradeName;
    private double upgradeMultiplier;
    private int numberOfTiers;
    private int baseCost;
    private double increasePerTier;
    private String imageUrl;
}
