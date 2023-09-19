package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HighLowRouletteBetTest {
    Double amount;
    Bet highRouletteBet, lowRouletteBet;
    Long gameId, playerId, betIdHigh, bedIdLow;
    final int HIGH_LOW_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betIdHigh = 3L;
        bedIdLow = 4L;
        amount = 50d;
        highRouletteBet = createHighLowRouletteBet(gameId, playerId, betIdHigh, amount, "HIGH");
        lowRouletteBet = createHighLowRouletteBet(gameId, playerId, bedIdLow, amount, "LOW");
    }

    @ParameterizedTest
    @ValueSource(ints = {19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36})
    public void highRouletteBetWinsWithCorrectAmountWhenRollAbove18(int roll) throws IncorrectBetTypeException {
        BetResult betResultHigh = highRouletteBet.getResult(roll);
        BetResult betResultLow = lowRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betIdHigh, betResultHigh.getBetId()),
                () -> assertEquals(amount * HIGH_LOW_ROULETTE_PAYOUT, betResultHigh.getAmount()),
                () -> assertEquals(bedIdLow, betResultLow.getBetId()),
                () -> assertEquals(0d, betResultLow.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18})
    public void lowRouletteBetWinsWithCorrectAmountWhenRollBelow19(int roll) throws IncorrectBetTypeException {
        BetResult betResultHigh = highRouletteBet.getResult(roll);
        BetResult betResultLow = lowRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betIdHigh, betResultHigh.getBetId()),
                () -> assertEquals(0d, betResultHigh.getAmount()),
                () -> assertEquals(bedIdLow, betResultLow.getBetId()),
                () -> assertEquals(amount * HIGH_LOW_ROULETTE_PAYOUT, betResultLow.getAmount())
        );
    }

    @Test
    public void highLowRouletteBetLosesWhenRollIs0() throws IncorrectBetTypeException {
        BetResult betResultHigh = highRouletteBet.getResult(0);
        BetResult betResultLow = lowRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betIdHigh, betResultHigh.getBetId()),
                () -> assertEquals(0d, betResultHigh.getAmount()),
                () -> assertEquals(bedIdLow, betResultLow.getBetId()),
                () -> assertEquals(0d, betResultLow.getAmount())
        );
    }

    @Test
    public void throwsIncorrectBetTypeExceptionWhenBetNameIsIncorrect() {
        String bet = "INCORRECT_BET_NAME";
        Bet higLowRouletteBet = createHighLowRouletteBet(gameId, playerId, bedIdLow, amount, bet);

        assertThrows(IncorrectBetTypeException.class, () -> higLowRouletteBet.getResult(20));
    }

    private static HighLowRouletteBet createHighLowRouletteBet(long gameId, long playerId, long betId, double amount, String bet) {
        return HighLowRouletteBet.builder()
                .gameId(gameId)
                .playerId(playerId)
                .betId(betId)
                .amount(amount)
                .bet(bet)
                .build();
    }

}