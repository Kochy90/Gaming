package com.gaming_platform.games.multi_player.multi_bet.roulette.model;

import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.RouletteBet;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RoulettePlayer {
    private final Long playerId;
    private final List<RouletteBet> bets;
}
