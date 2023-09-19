package com.gaming_platform.games.single_player_single_bet.higher_or_lower.model;

import com.gaming_platform.core.model.bet.Bet;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HigherOrLowerBet extends Bet {

    private final int bet;

    @Builder
    public HigherOrLowerBet(long betId, long playerId, long gameId, double amount, int bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    protected boolean isWinningBet(int roll) {
        return bet > roll;
    }

    protected int getPayout() {
        return 99 / (100 - this.bet);
    }

}
