package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import lombok.Getter;

/**
 *  You’re betting on whether the number selected will fall within 1-18 (low) or 19-36 (high).
 *  Both are even money bets.
 */

@Getter
public class HighLowRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.HIGH_LOW.name();
    private static final int payout = 1;
    private final String bet;

    public HighLowRouletteBet(double amount, long betId, String bet) {
        super(amount, betId);
        this.bet = bet;
    }

    @Override
    protected boolean isWinningBet(int roll) throws IncorrectBetTypeException {
        if (roll == 0) {
            return false;
        }

        return switch (bet.toUpperCase()) {
            case "HIGH" -> roll > 18;
            case "LOW" -> roll < 19;
            default -> throw new IncorrectBetTypeException("Bet type not recognised for HighLowRouletteBet");
        };
    }

}
