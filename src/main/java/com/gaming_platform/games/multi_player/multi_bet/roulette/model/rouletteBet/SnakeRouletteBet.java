package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * Snake Bet
 * This is a unique Bet that covers the numbers 1, 5, 9, 12, 14, 16, 19, 23, 27, 30, 32, and 34.
 * This Bet gets its name from the snake-like pattern you get when you highlight these numbers.
 *
 * This pays 2:1.
 */

@Getter
public class SnakeRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.SNAKE.name();
    private final int payout = 2;
    private final Set<Integer> bet = Set.of(1, 5, 9, 12, 14, 16, 19, 23, 27, 30, 32, 34);

    @Builder
    public SnakeRouletteBet(long betId, long playerId, long gameId, double amount) {
        super(betId, playerId, gameId, amount);
    }

    @Override
    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }
}
