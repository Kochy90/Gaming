package com.gaming_platform.service;

import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.NameNotFoundException;
import java.util.Map;
import java.util.Random;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    void gameServicePlayThrowsExceptionWhenGameNameNotFound() {
        CreateSinglePlayerSingleBetGameCommand command = new CreateSinglePlayerSingleBetGameCommand("MONOPOLY", new Random().nextLong(), Map.of());

        Assertions.assertThrows(NameNotFoundException.class, () -> gameService.playGame(command));
    }

}