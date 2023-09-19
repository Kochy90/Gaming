package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.core.model.bet.Bet;
import lombok.Getter;

@Getter
public abstract class RouletteBet extends Bet {

    public RouletteBet(long betId, long playerId, long gameId, double amount) {
        super(betId, playerId, gameId, amount);
    }

}
