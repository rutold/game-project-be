package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.EnemyGetAllUseCaseImpl;
import strike.ex.persistence.entity.EnemyEntity;
import strike.ex.persistence.repository.EnemyRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class EnemyGetAllUseCaseImplTest {

    @Mock
    private EnemyRepository enemyRepository;

    @InjectMocks
    private EnemyGetAllUseCaseImpl enemyGetAllUseCase;

    @Test
    void testGetAllEnemies() {
        EnemyEntity enemy1 = new EnemyEntity();
        EnemyEntity enemy2 = new EnemyEntity();
        when(enemyRepository.findAll()).thenReturn(Arrays.asList(enemy1, enemy2));

        List<EnemyEntity> enemies = enemyGetAllUseCase.getAllEnemies();

        assertEquals(2, enemies.size());
        verify(enemyRepository, times(1)).findAll();
    }
}
