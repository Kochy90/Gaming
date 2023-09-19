package com.gaming_platform.games.multi_player_multi_bet.roulette.model;

import com.gaming_platform.core.model.game.MultiPlayerMultiBetGame;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBet;
import lombok.Getter;

@Getter
public class RouletteGame extends MultiPlayerMultiBetGame<RoulettePlayer, RouletteBet> {

    protected final Integer roll;

    public RouletteGame() {
        this.roll = generateIntegerBetween0And36();
    }

    private int generateIntegerBetween0And36() {
        return RANDOM.nextInt(0, 37);
    }

}
