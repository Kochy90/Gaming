package com.gamingPlatform.service;

import com.gamingPlatform.GamingPlatformApplication;
import com.gamingPlatform.games.CommandToHigherOrLowerGameConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = { CommandToHigherOrLowerGameConverter.class, GameService.class})
@Execution(ExecutionMode.CONCURRENT)
@Import(GameServiceIntTest.GameServiceIntTestContextConfiguration.class)
class GameServiceIntTest {

    @TestConfiguration
    static class GameServiceIntTestContextConfiguration {
        @Bean
        public GameService gameService() {
            return new GameService();
        }
    }

    @Autowired
    private GameService gameService;

//    @Autowired
//    private CommandToHigherOrLowerGameConverter converter;

    GameServiceIntTest(GameService gameService) {
        this.gameService = gameService;
    }

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void test() {
        int i = 0;
        Float winnings = 0f;

        while (i < 41667) {
//            CreateGameCommand command = generateHigherOrLowerGameCommand(10f, (int) (Math.random() * 100));
            CreateGameCommand command = new CreateGameCommand("HIGHER_OR_LOWER", Map.of("bet", 10f, "playersNumber", (int) (Math.random() * 100)));
            winnings += gameService.playGame(command);
            i++;
        }
        System.out.println(winnings);

    }




}