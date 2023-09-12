package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DozensRouletteBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    DozensRouletteBet.DozensRouletteBetBuilder dozensRouletteBetBuilder;
    final int DOZENS_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        dozensRouletteBetBuilder = DozensRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11, 12})
    public void betResultContainsCorrectAmountWhenDozensFIRSTRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "FIRST";
        Bet dozensRouletteBet = dozensRouletteBetBuilder.bet(bet).build();

        BetResult betResult = dozensRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * DOZENS_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24})
    public void betResultContainsCorrectAmountWhenDozensSECONDRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "SECOND";
        Bet dozensRouletteBet = dozensRouletteBetBuilder.bet(bet).build();

        BetResult betResult = dozensRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * DOZENS_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36})
    public void betResultContainsCorrectAmountWhenDozensTHIRDRouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "THIRD";
        Bet dozensRouletteBet = dozensRouletteBetBuilder.bet(bet).build();

        BetResult betResult = dozensRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * DOZENS_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"FIRST", "SECOND", "THIRD"})
    public void betResultContainsZeroAmountWhenColumnsRouletteBetLoses(String bet) throws IncorrectBetTypeException {
        Bet columnsRouletteBet = dozensRouletteBetBuilder.bet(bet).build();

        BetResult betResult = columnsRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

}