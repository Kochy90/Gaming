package com.gaming_platform.games.multi_player.multi_bet;

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

    public abstract BetResult getResult(int roll) throws IncorrectBetTypeException;
}
