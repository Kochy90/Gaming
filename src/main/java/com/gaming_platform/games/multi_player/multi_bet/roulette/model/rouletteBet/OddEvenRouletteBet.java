package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RouletteBet;
import lombok.Getter;

/**
 *  You’re betting on whether an even or odd number is selected.
 *  This is marked as “even” and “odd” on the table.
 *
 *  This is an even money bet, but because of the 0 and 00, you don’t have 50/50 odds of winning.
 */

@Getter
public class OddEvenRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.ODD_EVEN.name();
    protected static final int payout = 1;
    private final String bet;

    public OddEvenRouletteBet(double amount, long betId, String bet) {
        super(amount, betId);
        this.bet = bet;
    }

    protected boolean isWinningBet(int roll) throws IncorrectBetTypeException {
        if (roll == 0) {
            return false;
        }

        return switch (bet.toUpperCase()) {
            case "ODD" -> roll % 2 != 0;
            case "EVEN" -> roll % 2 == 0;
            default -> throw new IncorrectBetTypeException("Bet type not recognised for OddEvenRouletteBet");
        };
    }

}

