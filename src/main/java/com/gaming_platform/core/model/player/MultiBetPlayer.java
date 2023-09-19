package com.gaming_platform.core.model.player;

import com.gaming_platform.core.model.bet.Bet;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MultiBetPlayer<T extends Bet> {
    private final Long playerId;
    private final List<T> bets;
}
