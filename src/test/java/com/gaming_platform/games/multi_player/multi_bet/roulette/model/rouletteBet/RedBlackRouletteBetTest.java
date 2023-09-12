package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackRouletteBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    RedBlackRouletteBet.RedBlackRouletteBetBuilder redBlackRouletteBetBuilder;
    final int RED_BLACK_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        redBlackRouletteBetBuilder = RedBlackRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36})
    public void betResultContainsCorrectAmountWhenRedRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "RED";
        Bet higLowRouletteBet = redBlackRouletteBetBuilder.bet(bet).build();

        BetResult betResult = higLowRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * RED_BLACK_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35})
    public void betResultContainsCorrectAmountWhenBlackRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "BLACK";
        Bet higLowRouletteBet = redBlackRouletteBetBuilder.bet(bet).build();

        BetResult betResult = higLowRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * RED_BLACK_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"ODD", "EVEN"})
    public void betResultContainsZeroAmountWhenRedBlackRouletteBetLoses(String bet) throws IncorrectBetTypeException {
        Bet higLowRouletteBet = redBlackRouletteBetBuilder.bet(bet).build();

        BetResult betResult = higLowRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

    @Test
    public void throwsIncorrectBetTypeExceptionWhenBetNameIsIncorrect() {
        String bet = "INCORRECT_BET_NAME";
        Bet higLowRouletteBet = redBlackRouletteBetBuilder.bet(bet).build();

        assertThrows(IncorrectBetTypeException.class, () -> higLowRouletteBet.getResult(20));
    }

}