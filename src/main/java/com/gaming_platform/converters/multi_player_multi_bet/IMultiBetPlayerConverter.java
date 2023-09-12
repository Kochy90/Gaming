package com.gaming_platform.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreatePlayerCommand;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player.model.MultiBetPlayer;
import com.gaming_platform.games.multi_player.multi_bet.Bet;

public interface IMultiBetPlayerConverter<T extends Bet> {

    MultiBetPlayer<T> convertCommandToMultiBetPlayer(Long gameId, CreatePlayerCommand playerCommand) throws InvalidPlayerException;
}
