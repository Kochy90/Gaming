package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StraightRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.STRAIGHT.name();
    private final int payout = 35;
    private final int bet;

    @Builder
    public StraightRouletteBet(long betId, long playerId, long gameId, double amount, int bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    protected boolean isWinningBet(int roll) {
        return roll == bet;
    }
}
