package com.gaming_platform.converters;

import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.ISinglePlayerSingleBetPlayable;

import java.util.Map;

public interface IGameConverter {

    boolean canConvert(Game game);

    void validateGameVariables(Map<String, Object> mapOfGameVariables) throws ValueOutOfBoundsException, InvalidFieldException;
    // maybe converting and validating should/could be separated in order to follow SRP. Validation could be even a Util class

    ISinglePlayerSingleBetPlayable build(Map<String, Object> mapOfGameVariables) throws ValueOutOfBoundsException, InvalidFieldException;
    // Maybe you could narrow a bit down what are the parameters it can take, like using a general Map<String, GameParameter> or something, or even a List<GameParameter> where you'd have there key and value
}
