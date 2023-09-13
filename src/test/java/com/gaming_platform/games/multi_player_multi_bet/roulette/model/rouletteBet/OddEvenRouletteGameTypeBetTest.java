package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class OddEvenRouletteGameTypeBetTest {

    Double amount;
    Bet oddRouletteBet, evenRouletteBet;
    Long gameId, playerId, betIdOdd, bedIdEven;
    final int ODD_EVEN_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betIdOdd = 3L;
        bedIdEven = 4L;
        amount = 50d;
        oddRouletteBet = createOddEvenRouletteBet(gameId, playerId, betIdOdd, amount, "ODD");
        evenRouletteBet = createOddEvenRouletteBet(gameId, playerId, bedIdEven, amount, "EVEN");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35})
    public void oddRouletteBetWinsWithCorrectAmountWhenValueOfRollIsOdd(int roll) throws IncorrectBetTypeException {
        BetResult betResultOdd = oddRouletteBet.getResult(roll);
        BetResult betResultEven = evenRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betIdOdd, betResultOdd.getBetId()),
                () -> assertEquals(amount * ODD_EVEN_ROULETTE_PAYOUT, betResultOdd.getAmount()),
                () -> assertEquals(bedIdEven, betResultEven.getBetId()),
                () -> assertEquals(0d, betResultEven.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20 ,22, 24, 26, 28, 30, 32, 34, 36})
    public void oddRouletteBetWinsWithCorrectAmountWhenValueOfRollIsEven(int roll) throws IncorrectBetTypeException {
        BetResult betResultOdd = oddRouletteBet.getResult(roll);
        BetResult betResultEven = evenRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betIdOdd, betResultOdd.getBetId()),
                () -> assertEquals(0d, betResultOdd.getAmount()),
                () -> assertEquals(bedIdEven, betResultEven.getBetId()),
                () -> assertEquals(amount * ODD_EVEN_ROULETTE_PAYOUT, betResultEven.getAmount())
        );
    }

    @Test
    public void oddEvenRouletteBetLosesWhenRollIs0() throws IncorrectBetTypeException {
        BetResult betResultOdd = oddRouletteBet.getResult(0);
        BetResult betResultEven = evenRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betIdOdd, betResultOdd.getBetId()),
                () -> assertEquals(0d, betResultOdd.getAmount()),
                () -> assertEquals(bedIdEven, betResultEven.getBetId()),
                () -> assertEquals(0d, betResultEven.getAmount())
        );
    }

    @Test
    public void throwsIncorrectBetTypeExceptionWhenBetNameIsIncorrect() {
        String bet = "INCORRECT_BET_NAME";
        Bet oddEvenRouletteBet = createOddEvenRouletteBet(gameId, playerId, betIdOdd, amount, bet);

        assertThrows(IncorrectBetTypeException.class, () -> oddEvenRouletteBet.getResult(20));
    }

    private static OddEvenRouletteBet createOddEvenRouletteBet(long gameId, long playerId, long betId, double amount, String bet) {
        return OddEvenRouletteBet.builder()
                .gameId(gameId)
                .playerId(playerId)
                .betId(betId)
                .amount(amount)
                .bet(bet)
                .build();
    }

}