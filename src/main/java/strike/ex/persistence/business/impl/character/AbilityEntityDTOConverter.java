package strike.ex.persistence.business.impl.character;

import strike.ex.persistence.domain.AbilityDTO;
import strike.ex.persistence.entity.AbilityEntity;

public class AbilityEntityDTOConverter {
    public static AbilityDTO mapToAbilityDTO(AbilityEntity abilityEntity) {
        return new AbilityDTO(
                abilityEntity.getId(),
                abilityEntity.getName(),
                abilityEntity.getImageUrl(),
                abilityEntity.getAtlasJsonUrl(),
                abilityEntity.getDamage(),
                abilityEntity.getWalkSpeed(),
                abilityEntity.getCooldown(),
                abilityEntity.getProjectilecount(),
                abilityEntity.isMultiHit(),
                abilityEntity.getMultiHitCount()
        );
    }
}
