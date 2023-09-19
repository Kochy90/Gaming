package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SnakeRouletteGameTypeBetTest {

    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    Bet snakeRouletteBet;
    final int SNAKE_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setUp() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        snakeRouletteBet = SnakeRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount).build();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9, 12, 14, 16, 19, 23, 27, 30, 32, 34})
    public void betResultContainsCorrectAmountWhenSnakeRouletteBetWins(int roll) throws IncorrectBetTypeException {

        BetResult betResult = snakeRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(3L, betResult.getBetId()),
                () -> assertEquals(amount * SNAKE_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 3, 4, 6, 7, 8, 10, 11, 13, 15, 17, 18 ,20, 21, 22, 24, 25, 26, 28, 29, 31, 33, 35, 36})
    public void betResultContainsZeroWhenSnakeRouletteBetLoses(int roll) throws IncorrectBetTypeException {

        BetResult betResult = snakeRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(3L, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }


}