package com.gaming_platform.converters;

import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.IPlayable;

import java.util.Map;

public interface IGameConverter {

    boolean canConvert(Game game);

    void validateGameVariables(Map<String, Object> mapOfGameVariables) throws ValueOutOfBoundsException, InvalidFieldException;

    IPlayable build(Map<String, Object> mapOfGameVariables) throws ValueOutOfBoundsException, InvalidFieldException;
}
