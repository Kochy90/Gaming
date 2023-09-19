package com.gaming_platform.games.single_player_single_bet.higher_or_lower;

import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.service.SinglePlayerSingleBetPlayableService;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerGame;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerPlayer;
import org.springframework.stereotype.Service;

@Service
public class HigherOrLowerPlayableService extends SinglePlayerSingleBetPlayableService<HigherOrLowerGame, HigherOrLowerPlayer, HigherOrLowerBet> {

    @Override
    public boolean canPlay(GameType gameType) {
        return gameType == GameType.HIGHER_OR_LOWER;
    }
}
