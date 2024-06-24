package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.GameUiGetAllUseCaseImpl;
import strike.ex.persistence.entity.GameUiEntity;
import strike.ex.persistence.repository.GameUiRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class GameUiGetAllUseCaseImplTest {

    @Mock
    private GameUiRepository gameUiRepository;

    @InjectMocks
    private GameUiGetAllUseCaseImpl gameUiGetAllUseCase;

    @Test
    void testGetAllGameUi() {
        GameUiEntity ui1 = new GameUiEntity();
        GameUiEntity ui2 = new GameUiEntity();
        when(gameUiRepository.findAll()).thenReturn(Arrays.asList(ui1, ui2));

        List<GameUiEntity> gameUis = gameUiGetAllUseCase.getAllGameUi();

        assertEquals(2, gameUis.size());
        verify(gameUiRepository, times(1)).findAll();
    }
}
