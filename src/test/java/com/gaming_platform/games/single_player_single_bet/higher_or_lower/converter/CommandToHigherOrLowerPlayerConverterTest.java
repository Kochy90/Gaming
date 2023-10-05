package com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gaming_platform.UnitTestConstants.generateHigherOrLowerBetCommandBuilder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommandToHigherOrLowerPlayerConverterTest {

    CommandToHigherOrLowerPlayerConverter playerConverter;

    @Mock
    HigherOrLowerBetConverter betConverter;

    @BeforeEach
    void setUp() {
        playerConverter = new CommandToHigherOrLowerPlayerConverter(betConverter);
    }

    @Test
    void convertCommandToHigherOrLowerPlayerCompletesSuccessfully() throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        Long gameId = 1L;
        Long playerId = 2L;
        Long betId = 3L;
        double betAmount = 50;
        int bet = 70;

        CreateBetCommand createBetCommand = generateHigherOrLowerBetCommandBuilder().id(betId).amount(betAmount).bet(bet).build();
        CreateSingleBetPlayerCommand playerCommand = new CreateSingleBetPlayerCommand(playerId, createBetCommand);

        Mockito.when(betConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand)).thenReturn(
                new HigherOrLowerBet(betId, playerId, gameId, betAmount, bet));
        HigherOrLowerPlayer player = playerConverter.convertCommandToSingleBetPlayer(gameId, playerCommand);

        assertAll("player",
                () -> assertEquals(playerId, player.getPlayerId()),
                () -> assertEquals(gameId, player.getBet().getGameId()),
                () -> assertEquals(betId, player.getBet().getBetId()),
                () -> assertEquals(betAmount, player.getBet().getAmount()));
    }

    @Test
    void converterThrowsExceptionWhenPlayerIdFieldIsNull() throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        Long gameId = 1L;
        Long playerId = null;
        CreateBetCommand createBetCommand = CreateBetCommand.builder().build();

        CreateSingleBetPlayerCommand playerCommand = new CreateSingleBetPlayerCommand(playerId, createBetCommand);

        InvalidPlayerException thrown = Assertions.assertThrows(InvalidPlayerException.class,
                () -> playerConverter.convertCommandToSingleBetPlayer(gameId, playerCommand));

        Assertions.assertTrue(thrown.getMessage().contains("Invalid Player Id"));
    }

    @Test
    void converterThrowsExceptionWhenCreateBetCommandFieldIsNull() throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        Long gameId = 1L;
        Long playerId = 2L;
        CreateBetCommand createBetCommand = null;

        CreateSingleBetPlayerCommand playerCommand = new CreateSingleBetPlayerCommand(playerId, createBetCommand);

        InvalidPlayerException thrown = Assertions.assertThrows(InvalidPlayerException.class,
                () -> playerConverter.convertCommandToSingleBetPlayer(gameId, playerCommand));

        Assertions.assertTrue(thrown.getMessage().contains("Player's Higher or Lower bet missing"));
    }

}