package com.gamingPlatform.games;

import com.gamingPlatform.service.CreateGameCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static com.gamingPlatform.TestConstants.generateHigherOrLowerGameCommand;

@SpringBootTest
class CommandToHigherOrLowerGameConverterTest {

    @Autowired
    CommandToHigherOrLowerGameConverter converter;

    @Test
    void buildHigherOrLowerGameFromCommand() {
        CreateGameCommand command = generateHigherOrLowerGameCommand(50f, 85);

        HigherOrLower game = (HigherOrLower) converter.buildHigherOrLowerGame(command);
        assertAll("game",
                () -> assertEquals(50f, game.getBet()),
                () -> assertEquals(85, game.getPlayersNumber())
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandDoesNotValidateBet() {
        CreateGameCommand command = generateHigherOrLowerGameCommand(-200f, 85);

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            converter.buildHigherOrLowerGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Value of bet must be larger than 0")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandDoesNotValidatePlayersNumberTooLow() {
        CreateGameCommand command = generateHigherOrLowerGameCommand(50.5f, 0);

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            converter.buildHigherOrLowerGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Value of player's number must be between 0 and 101")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandDoesNotValidatePlayersNumberTooHigh() {
        CreateGameCommand command = generateHigherOrLowerGameCommand(50.5f, 101);

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            converter.buildHigherOrLowerGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Value of player's number must be between 0 and 101")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenBetKeyNotPresent() {
        CreateGameCommand command = new CreateGameCommand("HIGHER_OR_LOWER", Map.of("playersNumber", 50));

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            converter.buildHigherOrLowerGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Incorrect field name or value for bet")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenBetIsIncorrectType() {
        CreateGameCommand command = new CreateGameCommand("HIGHER_OR_LOWER", Map.of("bet", 50, "playersNumber", 50));

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            converter.buildHigherOrLowerGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Incorrect field name or value for bet")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenPlayersNumberNotPresent() {
        CreateGameCommand command = new CreateGameCommand("HIGHER_OR_LOWER", Map.of("bet", 50f));

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            converter.buildHigherOrLowerGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Incorrect field name or value for playersNumber")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenPlayersNumberIsIncorrectType() {
        CreateGameCommand command = new CreateGameCommand("HIGHER_OR_LOWER", Map.of("bet", 50f, "playersNumber", 50d));

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            converter.buildHigherOrLowerGame(command);
        });

        Assertions.assertTrue(thrown.getMessage().contains(HttpStatus.BAD_REQUEST.toString())
                && thrown.getMessage().contains("Incorrect field name or value for playersNumber")
        );
    }



}