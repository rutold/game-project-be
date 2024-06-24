package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.character.AddCharacterToUserUseCaseImpl;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.entity.UserCharactersEntity;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.CharacterRepository;
import strike.ex.persistence.repository.NewUserRepository;
import strike.ex.persistence.repository.UserCharactersRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
class AddCharacterToUserUseCaseImplTest {

    @Mock
    private NewUserRepository userRepository;

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private UserCharactersRepository userCharactersRepository;

    @InjectMocks
    private AddCharacterToUserUseCaseImpl addCharacterToUserUseCase;

    @Test
    void testAddCharacterToUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> addCharacterToUserUseCase.addCharacterToUser(1L, 2L));
    }

    @Test
    void testAddCharacterToUser_CharacterNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        when(characterRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> addCharacterToUserUseCase.addCharacterToUser(1L, 2L));
    }

    @Test
    void testAddCharacterToUser_CharacterAlreadyOwned() {
        UserEntity user = new UserEntity();
        CharacterEntity character = new CharacterEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(characterRepository.findById(2L)).thenReturn(Optional.of(character));
        when(userCharactersRepository.findByUser_IdAndCharacter_Id(1L, 2L)).thenReturn(Optional.of(new UserCharactersEntity()));

        assertThrows(IllegalArgumentException.class, () -> addCharacterToUserUseCase.addCharacterToUser(1L, 2L));
    }

    @Test
    void testAddCharacterToUser_InsufficientCurrency() {
        UserEntity user = new UserEntity();
        user.setUserCurrency(10);
        CharacterEntity character = new CharacterEntity();
        character.setCost(20);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(characterRepository.findById(2L)).thenReturn(Optional.of(character));
        when(userCharactersRepository.findByUser_IdAndCharacter_Id(1L, 2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> addCharacterToUserUseCase.addCharacterToUser(1L, 2L));
    }

    @Test
    void testAddCharacterToUser_Success() {
        UserEntity user = new UserEntity();
        user.setUserCurrency(20);
        CharacterEntity character = new CharacterEntity();
        character.setCost(10);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(characterRepository.findById(2L)).thenReturn(Optional.of(character));
        when(userCharactersRepository.findByUser_IdAndCharacter_Id(1L, 2L)).thenReturn(Optional.empty());

        addCharacterToUserUseCase.addCharacterToUser(1L, 2L);

        verify(userRepository, times(1)).save(user);
        verify(userCharactersRepository, times(1)).save(any(UserCharactersEntity.class));
    }
}