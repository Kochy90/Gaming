package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitRouletteBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    SplitRouletteBet.SplitRouletteBetBuilder splitRouletteBetBuilder;
    final int SPLIT_ROULETTE_PAYOUT = 17;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        splitRouletteBetBuilder = SplitRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 8})
    public void betResultContainsCorrectAmountWhenSplitRouletteBetWins(int roll) throws IncorrectBetTypeException {
        List<Integer> bet = List.of(5, 8);
        Bet splitRouletteBet = splitRouletteBetBuilder.bet(bet).build();

        BetResult betResult = splitRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * SPLIT_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @Test
    public void betResultContainsZeroAmountWhenSplitRouletteBetLoses() throws IncorrectBetTypeException {
        List<Integer> bet = List.of(5, 8);
        Bet splitRouletteBet = splitRouletteBetBuilder.bet(bet).build();

        BetResult betResult = splitRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0d, betResult.getAmount())
        );
    }

}