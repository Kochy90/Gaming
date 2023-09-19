package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ColumnsRouletteBetTest {
    Long gameId;
    Long playerId;
    Long betId;
    Double amount;
    ColumnsRouletteBet.ColumnsRouletteBetBuilder columnsRouletteBetBuilder;
    final int COLUMNS_ROULETTE_PAYOUT = 2;

    @BeforeEach
    void setup() {
        gameId = 1L;
        playerId = 2L;
        betId = 3L;
        amount = 50d;
        columnsRouletteBetBuilder = ColumnsRouletteBet.builder().gameId(gameId).playerId(playerId).betId(betId).amount(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34})
    public void betResultContainsCorrectAmountWhenColumnsCOLUMN_1RouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "COLUMN_1";
        Bet columnsRouletteBet = columnsRouletteBetBuilder.bet(bet).build();

        BetResult betResult = columnsRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * COLUMNS_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35})
    public void betResultContainsCorrectAmountWhenColumnsCOLUMN_2RouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "COLUMN_2";
        Bet columnsRouletteBet = columnsRouletteBetBuilder.bet(bet).build();

        BetResult betResult = columnsRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * COLUMNS_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36})
    public void betResultContainsCorrectAmountWhenColumnsCOLUMN_3RouletteBetWins(int roll) throws IncorrectBetTypeException {
        String bet = "COLUMN_3";
        Bet columnsRouletteBet = columnsRouletteBetBuilder.bet(bet).build();

        BetResult betResult = columnsRouletteBet.getResult(roll);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(amount * COLUMNS_ROULETTE_PAYOUT, betResult.getAmount())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"COLUMN_1", "COLUMN_2", "COLUMN_3"})
    public void betResultContainsZeroAmountWhenColumnsRouletteBetLoses(String bet) throws IncorrectBetTypeException {
        Bet columnsRouletteBet = columnsRouletteBetBuilder.bet(bet).build();

        BetResult betResult = columnsRouletteBet.getResult(0);

        assertAll("betResult",
                () -> assertEquals(betId, betResult.getBetId()),
                () -> assertEquals(0, betResult.getAmount())
        );
    }

}