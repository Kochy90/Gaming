package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FiveNumberRouletteBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    Bet fiveNumberRouletteBet;
    final int FIVE_NUMBER_ROULETTE_PAYOUT = 6;

    @BeforeEach
    void setUp() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        fiveNumberRouletteBet = FiveNumberRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount).build();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void betResultContainsCorrectAmountWhenFiveNumberRouletteBetWins(int roll) throws IncorrectBetTypeException {
        BetResult betResult = fiveNumberRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(3L, betResult.getBetId()),
                () -> assertEquals(amount * FIVE_NUMBER_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 ,18 ,19 ,20, 21, 22, 23, 24, 25, 26, 27, 28, 28, 29, 30, 31, 32, 33, 34, 35, 36})
    public void betResultContainsZeroWhenFiveNumberRouletteBetLoses(int roll) throws IncorrectBetTypeException {
        BetResult betResult = fiveNumberRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(3L, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

}