package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import lombok.Getter;

import java.util.List;

/**
 * Corner
 * This bet is similar to the split bet. But instead of two numbers, you’re betting that one of four numbers will come up.
 * These numbers also need to touch (to form a square)
 * These are also called four-number or square bets. They pay 8:1.
 */

@Getter
public class CornerRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.CORNER.name();
    private static final int payout = 8;
    private final List<Integer> bet;

    public CornerRouletteBet(double amount, long betId, List<Integer> bet) {
        super(amount, betId);
        this.bet = bet;
    }

    @Override
    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }
}