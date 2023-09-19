package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Line
 * These are similar to streets bets, but instead of covering one row of three numbers, you cover two rows of three numbers.
 * For example:
 * | 1-6 | 4-9 | 7-12 | 13-18 | 31-36 |
 * The numbers need to be on two consecutive rows. These are also called six-number bets. The payoff is 5:1.
 */

@Getter
public class LineRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.LINE.name();
    private final int payout = 5;
    private final List<Integer> bet;

    @Builder
    public LineRouletteBet(long betId, long playerId, long gameId, double amount, List<Integer> bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    @Override
    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }
}
