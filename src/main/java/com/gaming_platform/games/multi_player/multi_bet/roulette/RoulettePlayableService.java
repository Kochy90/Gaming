package com.gaming_platform.games.multi_player.multi_bet.roulette;

import com.gaming_platform.games.Game;
import com.gaming_platform.games.multi_player.MultiPlayerMultiBetPlayableService;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.Roulette;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.RouletteBet;
import org.springframework.stereotype.Service;

@Service
public class RoulettePlayableService extends MultiPlayerMultiBetPlayableService<Roulette, RoulettePlayer, RouletteBet> {

    @Override
    public boolean canPlay(Game game) {
        return game == Game.ROULETTE;
    }

}
