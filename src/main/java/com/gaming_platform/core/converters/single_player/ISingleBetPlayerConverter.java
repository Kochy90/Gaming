package com.gaming_platform.core.converters.single_player;

import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import com.gaming_platform.exceptions.InvalidPlayerException;

public interface ISingleBetPlayerConverter<T extends SingleBetPlayer> {

    T convertCommandToSingleBetPlayer(Long gameId, CreateSingleBetPlayerCommand playerCommand) throws InvalidPlayerException;
}
