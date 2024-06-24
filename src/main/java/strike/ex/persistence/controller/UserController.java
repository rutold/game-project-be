package strike.ex.persistence.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import strike.ex.persistence.business.DeleteUserUseCase;
import strike.ex.persistence.domain.*;
import strike.ex.persistence.business.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.entity.UserCharactersEntity;
import strike.ex.persistence.entity.UserUpgradesEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
@AllArgsConstructor
public class UserController {
    private final GetUserUseCase getUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final AddUpgradeToUserUseCase addUpgradeToUserUseCase;
    private final AddCharacterToUserUseCase addCharacterToUserUseCase;
    private final GetUserCurrencyUseCase getUserCurrencyUseCase;
    private  final AddUserCurrencyUseCase addUserCurrencyUseCase;
    private  final UserUpgradesGetAllUseCase userUpgradesGetAllUseCase;
    private final UserUpgradesGetUseCase userUpgradesGetUseCase;
    private final UserCharactersGetAllUseCase userCharactersGetAllUseCase;
    private final GetAllFreeAndUserCharacters getAllFreeAndUserCharacters;
    private final GetUserCharacterUseCase getUserCharacterUseCase;
    @PreAuthorize("@authorizationService.isCurrentUser(#id)")
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#id)")
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("upgrades/user/{id}")
    public ResponseEntity<List<UserUpgradeDTO>> getUserAllUpgrades(@PathVariable("id") long id) {
        List<UserUpgradeDTO>  userUpgrades = userUpgradesGetAllUseCase.getAllUserUpgrades(id);
        if (userUpgrades.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(userUpgrades);
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#id)")
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("characters/user/{id}")
    public ResponseEntity<List<UserCharacterDTO>> getUserAllCharacters(@PathVariable("id") long id) {
        List<UserCharacterDTO>  userCharacters = userCharactersGetAllUseCase.getAllUserCharacters(id);
        if (userCharacters.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(userCharacters);
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#userId)")
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("character/{characterId}/user/{userId}")
    public ResponseEntity<UserCharactersEntity> getUserCharacter(@PathVariable("characterId") long characterId, @PathVariable("userId") long userId) {
        Optional<UserCharactersEntity> userCharacter = getUserCharacterUseCase.getUserCharacter(userId, characterId);
        if (userCharacter.isPresent()) {
            return ResponseEntity.ok(userCharacter.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#id)")
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("userRelatedCharacters/{id}")
    public ResponseEntity<List<CharacterEntity>> getFreeAndUserCharacters(@PathVariable("id") long id) {
        List<CharacterEntity>  userCharacters = getAllFreeAndUserCharacters.getAllFreeAndUserCharacters(id);
        if (userCharacters.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(userCharacters);
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#userId)")
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("upgrades/{userId}/{upgradeId}")
    public UserUpgradesEntity getOnlyUserUpgrades(@PathVariable("userId") long userId, @PathVariable("upgradeId") long upgradeId) {

        return userUpgradesGetUseCase.getAllUserUpgrades(userId,upgradeId);
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#userID)")
    @DeleteMapping("{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userID) {
        deleteUserUseCase.deleteUser(userID);
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @RolesAllowed("USER")
    @PutMapping("/profile/{email}")
    public ResponseEntity<Void> updateUser(@PathVariable String email, @RequestBody @Valid UpdateUserRequest request) {
        updateUserUseCase.updateUser(email, request);
        return ResponseEntity.noContent().build();
    }

    //add preauth here
    @PreAuthorize("@authorizationService.isCurrentUser(#userId)")
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/{userId}/upgrades/{upgradeId}")
    public ResponseEntity<Void> addUpgradeToUser(@PathVariable Long userId, @PathVariable Long upgradeId) {
        try {
            addUpgradeToUserUseCase.addUpgradeToUser(userId, upgradeId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#userId)")
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/{userId}/characters/{characterId}")
    public ResponseEntity<Void> addCharacterToUser(@PathVariable Long userId, @PathVariable Long characterId) {
        try {
            addCharacterToUserUseCase.addCharacterToUser(userId, characterId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#userId)")
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{userId}/currency")
    public ResponseEntity<Integer> getUserCurrency(@PathVariable Long userId) {
        try {
            int currency = getUserCurrencyUseCase.getUserCurrency(userId);
            return ResponseEntity.ok(currency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/addCurrency")
    public ResponseEntity<Integer> addUserCurrency(@RequestBody @Valid AddScoreRequest request) {
        try {
            int currency = addUserCurrencyUseCase.AddUserCurrency(request.getUser_id(), request.getScore());
            return ResponseEntity.ok(currency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
