package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * This is a Bet on the outcome being a number within one of the three columns of twelve numbers that span the longest side of the roulette table.
 * Column 1 – This covers the ball landing on the numbers 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34
 * Column 2 – This covers the ball landing on numbers 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35
 * Column 3 – This covers the ball landing on numbers 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36
 *
 * Each column pays 2:1, or double your Bet.
 */

@Getter
public class ColumnsRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.COLUMNS.name();
    private final int payout = 2;
    private final String bet;

    private static final Set<Integer> COLUMN_1 = Set.of(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34);
    private static final Set<Integer> COLUMN_2 = Set.of(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35);
    private static final Set<Integer> COLUMN_3 = Set.of(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36);

    @Builder
    public ColumnsRouletteBet(long betId, long playerId, long gameId, double amount, String bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    protected boolean isWinningBet(int roll) throws IncorrectBetTypeException {
        if (roll == 0) {
            return false;
        }

        return switch (bet.toUpperCase()) {
            case "COLUMN_1" -> COLUMN_1.contains(roll);
            case "COLUMN_2" -> COLUMN_2.contains(roll);
            case "COLUMN_3" -> COLUMN_3.contains(roll);
            default -> throw new IncorrectBetTypeException("Bet type not recognised for ColumnsRouletteBet");
        };
    }

}

