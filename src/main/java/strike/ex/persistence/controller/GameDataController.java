package strike.ex.persistence.controller;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import strike.ex.persistence.business.*;
import strike.ex.persistence.business.impl.character.CharacterGetAllUseCaseImpl;
import strike.ex.persistence.domain.CreateGameRequest;
import strike.ex.persistence.domain.SaveUserScoreRequest;
import strike.ex.persistence.domain.TopGamesRequest;
import strike.ex.persistence.domain.TopScoresRequest;
import strike.ex.persistence.entity.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gameData")
@AllArgsConstructor


public class GameDataController {
    private final EnemyGetAllUseCase enemyGetAll;
    private final AbilityGetAllUseCase abilityGetAll;
    private final GameUiGetAllUseCase getAllGameUi;
    private final CharacterGetAllUseCaseImpl getAllCharacters;
    private final UpgradesGetAllUseCase upgradesGetAllUseCase;
    private final GetCharacterUseCase getCharactersWithAbilities;
    private final FreeCharacterGetAllUseCase freeCharacterGetAllUseCase;
    private final GetTop10MostPlayedGamesUseCase getTop10MostPlayedGamesUseCase;
    private final AddGameScoreToGameUseCase addGameScoreToGameUseCase;
    private final GetTop10GamesScoresUseCase getTop10GamesScoresUseCase;
    private final CreateGameUseCase createGameUseCase;
    private  final CharacterEditUseCase characterEditUseCase;
    private  final GetGamesUseCase getGamesUseCase;
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/game-ui")
    public List<GameUiEntity> getAllGameUi() {
        return getAllGameUi.getAllGameUi();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/createGame")
    public ResponseEntity<Void> createGame(@RequestBody @Valid CreateGameRequest request) {
        try {
            createGameUseCase.createGame(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/character/edit")
    public ResponseEntity<Void> editCharacter(@RequestBody @Valid CharacterEntity request) {
        try {
            characterEditUseCase.EditCharacter(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/TopGames")
    public List<TopGamesRequest> getTopGames() {
        return getTop10MostPlayedGamesUseCase.getTop10MostPlayedGames();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("game/{name}")
    public ResponseEntity<GameEntity> getGame(@PathVariable("name") String name) {
        Optional<GameEntity> userUpgrades = getGamesUseCase.getGame(name);
        return userUpgrades.map(ResponseEntity::ok).orElse(null);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/TopScores")
    public List<TopScoresRequest> getHighestScoreGames() {
        return getTop10GamesScoresUseCase.getTop10GamesScores();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/abilities")
    public List<AbilityEntity> getAllAbilities() {
        return abilityGetAll.getAllAbilities();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/enemies")
    public List<EnemyEntity> getAllEnemies() {
        return enemyGetAll.getAllEnemies();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/upgrades")
    public List<UpgradesEntity> getAllUpgrades() {
        return upgradesGetAllUseCase.getAllUpgrades();
    }
    @CrossOrigin(origins = "http://localhost:5173")
        @GetMapping("/characters")
    public List<CharacterEntity> getAllCharacters() {
        return getAllCharacters.getAllCharactersWithAbilities();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/characters/free")
    public List<CharacterEntity> getAllFreeCharacters() {
        return freeCharacterGetAllUseCase.getAllFreeCharacters();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/character/{character}")
    public ResponseEntity<CharacterEntity> getCharactersWithAbilities(@PathVariable("character") String character) {
        return ResponseEntity.ok().body(getCharactersWithAbilities.getCharactersWithAbilities(character));
    }
    @PreAuthorize("@authorizationService.isCurrentUser(#request.getUserid())")
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/saveScores")
    public ResponseEntity<Void> addUserScoreToGame(@RequestBody SaveUserScoreRequest request) {
        try {
            addGameScoreToGameUseCase.AddGameScoreToGame(request.getGame(),request.getUserid(),request.getScore());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
