package com.gaming_platform.games;

import com.gaming_platform.games.multi_player.model.MultiBetPlayer;
import com.gaming_platform.games.multi_player.model.MultiPlayerMultiBetGame;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;

public interface IMultiPlayerMultiBetPlayable<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet> {

    boolean canPlay(Game game);
    MultiPlayerMultiBetGameResult play(T game);
}
