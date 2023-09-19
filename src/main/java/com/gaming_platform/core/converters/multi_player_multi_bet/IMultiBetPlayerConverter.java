package com.gaming_platform.core.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.player.MultiBetPlayer;
import com.gaming_platform.exceptions.InvalidPlayerException;

public interface IMultiBetPlayerConverter<T extends MultiBetPlayer<S>, S extends Bet> {

    T convertCommandToMultiBetPlayer(Long gameId, CreateMultiBetPlayerCommand playerCommand) throws InvalidPlayerException;
}
