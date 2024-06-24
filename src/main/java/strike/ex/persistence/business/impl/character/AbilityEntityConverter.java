package strike.ex.persistence.business.impl.character;

import strike.ex.persistence.domain.AbilityDTO;
import strike.ex.persistence.entity.AbilityEntity;
public class AbilityEntityConverter {
    public static AbilityEntity mapToAbilityEntity(AbilityDTO abilityDTO) {
        return new AbilityEntity(
                abilityDTO.getId(),
                abilityDTO.getName(),
                abilityDTO.getImageUrl(),
                abilityDTO.getAtlasJsonUrl(),
                null,
                abilityDTO.getDamage(),
                abilityDTO.getWalkSpeed(),
                abilityDTO.getCooldown(),
                abilityDTO.getProjectileCount(),
                abilityDTO.isMultiHit(),
                abilityDTO.getMultiHitCount()
        );
    }
}
