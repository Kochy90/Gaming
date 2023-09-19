package com.gaming_platform.games.multi_player_multi_bet.roulette;

import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.service.MultiPlayerMultiBetPlayableService;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RouletteGame;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBet;
import org.springframework.stereotype.Service;

@Service
public class RoulettePlayableService extends MultiPlayerMultiBetPlayableService<RouletteGame, RoulettePlayer, RouletteBet> {

    @Override
    public boolean canPlay(GameType gameType) {
        return gameType == GameType.ROULETTE;
    }

}
