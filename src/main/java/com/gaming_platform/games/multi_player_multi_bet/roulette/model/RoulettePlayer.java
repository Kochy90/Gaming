package com.gaming_platform.games.multi_player_multi_bet.roulette.model;

import com.gaming_platform.core.model.player.MultiBetPlayer;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBet;
import lombok.Getter;

import java.util.List;

@Getter
public class RoulettePlayer extends MultiBetPlayer<RouletteBet> {

    public RoulettePlayer(Long playerId, List<RouletteBet> bets) {
        super(playerId, bets);
    }
}
