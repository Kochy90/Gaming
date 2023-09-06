package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RouletteBet;
import lombok.Getter;

import java.util.Set;

/**
 *  Simple. You’re making a bet on whether a red number or a black number will win. These are marked “red” or “black” on the table.
 * <p> </p>
 *  This is an even money bet (1:1). But because of the 0 or 00 on the wheel – neither of which are black OR red – your odds of winning are less than 50/50.
 *  This is where the casino gets their advantage.
 */

@Getter
public class RedBlackRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.RED_BLACK.name();
    private static final int payout = 1;
    private final String bet;

    public RedBlackRouletteBet(double amount, long betId, String bet) {
        super(amount, betId);
        this.bet = bet;
    }

    protected boolean isWinningBet(int roll) throws IncorrectBetTypeException {
        if (roll == 0) {
            return false;
        }

        return switch (bet.toUpperCase()) {
            case "RED" -> RED.contains(roll);
            case "BLACK" -> BLACK.contains(roll);
            default -> throw new IncorrectBetTypeException("Bet type not recognised for RedBlackRouletteBet");
        };
    }

    private static final Set<Integer> RED = Set.of(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
    private static final Set<Integer> BLACK = Set.of(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);

}
