package com.gaming_platform.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.multi_player.model.MultiBetPlayer;
import com.gaming_platform.games.multi_player.model.MultiPlayerMultiBetGame;
import com.gaming_platform.games.multi_player.multi_bet.Bet;

public interface IMultiPlayerMultiBetGameConverter<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet> {

    boolean canConvert(Game game);

    T convert(CreateMultiPlayerMultiBetGameCommand command)
        throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException;
}
