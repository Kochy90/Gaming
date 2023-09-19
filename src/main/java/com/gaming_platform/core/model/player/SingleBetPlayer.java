package com.gaming_platform.core.model.player;

import com.gaming_platform.core.model.bet.Bet;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class SingleBetPlayer<T extends Bet> {
    private final Long playerId;
    private final T bet;
}
