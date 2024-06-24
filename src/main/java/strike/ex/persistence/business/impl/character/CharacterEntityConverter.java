package strike.ex.persistence.business.impl.character;

import strike.ex.persistence.domain.UserCharacterDTO;
import strike.ex.persistence.entity.AbilityEntity;
import strike.ex.persistence.entity.CharacterEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CharacterEntityConverter {
    public static CharacterEntity mapToCharacterEntity(UserCharacterDTO userCharacterDTO) {
        List<AbilityEntity> abilities = userCharacterDTO.getAbilities().stream()
                .map(AbilityEntityConverter::mapToAbilityEntity)
                .collect(Collectors.toList());

        CharacterEntity characterEntity = new CharacterEntity(
                userCharacterDTO.getCharacterId(),
                userCharacterDTO.getName(),
                userCharacterDTO.getWalkSpeed(),
                userCharacterDTO.getHealth(),
                abilities,
                userCharacterDTO.getDamageMultiplier(),
                userCharacterDTO.getCooldownReduction(),
                userCharacterDTO.getImageUrl(),
                userCharacterDTO.getAtlasJsonUrl(),
                userCharacterDTO.getCost()
        );

        abilities.forEach(ability -> ability.setCharacter(characterEntity));

        return characterEntity;
    }
}
