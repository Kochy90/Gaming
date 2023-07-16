package com.gamingPlatform.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;

//    @Autowired
//    private HigherOrLower higherOrLower;

    @Test
    void gameServicePlayThrowsExceptionWhenGameNameNotFound() {
        CreateGameCommand command = new CreateGameCommand("MONOPOLY", Map.of());

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            gameService.playGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Game type not found")
        );
    }

}