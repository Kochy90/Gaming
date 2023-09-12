package com.gaming_platform.games.multi_player.model;

import com.gaming_platform.games.multi_player.multi_bet.Bet;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MultiBetPlayer<T extends Bet> {
    private final Long playerId;
    private final List<T> bets;
}
