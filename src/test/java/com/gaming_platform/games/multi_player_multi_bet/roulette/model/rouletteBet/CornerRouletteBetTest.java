package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CornerRouletteBetTest {
    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    CornerRouletteBet.CornerRouletteBetBuilder cornerRouletteBetBuilder;
    final int CORNER_ROULETTE_PAYOUT = 8;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        cornerRouletteBetBuilder = CornerRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5})
    public void betResultContainsCorrectAmountWhenCornerRouletteBetWins(int roll) throws IncorrectBetTypeException {
        List<Integer> bet = List.of(1, 2, 4, 5);
        Bet cornerRouletteBet = cornerRouletteBetBuilder.bet(bet).build();

        BetResult betResult = cornerRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * CORNER_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @Test
    public void betResultContainsZeroAmountWhenCornerRouletteBetLoses() throws IncorrectBetTypeException {
        List<Integer> bet = List.of(1, 2, 4, 5);
        Bet cornerRouletteBet = cornerRouletteBetBuilder.bet(bet).build();

        BetResult betResult = cornerRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0d, betResult.getAmount())
        );
    }

}