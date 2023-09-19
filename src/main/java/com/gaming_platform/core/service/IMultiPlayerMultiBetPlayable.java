package com.gaming_platform.core.service;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.model.game.MultiPlayerMultiBetGame;
import com.gaming_platform.core.model.player.MultiBetPlayer;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;

public interface IMultiPlayerMultiBetPlayable<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet> {

    boolean canPlay(GameType gameType);
    MultiPlayerMultiBetGameResult play(T game);
}
