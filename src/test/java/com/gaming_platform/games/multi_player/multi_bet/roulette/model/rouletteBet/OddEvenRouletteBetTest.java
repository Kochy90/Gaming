package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class OddEvenRouletteBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    OddEvenRouletteBet.OddEvenRouletteBetBuilder oddEvenRouletteBetBuilder;
    final int ODD_EVEN_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        oddEvenRouletteBetBuilder = OddEvenRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35})
    public void betResultContainsCorrectAmountWhenOddRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "ODD";
        Bet oddEvenRouletteBet = oddEvenRouletteBetBuilder.bet(bet).build();

        BetResult betResult = oddEvenRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * ODD_EVEN_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20 ,22, 24, 26, 28, 30, 32, 34, 36})
    public void betResultContainsCorrectAmountWhenEvenRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "EVEN";
        Bet oddEvenRouletteBet = oddEvenRouletteBetBuilder.bet(bet).build();

        BetResult betResult = oddEvenRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * ODD_EVEN_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"ODD", "EVEN"})
    public void betResultContainsZeroAmountWhenOddEvenRouletteBetLoses(String bet) throws IncorrectBetTypeException {
        Bet oddEvenRouletteBet = oddEvenRouletteBetBuilder.bet(bet).build();

        BetResult betResult = oddEvenRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

    @Test
    public void throwsIncorrectBetTypeExceptionWhenBetNameIsIncorrect() {
        String bet = "INCORRECT_BET_NAME";
        Bet oddEvenRouletteBet = oddEvenRouletteBetBuilder.bet(bet).build();

        assertThrows(IncorrectBetTypeException.class, () -> oddEvenRouletteBet.getResult(20));
    }

}