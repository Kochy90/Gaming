package com.gamingPlatform.service;

import com.gamingPlatform.games.CommandToHigherOrLowerGameConverter;
import com.gamingPlatform.games.IPlayable;
import com.gamingPlatform.games.HigherOrLower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class GameService {

    @Autowired
    CommandToHigherOrLowerGameConverter converter;

    public Float playGame(CreateGameCommand createGameCommand) {
        IPlayable game = buildGame(createGameCommand);
        return game.play();
    }

    private IPlayable buildGame(CreateGameCommand createGameCommand) {
        if (HigherOrLower.name.equalsIgnoreCase(createGameCommand.getName())) {
            return converter.buildHigherOrLowerGame(createGameCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game type not found");
        }
    }

}
