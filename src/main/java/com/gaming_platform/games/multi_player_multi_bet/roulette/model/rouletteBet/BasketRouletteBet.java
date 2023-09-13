package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * Basket
 * This is a Bet on the numbers 0-1-2-3. It’s also called a “first four” Bet.
 * This pays 6:1.
 */

@Getter
public class BasketRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.BASKET.name();
    private final int payout = 6;
    private final Set<Integer> bet = Set.of(0, 1, 2, 3);

    @Builder
    public BasketRouletteBet(long betId, long playerId, long gameId, double amount) {
        super(betId, playerId, gameId, amount);
    }

    @Override
    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }

}
