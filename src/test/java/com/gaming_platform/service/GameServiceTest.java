package com.gaming_platform.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.NameNotFoundException;
import java.util.Map;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    void gameServicePlayThrowsExceptionWhenGameNameNotFound() {
        CreateGameCommand command = new CreateGameCommand("MONOPOLY", Map.of());

        Assertions.assertThrows(NameNotFoundException.class, () -> gameService.playGame(command));
    }

}