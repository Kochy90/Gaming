package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import lombok.Getter;

import java.util.Set;

/**
 * Basket
 * This is a bet on the numbers 0-1-2-3. It’s also called a “first four” bet.
 * This pays 6:1.
 */

@Getter
public class BasketRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.BASKET.name();
    private static final int payout = 6;
    private final Set<Integer> bet = Set.of(0, 1, 2, 3);

    public BasketRouletteBet(double amount, long betId) {
        super(amount, betId);
    }

    @Override
    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }
}
