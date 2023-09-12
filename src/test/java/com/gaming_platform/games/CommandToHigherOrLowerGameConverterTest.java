//package com.gaming_platform.games;
//
//import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
//import com.gaming_platform.games.single_player.single_bet.higher_or_lower.converter.CommandToHigherOrLowerGameConverter;
//import com.gaming_platform.exceptions.InvalidFieldException;
//import com.gaming_platform.exceptions.ValueOutOfBoundsException;
//import com.gaming_platform.games.single_player.single_bet.higher_or_lower.HigherOrLower;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Map;
//
//import static com.gaming_platform.UnitTestConstants.generateHigherOrLowerGameCommand;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//class CommandToHigherOrLowerGameConverterTest {
//
//    @Autowired
//    CommandToHigherOrLowerGameConverter converter;
//
//    @Test
//    void buildHigherOrLowerGameFromCommand() throws ValueOutOfBoundsException, InvalidFieldException {
//        CreateSinglePlayerSingleBetGameCommand command = generateHigherOrLowerGameCommand(50d, 85);
//
//        HigherOrLower game = (HigherOrLower) converter.build(command.getGameVariables());
//        assertAll("game",
//                () -> assertEquals(50.0, game.getBet()),
//                () -> assertEquals(85, game.getPlayersNumber())
//        );
//    }
//
//    @Test
//    void buildHigherOrLowerGameFromCommandDoesNotValidateBet() {
//        CreateSinglePlayerSingleBetGameCommand command = generateHigherOrLowerGameCommand(-200d, 85);
//
//        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class, () -> converter.build(command.getGameVariables()));
//
//        Assertions.assertTrue(thrown.getMessage().contains("Value of Bet must be larger than 0")
//        );
//    }
//
//    @Test
//    void buildHigherOrLowerGameFromCommandDoesNotValidatePlayersNumberTooLow() {
//        CreateSinglePlayerSingleBetGameCommand command = generateHigherOrLowerGameCommand(50.5d, 0);
//
//        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class, () -> converter.build(command.getGameVariables()));
//
//        Assertions.assertTrue(thrown.getMessage().contains("Value of MultiBetPlayer's number must be between 0 and 100")
//        );
//    }
//
//    @Test
//    void buildHigherOrLowerGameFromCommandDoesNotValidatePlayersNumberTooHigh() {
//        CreateSinglePlayerSingleBetGameCommand command = generateHigherOrLowerGameCommand(50.5d, 101);
//
//        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class, () -> converter.build(command.getGameVariables()));
//
//        Assertions.assertTrue(thrown.getMessage().contains("Value of MultiBetPlayer's number must be between 0 and 100")
//        );
//    }
//
//    @Test
//    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenBetKeyNotPresent() {
//        CreateSinglePlayerSingleBetGameCommand command = generateHigherOrLowerGameCommand(Map.of("playersNumber", 50));
//
//        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class, () -> converter.build(command.getGameVariables()));
//
//        Assertions.assertTrue(thrown.getMessage().contains("Incorrect field name or value for Bet")
//        );
//    }
//
//    @Test
//    void buildHigherOrLowerGameFromCommandThrowsExceptionWhenPlayersNumberNotPresent() {
//        CreateSinglePlayerSingleBetGameCommand command = generateHigherOrLowerGameCommand(Map.of("Bet", 50.0d));
//
//        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class, () -> converter.build(command.getGameVariables()));
//
//        Assertions.assertTrue(thrown.getMessage().contains("Incorrect field name or value for playersNumber")
//        );
//    }
//
//}