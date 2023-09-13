package com.gaming_platform.core.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.model.game.MultiPlayerMultiBetGame;
import com.gaming_platform.core.model.player.MultiBetPlayer;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;

public interface IMultiPlayerMultiBetGameConverter<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet> {

    boolean canConvert(GameType gameType);

    T convert(CreateMultiPlayerMultiBetGameCommand command) throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException;
}
