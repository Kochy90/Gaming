package com.gaming_platform.games;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter.CommandToHigherOrLowerGameConverter;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter.CommandToHigherOrLowerPlayerConverter;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerGame;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gaming_platform.UnitTestConstants.createHigherOrLowerGameCommandBuilder;
import static com.gaming_platform.UnitTestConstants.generateHigherOrLowerBetCommandBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommandToHigherOrLowerGameConverterTest {

    CommandToHigherOrLowerGameConverter gameConverter;

    @Mock
    CommandToHigherOrLowerPlayerConverter playerConverter;

    @BeforeEach
    void setUp() {
        gameConverter = new CommandToHigherOrLowerGameConverter(playerConverter);
    }

// TODO check implementation of Mock - can this be done better by using the generated GameId instead of the mocked one
    @Test
    void buildHigherOrLowerGameFromCommand() throws InvalidPlayerException {
        long playerId = 2L;
        long betId = 3L;
        double betAmount = 50;
        int bet = 70;
        long gameId = 2L;

        CreateBetCommand betCommand = generateHigherOrLowerBetCommandBuilder().id(betId).amount(betAmount).bet(bet).build();
        CreateSingleBetPlayerCommand playerCommand = new CreateSingleBetPlayerCommand(playerId, betCommand);
        CreateSinglePlayerSingleBetGameCommand gameCommand = createHigherOrLowerGameCommandBuilder()
                .gameId(playerId)
                .createSingleBetPlayerCommand(playerCommand)
                .build();

        HigherOrLowerBet higherOrLowerBet = new HigherOrLowerBet(betId, playerId, gameId, betAmount, bet);
        HigherOrLowerPlayer higherOrLowerPlayer = new HigherOrLowerPlayer(playerId, higherOrLowerBet);

        Mockito.when(playerConverter.convertCommandToSingleBetPlayer(anyLong(), any(CreateSingleBetPlayerCommand.class)))
                .thenReturn(higherOrLowerPlayer);
        HigherOrLowerGame higherOrLowerGame = gameConverter.convert(gameCommand);

        HigherOrLowerPlayer testPlayer = higherOrLowerGame.getPlayer();

        assertAll("higherOrLowerGame",
                () -> assertEquals(playerId, testPlayer.getPlayerId()),
                () -> assertEquals(playerId, testPlayer.getBet().getPlayerId()),
                () -> assertEquals(50, testPlayer.getBet().getAmount()),
                () -> assertEquals(70, testPlayer.getBet().getBet())
        );
    }

    @Test
    void higherOrLowerGameConverterOnlyConvertsWhenGameTypeIsCorrect() {
        GameType higherOrLowerGameType = GameType.HIGHER_OR_LOWER;
        GameType rouletteGameType = GameType.ROULETTE;

        boolean shouldConvert = gameConverter.canConvert(higherOrLowerGameType);
        boolean shouldNotConvert = gameConverter.canConvert(rouletteGameType);

        assertAll("booleans",
                () -> assertTrue(shouldConvert),
                () -> assertFalse(shouldNotConvert)
        );
    }

}