package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LineRouletteBetTest {
    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    LineRouletteBet.LineRouletteBetBuilder lineRouletteBetBuilder;
    final int LINE_ROULETTE_PAYOUT = 5;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        lineRouletteBetBuilder = LineRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void betResultContainsCorrectAmountWhenLineRouletteBetWins(int roll) throws IncorrectBetTypeException {
        List<Integer> bet = List.of(1, 2, 3, 4, 5, 6);
        Bet lineRouletteBet = lineRouletteBetBuilder.bet(bet).build();

        BetResult betResult = lineRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * LINE_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10 ,11, 12})
    public void betResultContainsCorrectAmountWhenLineRouletteBetLoses(int roll) throws IncorrectBetTypeException {
        List<Integer> bet = List.of(1, 2, 3, 4, 5, 6);
        Bet lineRouletteBet = lineRouletteBetBuilder.bet(bet).build();

        BetResult betResult = lineRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

}