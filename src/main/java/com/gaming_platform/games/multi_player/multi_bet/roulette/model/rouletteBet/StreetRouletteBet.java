package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import lombok.Getter;

import java.util.List;

/**
 * Street
 * This is a bet on any number within a row of three numbers. For example:
 *
 * | 1, 2, 3 | 4, 5, 6 | 7, 8, 9 | … | 34, 35, 36 |
 * If the ball lands on any number within the trio, you’re paid 11:1.
 * These bets are also called trio or steam bets.
 */

@Getter
public class StreetRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.STREET.name();
    private static final int payout = 11;
    private final List<Integer> bet;

    public StreetRouletteBet(double amount, long betId, List<Integer> bet) {
        super(amount, betId);
        this.bet = bet;
    }

    @Override
    protected boolean isWinningBet(int roll) throws IncorrectBetTypeException {
        return bet.contains(roll);
    }
}
