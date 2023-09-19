package com.gaming_platform.games;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter.CommandToHigherOrLowerGameConverter;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter.CommandToHigherOrLowerPlayerConverter;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerGame;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gaming_platform.UnitTestConstants.createHigherOrLowerGameCommandBuilder;
import static com.gaming_platform.UnitTestConstants.generateHigherOrLowerBetCommandBuilder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommandToHigherOrLowerGameConverterTest {

    @Autowired
    CommandToHigherOrLowerGameConverter gameConverter;

    @Mock
    CommandToHigherOrLowerPlayerConverter playerConverter;

    @Test
    void buildHigherOrLowerGameFromCommand() throws InvalidPlayerException {
        Long playerId = 2L;
        Long betId = 3L;
        double betAmount = 50;
        int bet = 70;

        CreateBetCommand betCommand = generateHigherOrLowerBetCommandBuilder().id(betId).amount(betAmount).bet(bet).build();
        CreateSingleBetPlayerCommand playerCommand = new CreateSingleBetPlayerCommand(playerId, betCommand);
        CreateSinglePlayerSingleBetGameCommand gameCommand = createHigherOrLowerGameCommandBuilder()
                .gameId(playerId)
                .createSingleBetPlayerCommand(playerCommand)
                .build();

        HigherOrLowerGame higherOrLowerGame = gameConverter.convert(gameCommand);

//        HigherOrLowerBet higherOrLowerBet = new HigherOrLowerBet(betId, playerId, higherOrLowerGame.getGameId(), betAmount, bet);
//        HigherOrLowerPlayer higherOrLowerPlayer = new HigherOrLowerPlayer(playerId, higherOrLowerBet);
//        Mockito.when(playerConverter.convertCommandToSingleBetPlayer(higherOrLowerGame.getGameId(), playerCommand)).thenReturn(higherOrLowerPlayer);

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