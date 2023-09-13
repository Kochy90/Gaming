package com.gaming_platform.core.converters.single_player;

import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.model.game.SinglePlayerSingleBetGame;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import com.gaming_platform.exceptions.InvalidPlayerException;

public interface ISinglePlayerSingleBetGameConverter<T extends SinglePlayerSingleBetGame<S, U>, S extends SingleBetPlayer<U>, U extends Bet> {

    boolean canConvert(GameType gameType);

    T convert(CreateSinglePlayerSingleBetGameCommand command) throws InvalidPlayerException;
}
