package com.gaming_platform.games.single_player.single_bet.higher_or_lower.converter;

import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.converters.single_player.ISinglePlayerSingleBetGameConverter;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.ISinglePlayerSingleBetPlayable;
import com.gaming_platform.games.single_player.single_bet.higher_or_lower.HigherOrLower;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommandToHigherOrLowerGameConverter implements ISinglePlayerSingleBetGameConverter {

    private static final String BET = "Bet";
    private static final String PLAYERS_NUMBER = "playersNumber";

    @Override
    public boolean canConvert(Game game) {
        return game == Game.HIGHER_OR_LOWER;
    }

    @Override
    public ISinglePlayerSingleBetPlayable build(CreateSinglePlayerSingleBetGameCommand command) throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        validateGameVariables(command);
        Map<String, Object> mapOfGameVariables = command.getGameVariables();
        return new HigherOrLower((double) mapOfGameVariables.get(BET), (int) mapOfGameVariables.get(PLAYERS_NUMBER), command.getPlayerId());
    }

    @Override
    public void validateGameVariables(CreateSinglePlayerSingleBetGameCommand command) throws ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        Map<String, Object> mapOfGameVariables = command.getGameVariables();
        validateBet(mapOfGameVariables);
        validatePlayersNumber(mapOfGameVariables);
        validatePlayersId(command.getPlayerId());
    }

    private static void validateBet(Map<String, Object> variables) throws InvalidFieldException, ValueOutOfBoundsException {
        if (!variables.containsKey(BET) || !(variables.get(BET) instanceof Number)) {
            throw new InvalidFieldException("Incorrect field name or value for Bet");
        }

        Double bet = (Double) variables.get(BET);
        if (bet <= 0.0) {
            throw new ValueOutOfBoundsException("Value of Bet must be larger than 0");
        }
    }

    private static void validatePlayersNumber(Map<String, Object> variables) throws InvalidFieldException, ValueOutOfBoundsException {
        if (!(variables.containsKey(PLAYERS_NUMBER)) || !(variables.get(PLAYERS_NUMBER) instanceof Number)) {
            throw new InvalidFieldException("Incorrect field name or value for playersNumber");
        }

        Integer playersNumber = (Integer) variables.get(PLAYERS_NUMBER);
        if (playersNumber < 1 || playersNumber > 99) {
            throw new ValueOutOfBoundsException("Value of MultiBetPlayer's number must be between 0 and 100");
        }
    }

    private static void validatePlayersId(Long playersId) throws InvalidFieldException {
        if (null == playersId) {
            throw new InvalidFieldException("PlayersId is null");
        }
    }
}