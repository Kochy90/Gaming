package com.gaming_platform.core.service;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.model.game.SinglePlayerSingleBetGame;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;

public interface ISinglePlayerSingleBetPlayable<T extends SinglePlayerSingleBetGame<S, U>, S extends SingleBetPlayer<U>, U extends Bet> {

    boolean canPlay(GameType gameType);
    SinglePlayerSingleBetGameResult play(T game);
}
