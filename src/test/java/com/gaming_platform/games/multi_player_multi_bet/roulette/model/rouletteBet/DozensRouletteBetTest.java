package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DozensRouletteBetTest {
    Double amount;
    Long gameId, playerId, betId1, betId2, betId3;
    Bet dozensRouletteBetFirst, dozensRouletteBetSecond, dozensRouletteBetThird;
    final int DOZENS_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId1 = 3L;
        betId2 = 4L;
        betId3 = 5L;
        amount = 50d;

        dozensRouletteBetFirst = createDozensRouletteBet(gameId, playerId, betId1, amount, "FIRST");
        dozensRouletteBetSecond = createDozensRouletteBet(gameId, playerId, betId2, amount, "SECOND");
        dozensRouletteBetThird = createDozensRouletteBet(gameId, playerId, betId3, amount, "THIRD");
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11, 12})
    public void thirdDozensRouletteBetWinsWithCorrectAmountWhenValueOfRollBelow13(int roll) throws IncorrectBetTypeException {
        BetResult betResultFirst = dozensRouletteBetFirst.getResult(roll);
        BetResult betResultSecond = dozensRouletteBetSecond.getResult(roll);
        BetResult betResultThird = dozensRouletteBetThird.getResult(roll);

        assertAll("betResults",
                () -> assertEquals(betId1, betResultFirst.getBetId()),
                () -> assertEquals(amount * DOZENS_ROULETTE_PAYOUT, betResultFirst.getAmount()),
                () -> assertEquals(betId2, betResultSecond.getBetId()),
                () -> assertEquals(0d, betResultSecond.getAmount()),
                () -> assertEquals(betId3, betResultThird.getBetId()),
                () -> assertEquals(0d, betResultThird.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24})
    public void secondDozensRouletteBetWinsWithCorrectAmountWhenValueOfRollBetween13And24(int roll) throws IncorrectBetTypeException {
        BetResult betResultFirst = dozensRouletteBetFirst.getResult(roll);
        BetResult betResultSecond = dozensRouletteBetSecond.getResult(roll);
        BetResult betResultThird = dozensRouletteBetThird.getResult(roll);

        assertAll("betResults",
                () -> assertEquals(betId1, betResultFirst.getBetId()),
                () -> assertEquals(0d, betResultFirst.getAmount()),
                () -> assertEquals(betId2, betResultSecond.getBetId()),
                () -> assertEquals(amount * DOZENS_ROULETTE_PAYOUT, betResultSecond.getAmount()),
                () -> assertEquals(betId3, betResultThird.getBetId()),
                () -> assertEquals(0d, betResultThird.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36})
    public void thirdDozensRouletteBetWinsWithCorrectAmountWhenValueOfRollAbove24(int roll) throws IncorrectBetTypeException {
        BetResult betResultFirst = dozensRouletteBetFirst.getResult(roll);
        BetResult betResultSecond = dozensRouletteBetSecond.getResult(roll);
        BetResult betResultThird = dozensRouletteBetThird.getResult(roll);

        assertAll("betResults",
                () -> assertEquals(betId1, betResultFirst.getBetId()),
                () -> assertEquals(0d, betResultFirst.getAmount()),
                () -> assertEquals(betId2, betResultSecond.getBetId()),
                () -> assertEquals(0d, betResultSecond.getAmount()),
                () -> assertEquals(betId3, betResultThird.getBetId()),
                () -> assertEquals(amount * DOZENS_ROULETTE_PAYOUT, betResultThird.getAmount())
        );
    }

    @Test
    public void allDozensRouletteBetsLoseWhenValueOfRollIs0() throws IncorrectBetTypeException {
        BetResult betResultFirst = dozensRouletteBetFirst.getResult(0);
        BetResult betResultSecond = dozensRouletteBetSecond.getResult(0);
        BetResult betResultThird = dozensRouletteBetThird.getResult(0);

        assertAll("betResults",
                () -> assertEquals(betId1, betResultFirst.getBetId()),
                () -> assertEquals(0d, betResultFirst.getAmount()),
                () -> assertEquals(betId2, betResultSecond.getBetId()),
                () -> assertEquals(0d, betResultSecond.getAmount()),
                () -> assertEquals(betId3, betResultThird.getBetId()),
                () -> assertEquals(0, betResultThird.getAmount())
        );
    }

    private static DozensRouletteBet createDozensRouletteBet(long gameId, long playerId, long betId, double amount, String bet) {
        return DozensRouletteBet.builder()
                .gameId(gameId)
                .playerId(playerId)
                .betId(betId)
                .amount(amount)
                .bet(bet)
                .build();
    }

}