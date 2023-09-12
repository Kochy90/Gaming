package com.gaming_platform.games.multi_player.multi_bet.roulette.model;

import com.gaming_platform.games.multi_player.model.MultiPlayerMultiBetGame;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.RouletteBet;
import lombok.Getter;

import java.util.List;

@Getter
public class Roulette extends MultiPlayerMultiBetGame<RoulettePlayer, RouletteBet> {

    private final Integer roll;

    public Roulette(List<RoulettePlayer> players) {
        super(players);
        this.roll = generateIntegerBetween0And36();
    }

    public Roulette() {
        this.roll = generateIntegerBetween0And36();
    }

    private int generateIntegerBetween0And36() {
        return RANDOM.nextInt(0, 37);
    }

}
