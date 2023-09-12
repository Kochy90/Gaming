package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * Five-Number Bet
 * This is a wager on the group of numbers: 0, 00, 1, 2 and 3. These are the only numbers included in this Bet.
 * The house pays 6:1 if you win and has a 13.16% probability of winning.
 *
 * These odds make it one of the worst roulette bets you can make. This is also called a “top line” Bet.
 */

@Getter
public class FiveNumberRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.FIVE_NUMBER_BET.name();
    private final int payout = 6;
    private final Set<Integer> bet = Set.of(0, 1, 2, 3, 4);

    @Builder
    public FiveNumberRouletteBet(long betId, long playerId, long gameId, double amount) {
        super(betId, playerId, gameId, amount);
    }

    @Override
    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }
}