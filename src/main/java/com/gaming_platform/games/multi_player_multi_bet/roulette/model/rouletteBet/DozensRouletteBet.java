package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import lombok.Builder;
import lombok.Getter;

/**
 * This Bet covers a dozen numbers, too. But, in order:
 * <p>
 * 1st 12 – This covers the ball landing on numbers 1-12.
 * 2nd 12 – This covers the ball landing on numbers 13-24.
 * 3rd 12 – This covers the ball landing on numbers 25-36.
 * <p>
 * Each dozen pays 2:1, or double your Bet.
 */

@Getter
public class DozensRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.DOZENS.name();
    private final int payout = 2;
    private final String bet;

    @Builder
    public DozensRouletteBet(long betId, long playerId, long gameId, double amount, String bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    public boolean isWinningBet(int roll) throws IncorrectBetTypeException {
        if (roll == 0) {
            return false;
        }

        return switch (bet.toUpperCase()) {
            case "FIRST" -> roll <= 12;
            case "SECOND" -> roll >= 13 && roll <= 24;
            case "THIRD" -> roll >= 25;
            default -> throw new IncorrectBetTypeException("Bet type not recognised for DozensRouletteBet");
        };
    }

}
