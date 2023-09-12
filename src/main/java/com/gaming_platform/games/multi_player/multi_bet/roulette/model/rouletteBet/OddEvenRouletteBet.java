package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import lombok.Builder;
import lombok.Getter;

/**
 *  You’re betting on whether an even or odd number is selected.
 *  This is marked as “even” and “odd” on the table.
 *
 *  This is an even money Bet, but because of the 0 and 00, you don’t have 50/50 odds of winning.
 */

@Getter
public class OddEvenRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.ODD_EVEN.name();
    private final int payout = 2;
    private final String bet;

    @Builder
    public OddEvenRouletteBet(long betId, long playerId, long gameId, double amount, String bet) {
        super(betId, playerId, gameId, amount);
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

