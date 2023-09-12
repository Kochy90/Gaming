package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HighLowRouletteBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    HighLowRouletteBet.HighLowRouletteBetBuilder highLowRouletteBetBuilder;
    final int HIGH_LOW_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        highLowRouletteBetBuilder = HighLowRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36})
    public void betResultContainsCorrectAmountWhenHighRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "HIGH";
        Bet higLowRouletteBet = highLowRouletteBetBuilder.bet(bet).build();

        BetResult betResult = higLowRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * HIGH_LOW_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18})
    public void betResultContainsCorrectAmountWhenLowRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "LOW";
        Bet higLowRouletteBet = highLowRouletteBetBuilder.bet(bet).build();

        BetResult betResult = higLowRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * HIGH_LOW_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"HIGH", "LOW"})
    public void betResultContainsZeroAmountWhenHighLowRouletteBetLoses(String bet) throws IncorrectBetTypeException {
        Bet higLowRouletteBet = highLowRouletteBetBuilder.bet(bet).build();

        BetResult betResult = higLowRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

    @Test
    public void throwsIncorrectBetTypeExceptionWhenBetNameIsIncorrect() {
        String bet = "INCORRECT_BET_NAME";
        Bet higLowRouletteBet = highLowRouletteBetBuilder.bet(bet).build();

        assertThrows(IncorrectBetTypeException.class, () -> higLowRouletteBet.getResult(20));
    }

}