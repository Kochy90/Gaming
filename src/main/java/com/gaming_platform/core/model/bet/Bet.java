package com.gaming_platform.core.model.bet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Bet {
    protected final long betId;
    protected final long playerId;
    protected final long gameId;
    protected final double amount;

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
