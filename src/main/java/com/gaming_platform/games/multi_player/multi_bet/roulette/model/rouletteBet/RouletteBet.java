package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.BetResult;
import lombok.Getter;

@Getter
public abstract class RouletteBet extends Bet {

    public RouletteBet(long betId, long playerId, long gameId, double amount) {
        super(betId, playerId, gameId, amount);
    }

    @Override
    public BetResult getResult(int roll) throws IncorrectBetTypeException {
        if (isWinningBet(roll)) {
            return new BetResult(this.betId, amount * getPayout());
        } else {
            return new BetResult(this.betId, 0d);
        }
    }

    protected abstract boolean isWinningBet(int roll) throws IncorrectBetTypeException;

    protected abstract int getPayout();

}
