package strike.ex.persistence.business.impl.upgrades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import strike.ex.persistence.business.AddUpgradeToUserUseCase;
import strike.ex.persistence.entity.UpgradesEntity;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.ex.persistence.repository.NewUserRepository;
import strike.ex.persistence.repository.UpgradesRepository;
import strike.ex.persistence.repository.UserUpgradesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddUpgradeToUserUseCaseImpl implements AddUpgradeToUserUseCase {
    private final NewUserRepository userRepository;
    private final UpgradesRepository upgradesRepository;
    private final UserUpgradesRepository userUpgradesRepository;

    @Override
    @Transactional
    public void addUpgradeToUser(Long userId, Long upgradeId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<UpgradesEntity> upgradeOpt = upgradesRepository.findById(upgradeId);

        if (userOpt.isEmpty() || upgradeOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Upgrade not found");
        }

        UserEntity user = userOpt.get();
        UpgradesEntity upgrade = upgradeOpt.get();

        Optional<UserUpgradesEntity> userUpgradeOpt = userUpgradesRepository.findByUser_IdAndUpgrade_Id(userId, upgradeId);

        int cost;
        if (userUpgradeOpt.isPresent()) {
            // User already has this upgrade, check and upgrade tier
            UserUpgradesEntity userUpgrade = userUpgradeOpt.get();
            int currentTier = userUpgrade.getTier();

            if (currentTier >= upgrade.getNumberOfTiers()) {
                throw new IllegalArgumentException("Upgrade tier is already at maximum level");
            }

            cost = (int) (upgrade.getBaseCost() * Math.pow(upgrade.getIncreasePerTier(), currentTier));
            if (user.getUserCurrency() < cost) {
                throw new IllegalArgumentException("Insufficient currency");
            }

            // Update tier and subtract currency
            userUpgrade.setTier(currentTier + 1);
            user.setUserCurrency(user.getUserCurrency() - cost);
            userUpgradesRepository.save(userUpgrade);

        } else {
            // User does not have this upgrade, add it
            cost = upgrade.getBaseCost();
            if (user.getUserCurrency() < cost) {
                throw new IllegalArgumentException("Insufficient currency");
            }

            UserUpgradesEntity newUserUpgrade = UserUpgradesEntity.builder()
                    .user(user)
                    .upgrade(upgrade)
                    .tier(1)
                    .build();
            user.setUserCurrency(user.getUserCurrency() - cost);
            userUpgradesRepository.save(newUserUpgrade);
        }

        userRepository.save(user);
    }
}
