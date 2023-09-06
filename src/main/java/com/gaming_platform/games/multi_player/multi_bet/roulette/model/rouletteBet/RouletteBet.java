package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class RouletteBet {
    private final double amount;
    protected static int payout;
    private final long betId;

    public BetResult getResult(int roll) throws IncorrectBetTypeException {
        if (isWinningBet(roll)) {
            return new BetResult(this.betId, amount * payout);
        } else {
            return new BetResult(this.betId, 0d);
        }
    }

    protected abstract boolean isWinningBet(int roll) throws IncorrectBetTypeException;

}
