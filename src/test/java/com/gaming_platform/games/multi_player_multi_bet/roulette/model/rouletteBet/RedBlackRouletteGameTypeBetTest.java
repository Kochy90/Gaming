package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackRouletteGameTypeBetTest {

    Double amount;
    Bet redRouletteBet,blackRouletteBet;
    Long gameId, playerId, betIdRed, betIdBlack;
    final int RED_BLACK_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betIdRed = 3L;
        betIdBlack = 4L;
        amount = 50d;
        redRouletteBet = createRedBlackRouletteBet(gameId, playerId, betIdRed, amount, "RED");
        blackRouletteBet = createRedBlackRouletteBet(gameId, playerId, betIdBlack, amount, "BLACK");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36})
    public void redRouletteBetWinsWithCorrectAmountWhenColourOfRollIsRed(int roll) throws IncorrectBetTypeException {
        BetResult betResultRed = redRouletteBet.getResult(roll);
        BetResult betResultBlack = blackRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betIdRed, betResultRed.getBetId()),
                () -> assertEquals(amount * RED_BLACK_ROULETTE_PAYOUT, betResultRed.getAmount()),
                () -> assertEquals(betIdBlack, betResultBlack.getBetId()),
                () -> assertEquals(0d, betResultBlack.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35})
    public void blackRouletteBetWinsWithCorrectAmountWhenColourOfRollIsBlack(int roll) throws IncorrectBetTypeException {
        BetResult betResultRed = redRouletteBet.getResult(roll);
        BetResult betResultBlack = blackRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betIdRed, betResultRed.getBetId()),
                () -> assertEquals(0d, betResultRed.getAmount()),
                () -> assertEquals(betIdBlack, betResultBlack.getBetId()),
                () -> assertEquals(amount * RED_BLACK_ROULETTE_PAYOUT, betResultBlack.getAmount())
        );
    }

    @Test
    public void redBlackRouletteBetLosesWhenRollIs0() throws IncorrectBetTypeException {
        BetResult betResultRed = redRouletteBet.getResult(0);
        BetResult betResultBlack = blackRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betIdRed, betResultRed.getBetId()),
                () -> assertEquals(0d, betResultRed.getAmount()),
                () -> assertEquals(betIdBlack, betResultBlack.getBetId()),
                () -> assertEquals(0d, betResultBlack.getAmount())
        );
    }

    @Test
    public void throwsIncorrectBetTypeExceptionWhenBetNameIsIncorrect() {
        String bet = "INCORRECT_BET_NAME";
        Bet higLowRouletteBet = createRedBlackRouletteBet(gameId, playerId, betIdRed, amount, bet);

        assertThrows(IncorrectBetTypeException.class, () -> higLowRouletteBet.getResult(20));
    }

    private static RedBlackRouletteBet createRedBlackRouletteBet(long gameId, long playerId, long betId, double amount, String bet) {
        return RedBlackRouletteBet.builder()
                .gameId(gameId)
                .playerId(playerId)
                .betId(betId)
                .amount(amount)
                .bet(bet)
                .build();
    }

}