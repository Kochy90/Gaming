package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RouletteBet;

public class StraightRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.STRAIGHT.name();
    private static final int payout = 35;
    private final int bet;

    public StraightRouletteBet(double amount, long betId, int bet) {
        super(amount, betId);
        this.bet = bet;
    }

    protected boolean isWinningBet(int roll) {
        return roll == bet;
    }
}
