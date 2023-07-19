package com.gaming_platform.games;

import com.gaming_platform.converters.CommandToHigherOrLowerGameConverter;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.service.CreateGameCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static com.gaming_platform.UnitTestConstants.generateHigherOrLowerGameCommand;

@SpringBootTest
class CommandToHigherOrLowerGameConverterTest {

    @Autowired
    CommandToHigherOrLowerGameConverter converter;

    @Test
    void buildHigherOrLowerGameFromCommand() throws ValueOutOfBoundsException, InvalidFieldException {
        CreateGameCommand command = generateHigherOrLowerGameCommand(50d, 85);

        com.gaming_platform.games.HigherOrLower game = (com.gaming_platform.games.HigherOrLower) converter.build(command.getGameVariables());
        assertAll("game",
                () -> assertEquals(50.0, game.getBet()),
                () -> assertEquals(85, game.getPlayersNumber())
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandDoesNotValidateBet() {
        CreateGameCommand command = generateHigherOrLowerGameCommand(-200d, 85);

        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class, () -> converter.build(command.getGameVariables()));

        Assertions.assertTrue(thrown.getMessage().contains("Value of bet must be larger than 0")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandDoesNotValidatePlayersNumberTooLow() {
        CreateGameCommand command = generateHigherOrLowerGameCommand(50.5d, 0);

        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class, () -> converter.build(command.getGameVariables()));

        Assertions.assertTrue(thrown.getMessage().contains("Value of player's number must be between 0 and 100")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandDoesNotValidatePlayersNumberTooHigh() {
        CreateGameCommand command = generateHigherOrLowerGameCommand(50.5d, 101);

        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class, () -> converter.build(command.getGameVariables()));

        Assertions.assertTrue(thrown.getMessage().contains("Value of player's number must be between 0 and 100")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenBetKeyNotPresent() {
        CreateGameCommand command = new CreateGameCommand("HIGHER_OR_LOWER", Map.of("playersNumber", 50));

        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class, () -> converter.build(command.getGameVariables()));

        Assertions.assertTrue(thrown.getMessage().contains("Incorrect field name or value for bet")
        );
    }

    @Test
    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenPlayersNumberNotPresent() {
        CreateGameCommand command = new CreateGameCommand("HIGHER_OR_LOWER", Map.of("bet", 50.0d));

        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class, () -> converter.build(command.getGameVariables()));

        Assertions.assertTrue(thrown.getMessage().contains("Incorrect field name or value for playersNumber")
        );
    }

}