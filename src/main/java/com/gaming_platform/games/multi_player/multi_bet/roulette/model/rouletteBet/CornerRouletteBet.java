package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Corner
 * This Bet is similar to the split Bet. But instead of two numbers, you’re betting that one of four numbers will come up.
 * These numbers also need to touch (to form a square)
 * These are also called four-number or square bets. They pay 8:1.
 */

@Getter
public class CornerRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.CORNER.name();
    private final int payout = 8;
    private final List<Integer> bet;

    @Builder
    public CornerRouletteBet(long betId, long playerId, long gameId, double amount, List<Integer> bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    @Override
    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }
}