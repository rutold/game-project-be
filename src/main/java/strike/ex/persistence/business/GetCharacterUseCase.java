package strike.ex.persistence.business;


import strike.ex.persistence.entity.CharacterEntity;


public interface GetCharacterUseCase {
     CharacterEntity getCharactersWithAbilities(String name);
}

