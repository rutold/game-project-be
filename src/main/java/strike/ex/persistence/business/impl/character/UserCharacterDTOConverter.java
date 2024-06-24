package strike.ex.persistence.business.impl.character;

import strike.ex.persistence.domain.AbilityDTO;
import strike.ex.persistence.domain.UserCharacterDTO;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.entity.UserCharactersEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserCharacterDTOConverter
{
    public static UserCharacterDTO mapToUserCharacterDTO(UserCharactersEntity userCharactersEntity) {
        CharacterEntity characterEntity = userCharactersEntity.getCharacter();
        List<AbilityDTO> abilities = characterEntity.getAbilities().stream()
                .map(AbilityEntityDTOConverter::mapToAbilityDTO)
                .collect(Collectors.toList());
        return new UserCharacterDTO(
                userCharactersEntity.getId(),
                userCharactersEntity.getUser().getId(),
                characterEntity.getId(),
                characterEntity.getName(),
                characterEntity.getWalkSpeed(),
                characterEntity.getHealth(),
                abilities,
                characterEntity.getDamageMultiplier(),
                characterEntity.getCooldownReduction(),
                characterEntity.getImageUrl(),
                characterEntity.getAtlasJsonUrl(),
                characterEntity.getCost()
        );
    }
}
