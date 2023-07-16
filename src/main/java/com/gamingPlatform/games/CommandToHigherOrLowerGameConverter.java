package com.gamingPlatform.games;

import com.gamingPlatform.service.CreateGameCommand;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component
public class CommandToHigherOrLowerGameConverter {

    public IPlayable buildHigherOrLowerGame(CreateGameCommand createGameCommand) {
        Map<String, Object> gameVariables = createGameCommand.getGameVariables();
        Float bet = validateAndReturnBet(gameVariables);
        Integer playersNumber = validateAndReturnPlayersNumber(gameVariables);

        return new HigherOrLower(bet, playersNumber);
    }

    private Float validateAndReturnBet(Map<String, Object> variables) {
        Float bet;
        if (variables.containsKey("bet") && variables.get("bet") instanceof Float) {
            bet = (Float) variables.get("bet");
            if (bet <= 0f) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value of bet must be larger than 0");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect field name or value for bet");
        }
        return bet;
    }

    private Integer validateAndReturnPlayersNumber(Map<String, Object> variables) {
        Integer playersNumber;
        if (variables.containsKey("playersNumber") && variables.get("playersNumber") instanceof Integer) {
            playersNumber = (Integer) variables.get("playersNumber");
            if (playersNumber < 1 || playersNumber > 100) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value of player's number must be between 0 and 101");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect field name or value for playersNumber");
        }
        return playersNumber;
    }

}
