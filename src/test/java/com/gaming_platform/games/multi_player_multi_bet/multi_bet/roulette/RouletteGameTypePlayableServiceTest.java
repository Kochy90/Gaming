package com.gaming_platform.games.multi_player_multi_bet.multi_bet.roulette;

import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.games.multi_player_multi_bet.roulette.RoulettePlayableService;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RouletteGame;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBet;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.StraightRouletteBet;
import com.gaming_platform.result_dto.BetResult;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import com.gaming_platform.result_dto.PlayerResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RouletteGameTypePlayableServiceTest {

    @Autowired
    RoulettePlayableService roulettePlayableService;

    @Test
    public void playRouletteGame() {
        Long playerId = 2L;
        Long betId = 3L;
        int rouletteBetNumber = 9;

        RouletteGame rouletteGame = new RouletteGame();
        Long gameId = rouletteGame.getGameId();
        int roll = rouletteGame.getRoll();

        RouletteBet rouletteBet = generateStraightRouletteBet(gameId, playerId, betId, rouletteBetNumber);
        RoulettePlayer roulettePlayer = new RoulettePlayer(playerId, List.of(rouletteBet));
        rouletteGame.setPlayers(List.of(roulettePlayer));

        MultiPlayerMultiBetGameResult gameResult = roulettePlayableService.play(rouletteGame);
        PlayerResult playerResult = gameResult.getResults().get(0);
        BetResult betResult = playerResult.getResults().get(0);

        assertAll("gameResult",
                () -> assertEquals(gameId, gameResult.getGameId()),
                () -> assertEquals(roll, gameResult.getRoll()),
                () -> assertEquals(playerId, playerResult.getPlayerId()),
                () -> assertEquals(betId, betResult.getBetId())
        );
        if (roll == rouletteBetNumber) {
            assertTrue(betResult.getAmount() > 0d);
        } else {
            assertEquals(0, (double) betResult.getAmount());
        }
    }

    @Test
    public void canPlayReturnsTrueForRouletteGameType() {
        GameType gameType = GameType.ROULETTE;
        boolean canPlayResult = roulettePlayableService.canPlay(gameType);
        assertTrue(canPlayResult);
    }

    @Test
    public void canPlayReturnsFalseForNonRouletteGameType() {
        GameType gameType = GameType.HIGHER_OR_LOWER;
        boolean canPlayResult = roulettePlayableService.canPlay(gameType);
        assertFalse(canPlayResult);
    }

    private static StraightRouletteBet generateStraightRouletteBet(Long gameId, Long playerId, Long betId, int bet) {
        return StraightRouletteBet.builder().betId(betId).playerId(playerId).gameId(gameId)
                .amount(50d).bet(bet).build();
    }
}