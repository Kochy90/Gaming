package com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gaming_platform.UnitTestConstants.generateHigherOrLowerBetCommandBuilder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HigherOrLowerBetConverterTest {

    @Autowired
    HigherOrLowerBetConverter betConverter;

    Long betId;
    Double betAmount;
    Integer bet;
    CreateBetCommand createBetCommand;
    CreateBetCommand.CreateBetCommandBuilder createBetCommandBuilder;

    @BeforeEach
    void setup() {
        betId = 3L;
        betAmount = 50d;
        bet = 70;
        createBetCommandBuilder = generateHigherOrLowerBetCommandBuilder().id(betId).amount(betAmount).bet(bet);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 50, 99})
    void convertCommandToHigherOrLowerBetCompletesSuccessfully(int bet) throws ValueOutOfBoundsException, InvalidFieldException {
        Long gameId = 1L;
        Long playerId = 2L;
        createBetCommand = createBetCommandBuilder.bet(bet).build();

        HigherOrLowerBet higherOrLowerBet = betConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand);

        assertAll("player",
                () -> assertEquals(playerId, higherOrLowerBet.getPlayerId()),
                () -> assertEquals(gameId, higherOrLowerBet.getGameId()),
                () -> assertEquals(betId, higherOrLowerBet.getBetId()),
                () -> assertEquals(bet, higherOrLowerBet.getBet()),
                () -> assertEquals(betAmount, higherOrLowerBet.getAmount()));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 100})
    void throwsValueOutOfBoundsExceptionWhenBetNotBetween0And100(int bet) {
        Long gameId = 1L;
        Long playerId = 2L;
        createBetCommand = createBetCommandBuilder.bet(bet).build();

        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class,
                () -> betConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand));

        Assertions.assertTrue(thrown.getMessage().contains("Value of Higher or Lower Bet must be between 0 and 100"));
    }

    @Test
    void converterThrowsExceptionWhenPlayerIdFieldIsNull() {
        Long gameId = 1L;
        Long playerId = null;
        createBetCommand = createBetCommandBuilder.build();

        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class,
                () -> betConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand));

        Assertions.assertTrue(thrown.getMessage().contains("playerId for Higher Or Lower bet is null"));
    }

    @Test
    void converterThrowsExceptionWhenGameIdFieldIsNull() {
        Long gameId = null;
        Long playerId = 2L;
        createBetCommand = createBetCommandBuilder.build();

        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class,
                () -> betConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand));

        Assertions.assertTrue(thrown.getMessage().contains("GameId for Higher Or Lower bet is null"));
    }

    @Test
    void converterThrowsExceptionWhenBetIdFieldIsNull() {
        Long gameId = 1L;
        Long playerId = 2L;
        createBetCommand = createBetCommandBuilder.id(null).build();

        InvalidFieldException thrown = Assertions.assertThrows(InvalidFieldException.class,
                () -> betConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand));

        Assertions.assertTrue(thrown.getMessage().contains("betId for Higher Or Lower bet is null"));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, -50})
    void converterThrowsExceptionWhenBetAmountFiledIsZeroOrLess(double betAmount) {
        Long gameId = 1L;
        Long playerId = 2L;
        createBetCommand = createBetCommandBuilder.amount(betAmount).build();

        ValueOutOfBoundsException thrown = Assertions.assertThrows(ValueOutOfBoundsException.class,
                () -> betConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand));

        Assertions.assertTrue(thrown.getMessage().contains("Value of Higher or Lower Bet Amount must be larger than 0"));
    }

}