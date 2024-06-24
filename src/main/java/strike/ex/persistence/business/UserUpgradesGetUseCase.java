package strike.ex.persistence.business;
import strike.ex.persistence.entity.UserUpgradesEntity;


public interface UserUpgradesGetUseCase {
    UserUpgradesEntity getAllUserUpgrades(long userId, long upgradeId);
}