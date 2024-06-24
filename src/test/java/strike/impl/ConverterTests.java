package strike.impl;

import org.junit.jupiter.api.Test;
import strike.ex.persistence.business.impl.character.AbilityEntityConverter;
import strike.ex.persistence.business.impl.character.AbilityEntityDTOConverter;
import strike.ex.persistence.business.impl.character.CharacterEntityConverter;
import strike.ex.persistence.business.impl.character.UserCharacterDTOConverter;
import strike.ex.persistence.business.impl.upgrades.UpgradeDToConverter;
import strike.ex.persistence.domain.AbilityDTO;
import strike.ex.persistence.domain.UserCharacterDTO;
import strike.ex.persistence.domain.UserUpgradeDTO;
import strike.ex.persistence.entity.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterTests {

    @Test
    void testMapToAbilityDTO() {
        AbilityEntity abilityEntity = new AbilityEntity();
        abilityEntity.setId(1L);
        abilityEntity.setName("Test Ability");
        abilityEntity.setImageUrl("imageUrl");
        abilityEntity.setAtlasJsonUrl("atlasJsonUrl");
        abilityEntity.setDamage(100);
        abilityEntity.setWalkSpeed(1);
        abilityEntity.setCooldown(10);
        abilityEntity.setProjectilecount(3);
        abilityEntity.setMultiHit(true);
        abilityEntity.setMultiHitCount(2);

        AbilityDTO abilityDTO = AbilityEntityDTOConverter.mapToAbilityDTO(abilityEntity);

        assertEquals(abilityEntity.getId(), abilityDTO.getId());
        assertEquals(abilityEntity.getName(), abilityDTO.getName());
        assertEquals(abilityEntity.getImageUrl(), abilityDTO.getImageUrl());
        assertEquals(abilityEntity.getAtlasJsonUrl(), abilityDTO.getAtlasJsonUrl());
        assertEquals(abilityEntity.getDamage(), abilityDTO.getDamage());
        assertEquals(abilityEntity.getWalkSpeed(), abilityDTO.getWalkSpeed());
        assertEquals(abilityEntity.getCooldown(), abilityDTO.getCooldown());
        assertEquals(abilityEntity.getProjectilecount(), abilityDTO.getProjectileCount());
        assertEquals(abilityEntity.isMultiHit(), abilityDTO.isMultiHit());
        assertEquals(abilityEntity.getMultiHitCount(), abilityDTO.getMultiHitCount());
    }

    @Test
    void testMapToAbilityEntity() {
        AbilityDTO abilityDTO = new AbilityDTO(
                1L,
                "Test Ability",
                "imageUrl",
                "atlasJsonUrl",
                100,
                1,
                10,
                3,
                true,
                2
        );

        AbilityEntity abilityEntity = AbilityEntityConverter.mapToAbilityEntity(abilityDTO);

        assertEquals(abilityDTO.getId(), abilityEntity.getId());
        assertEquals(abilityDTO.getName(), abilityEntity.getName());
        assertEquals(abilityDTO.getImageUrl(), abilityEntity.getImageUrl());
        assertEquals(abilityDTO.getAtlasJsonUrl(), abilityEntity.getAtlasJsonUrl());
        assertEquals(abilityDTO.getDamage(), abilityEntity.getDamage());
        assertEquals(abilityDTO.getWalkSpeed(), abilityEntity.getWalkSpeed());
        assertEquals(abilityDTO.getCooldown(), abilityEntity.getCooldown());
        assertEquals(abilityDTO.getProjectileCount(), abilityEntity.getProjectilecount());
        assertEquals(abilityDTO.isMultiHit(), abilityEntity.isMultiHit());
        assertEquals(abilityDTO.getMultiHitCount(), abilityEntity.getMultiHitCount());
    }

    @Test
    void testMapToCharacterEntity() {
        AbilityDTO abilityDTO = new AbilityDTO(
                1L,
                "Ability",
                "imageUrl",
                "atlasJsonUrl",
                100,
                1,
                10,
                3,
                true,
                2
        );
        List<AbilityDTO> abilities = List.of(abilityDTO);

        UserCharacterDTO userCharacterDTO = new UserCharacterDTO(
                1L,
                1L,
                1L,
                "Character",
                1,
                100,
                abilities,
                1.5,
                10,
                "imageUrl",
                "atlasJsonUrl",
                1000
        );
        CharacterEntity characterEntity = CharacterEntityConverter.mapToCharacterEntity(userCharacterDTO);

        assertEquals(userCharacterDTO.getCharacterId(), characterEntity.getId());
        assertEquals(userCharacterDTO.getName(), characterEntity.getName());
        assertEquals(userCharacterDTO.getWalkSpeed(), characterEntity.getWalkSpeed());
        assertEquals(userCharacterDTO.getHealth(), characterEntity.getHealth());
        assertEquals(userCharacterDTO.getDamageMultiplier(), characterEntity.getDamageMultiplier());
        assertEquals(userCharacterDTO.getCooldownReduction(), characterEntity.getCooldownReduction());
        assertEquals(userCharacterDTO.getImageUrl(), characterEntity.getImageUrl());
        assertEquals(userCharacterDTO.getAtlasJsonUrl(), characterEntity.getAtlasJsonUrl());
        assertEquals(userCharacterDTO.getCost(), characterEntity.getCost());
        assertEquals(userCharacterDTO.getAbilities().size(), characterEntity.getAbilities().size());
    }

    @Test
    void testMapToUserCharacterDTO() {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setId(1L);
        characterEntity.setName("Character");
        characterEntity.setWalkSpeed(1);
        characterEntity.setHealth(100);
        characterEntity.setDamageMultiplier(1.5);
        characterEntity.setCooldownReduction(10);
        characterEntity.setImageUrl("imageUrl");
        characterEntity.setAtlasJsonUrl("atlasJsonUrl");
        characterEntity.setCost(1000);

        AbilityEntity abilityEntity = new AbilityEntity();
        abilityEntity.setId(1L);
        abilityEntity.setName("Ability");
        abilityEntity.setImageUrl("imageUrl");
        abilityEntity.setAtlasJsonUrl("atlasJsonUrl");
        abilityEntity.setDamage(100);
        abilityEntity.setWalkSpeed(1);
        abilityEntity.setCooldown(10);
        abilityEntity.setProjectilecount(3);
        abilityEntity.setMultiHit(true);
        abilityEntity.setMultiHitCount(2);
        abilityEntity.setCharacter(characterEntity);

        characterEntity.setAbilities(List.of(abilityEntity));

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        UserCharactersEntity userCharactersEntity = new UserCharactersEntity();
        userCharactersEntity.setId(1L);
        userCharactersEntity.setUser(userEntity);
        userCharactersEntity.setCharacter(characterEntity);

        UserCharacterDTO userCharacterDTO = UserCharacterDTOConverter.mapToUserCharacterDTO(userCharactersEntity);

        assertEquals(userCharactersEntity.getId(), userCharacterDTO.getId());
        assertEquals(userCharactersEntity.getUser().getId(), userCharacterDTO.getUserId());
        assertEquals(characterEntity.getId(), userCharacterDTO.getCharacterId());
        assertEquals(characterEntity.getName(), userCharacterDTO.getName());
        assertEquals(characterEntity.getWalkSpeed(), userCharacterDTO.getWalkSpeed());
        assertEquals(characterEntity.getHealth(), userCharacterDTO.getHealth());
        assertEquals(characterEntity.getDamageMultiplier(), userCharacterDTO.getDamageMultiplier());
        assertEquals(characterEntity.getCooldownReduction(), userCharacterDTO.getCooldownReduction());
        assertEquals(characterEntity.getImageUrl(), userCharacterDTO.getImageUrl());
        assertEquals(characterEntity.getAtlasJsonUrl(), userCharacterDTO.getAtlasJsonUrl());
        assertEquals(characterEntity.getCost(), userCharacterDTO.getCost());
        assertEquals(characterEntity.getAbilities().size(), userCharacterDTO.getAbilities().size());
    }

    @Test
    void testMapToUserUpgradeDTO() {
        UserUpgradesEntity userUpgradesEntity = new UserUpgradesEntity();
        userUpgradesEntity.setId(1L);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userUpgradesEntity.setUser(userEntity);

        UpgradesEntity upgradesEntity = new UpgradesEntity();
        upgradesEntity.setId(1L);
        upgradesEntity.setName("Upgrade");
        upgradesEntity.setUpgradeMultiplier(1.5);
        upgradesEntity.setNumberOfTiers(3);
        upgradesEntity.setBaseCost(100);
        upgradesEntity.setIncreasePerTier(50);
        upgradesEntity.setImageUrl("imageUrl");
        userUpgradesEntity.setUpgrade(upgradesEntity);
        userUpgradesEntity.setTier(2);

        UserUpgradeDTO userUpgradeDTO = UpgradeDToConverter.mapToUserUpgradeDTO(userUpgradesEntity);

        assertEquals(userUpgradesEntity.getId(), userUpgradeDTO.getId());
        assertEquals(userUpgradesEntity.getUser().getId(), userUpgradeDTO.getUserId());
        assertEquals(upgradesEntity.getId(), userUpgradeDTO.getUpgradeId());
        assertEquals(userUpgradesEntity.getTier(), userUpgradeDTO.getTier());
        assertEquals(upgradesEntity.getName(), userUpgradeDTO.getUpgradeName());
        assertEquals(upgradesEntity.getUpgradeMultiplier(), userUpgradeDTO.getUpgradeMultiplier());
        assertEquals(upgradesEntity.getNumberOfTiers(), userUpgradeDTO.getNumberOfTiers());
        assertEquals(upgradesEntity.getBaseCost(), userUpgradeDTO.getBaseCost());
        assertEquals(upgradesEntity.getIncreasePerTier(), userUpgradeDTO.getIncreasePerTier());
        assertEquals(upgradesEntity.getImageUrl(), userUpgradeDTO.getImageUrl());
    }
}
