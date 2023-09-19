package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StreetRouletteBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    StreetRouletteBet.StreetRouletteBetBuilder streetRouletteBetBuilder;
    final int STREET_ROULETTE_PAYOUT = 11;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        streetRouletteBetBuilder = StreetRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void betResultContainsCorrectAmountWhenStreetRouletteBetWins(int roll) throws IncorrectBetTypeException {
        List<Integer> bet = List.of(1, 2, 3);
        Bet streetRouletteBet = streetRouletteBetBuilder.bet(bet).build();

        BetResult betResult = streetRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * STREET_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {10 ,11, 12})
    public void betResultContainsCorrectAmountWhenStreetRouletteBetLoses(int roll) throws IncorrectBetTypeException {
        List<Integer> bet = List.of(4, 5, 6);
        Bet streetRouletteBet = streetRouletteBetBuilder.bet(bet).build();

        BetResult betResult = streetRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

}