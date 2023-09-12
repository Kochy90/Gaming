package com.gaming_platform.converters.single_player;

import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.ISinglePlayerSingleBetPlayable;

public interface ISinglePlayerSingleBetGameConverter {

    boolean canConvert(Game game);

    void validateGameVariables(CreateSinglePlayerSingleBetGameCommand command) throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException;

    ISinglePlayerSingleBetPlayable build(CreateSinglePlayerSingleBetGameCommand command) throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException;
}
