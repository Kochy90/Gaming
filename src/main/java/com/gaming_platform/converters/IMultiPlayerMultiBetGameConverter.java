package com.gaming_platform.converters;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.IMultiPlayerMultiBetPlayable;

public interface IMultiPlayerMultiBetGameConverter {

    boolean canConvert(Game game);
    IMultiPlayerMultiBetPlayable build(CreateMultiPlayerMultiBetGameCommand command) throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException;
}
