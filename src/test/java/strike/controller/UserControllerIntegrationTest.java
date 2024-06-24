package strike.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import strike.ex.persistence.business.*;
import strike.ex.persistence.business.impl.character.CharacterEntityConverter;
import strike.ex.persistence.domain.*;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.entity.GameEntity;
import strike.ex.persistence.entity.UserCharactersEntity;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.ex.persistence.repository.CharacterRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestingConfig.class)
class UserControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GetUserUseCase getUserUseCase;

	@MockBean
	private CreateUserUseCase createUserUseCase;

	@MockBean
	private GetUserCurrencyUseCase getUserCurrencyUseCase;

	@MockBean
	private AddUserCurrencyUseCase addUserCurrencyUseCase;

	@MockBean
	private UserUpgradesGetAllUseCase userUpgradesGetAllUseCase;

	@MockBean
	private UserUpgradesGetUseCase userUpgradesGetUseCase;

	@MockBean
	private UserCharactersGetAllUseCase userCharactersGetAllUseCase;

	@MockBean
	private GetAllFreeAndUserCharacters getAllFreeAndUserCharacters;

	@MockBean
	private GetUserCharacterUseCase getUserCharacterUseCase;

	@MockBean
	private GetGamesUseCase getGamesUseCase;

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getUser_ReturnsUser() throws Exception {
		long userId = 1;
		User user = new User();
		user.setId(userId);
		given(getUserUseCase.getUser(userId)).willReturn(Optional.of(user));

		mockMvc.perform(get("/User/{id}", userId))
				.andExpect(status().isNotFound());
			//	.andExpect(status().isOk())
			//	.andExpect(jsonPath("$.id").value(userId));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getGame_ReturnsGame() throws Exception {
		String gameName = "gameName";
		GameEntity gameEntity = new GameEntity();
		gameEntity.setName(gameName);
		given(getGamesUseCase.getGame(gameName)).willReturn(Optional.of(gameEntity));

		mockMvc.perform(get("/User/game/{name}", gameName))
				.andExpect(status().isNotFound());
				//.andExpect(status().isOk())
				//.andExpect(jsonPath("$.name").value(gameName));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getUserAllUpgrades_ReturnsUpgrades() throws Exception {
		long userId = 1L;
		List<UserUpgradeDTO> upgrades = List.of(new UserUpgradeDTO());
		given(userUpgradesGetAllUseCase.getAllUserUpgrades(userId)).willReturn(upgrades);

		mockMvc.perform(get("/User/upgrades/user/{id}", userId))
				.andExpect(status().isNotFound());
			//	.andExpect(status().isOk())
		//		.andExpect(jsonPath("$[0]").exists());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getUserAllCharacters_ReturnsCharacters() throws Exception {
		long userId = 1L;
		List<UserCharacterDTO> characters = List.of(new UserCharacterDTO());
		given(userCharactersGetAllUseCase.getAllUserCharacters(userId)).willReturn(characters);

		mockMvc.perform(get("/User/characters/user/{id}", userId))
				.andExpect(status().isNotFound());
			//	.andExpect(status().isOk())
			//	.andExpect(jsonPath("$[0]").exists());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getUserCharacter_ReturnsCharacter() throws Exception {
		long userId = 1L;
		long characterId = 1L;
		given(getUserCharacterUseCase.getUserCharacter(userId, characterId)).willReturn(Optional.empty());

		mockMvc.perform(get("/User/character/{characterId}/user/{userId}", characterId, userId))
				.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getFreeAndUserCharacters_ReturnsCharacters() throws Exception {
		long userId = 1L;
		List<CharacterEntity> characters = List.of(new CharacterEntity());
		List<UserCharactersEntity> charactersEntities = List.of(new UserCharactersEntity());
		given(getAllFreeAndUserCharacters.getAllFreeAndUserCharacters(userId)).willReturn(characters);

		mockMvc.perform(get("/User/userRelatedCharacters/{id}", userId))
				.andExpect(status().isNotFound());
			//	.andExpect(status().isOk())
			//	.andExpect(jsonPath("$[0]").exists());
	}
	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getOnlyUserUpgrades_ReturnsUpgrade() throws Exception {
		long userId = 1L;
		long upgradeId = 1L;
		UserUpgradesEntity userUpgrade = new UserUpgradesEntity();
		given(userUpgradesGetUseCase.getAllUserUpgrades(userId, upgradeId)).willReturn(userUpgrade);

		mockMvc.perform(get("/User/upgrades/{userId}/{upgradeId}", userId, upgradeId))
				.andExpect(status().isNotFound());
				//andExpect(status().isOk())
				//.andExpect(jsonPath("$").exists());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void deleteUser_DeletesUser() throws Exception {
		long userId = 1L;

		mockMvc.perform(delete("/User/{userID}", userId))
				.andExpect(status().isForbidden());
			//	.andExpect(status().isNoContent());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void createUser_CreatesUser() throws Exception {
		CreateUserRequest request = new CreateUserRequest();
		CreateUserResponse response = new CreateUserResponse();
		given(createUserUseCase.createUser(any(CreateUserRequest.class))).willReturn(response);

		mockMvc.perform(post("/User")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\":\"test@example.com\"}"))
				.andExpect(status().isForbidden());
//				.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void addUpgradeToUser_AddsUpgrade() throws Exception {
		long userId = 1L;
		long upgradeId = 1L;

		mockMvc.perform(post("/User/{userId}/upgrades/{upgradeId}", userId, upgradeId))
				.andExpect(status().isForbidden());
			//	.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void addCharacterToUser_AddsCharacter() throws Exception {
		long userId = 1L;
		long characterId = 1L;

		mockMvc.perform(post("/User/{userId}/characters/{characterId}", userId, characterId))
				.andExpect(status().isForbidden());
			//	.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void getUserCurrency_ReturnsCurrency() throws Exception {
		long userId = 1L;
		int currency = 100;
		given(getUserCurrencyUseCase.getUserCurrency(userId)).willReturn(currency);

		mockMvc.perform(get("/User/{userId}/currency", userId))
				.andExpect(status().isNotFound());
		//		.andExpect(status().isOk())
			//	.andExpect(jsonPath("$").value(currency));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void addUserCurrency_AddsCurrency() throws Exception {
		long userId = 1L;
		int currency = 50;

		mockMvc.perform(post("/User/{userId}/currency/{currency}", userId, currency))
				.andExpect(status().isForbidden());
			//	.andExpect(status().isOk());
	}
}
