package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StraightRouletteBetTest {
    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    StraightRouletteBet.StraightRouletteBetBuilder straightRouletteBetBuilder;
    final int STRAIGHT_ROULETTE_PAYOUT = 35;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        straightRouletteBetBuilder = StraightRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @Test
    public void betResultContainsCorrectAmountWhenStraightRouletteBetWins() throws IncorrectBetTypeException {
        int bet = 9;
        Bet straightRouletteBet = straightRouletteBetBuilder.bet(bet).build();

        BetResult betResult = straightRouletteBet.getResult(9);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * STRAIGHT_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @Test
    public void betResultContainsZeroAmountWhenStraightRouletteBetLoses() throws IncorrectBetTypeException {
        int bet = 9;
        Bet straightRouletteBet = straightRouletteBetBuilder.bet(bet).build();

        BetResult betResult = straightRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0d, betResult.getAmount())
        );
    }

}