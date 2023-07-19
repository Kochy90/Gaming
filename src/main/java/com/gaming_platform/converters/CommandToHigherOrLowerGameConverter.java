package com.gaming_platform.converters;

import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.HigherOrLower;
import com.gaming_platform.games.IPlayable;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommandToHigherOrLowerGameConverter implements IGameConverter {

    private static final String BET = "bet";
    private static final String PLAYERS_NUMBER = "playersNumber";

    @Override
    public IPlayable build(Map<String, Object> mapOfGameVariables) throws ValueOutOfBoundsException, InvalidFieldException {
        validateGameVariables(mapOfGameVariables);
        return new HigherOrLower((double) mapOfGameVariables.get(BET), (int) mapOfGameVariables.get(PLAYERS_NUMBER));
    }

    @Override
    public void validateGameVariables(Map<String, Object> mapOfGameVariables) throws ValueOutOfBoundsException, InvalidFieldException {
        validateBet(mapOfGameVariables);
        validatePlayersNumber(mapOfGameVariables);
    }

    @Override
    public boolean canConvert(Game game) {
        return game == Game.HIGHER_OR_LOWER;
    }

    private static void validateBet(Map<String, Object> variables) throws InvalidFieldException, ValueOutOfBoundsException {
        if (!variables.containsKey(BET) || !(variables.get(BET) instanceof Number)) {
            throw new InvalidFieldException("Incorrect field name or value for bet");
        }

        Double bet = (Double) variables.get(BET);
        if (bet <= 0.0) {
            throw new ValueOutOfBoundsException("Value of bet must be larger than 0");
        }
    }

    private static void validatePlayersNumber(Map<String, Object> variables) throws InvalidFieldException, ValueOutOfBoundsException {
        if (!(variables.containsKey(PLAYERS_NUMBER)) || !(variables.get(PLAYERS_NUMBER) instanceof Number)) {
            throw new InvalidFieldException("Incorrect field name or value for playersNumber");
        }

        Integer playersNumber = (Integer) variables.get(PLAYERS_NUMBER);
        if (playersNumber < 1 || playersNumber > 99) {
            throw new ValueOutOfBoundsException("Value of player's number must be between 0 and 100");
        }
    }
}