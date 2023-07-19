package com.gaming_platform.service;

import com.gaming_platform.converters.IGameConverter;
import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.IPlayable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private List<IGameConverter> converters;

    public Double playGame(CreateGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException {
        Game game = Arrays.stream(Game.values())
                .filter(g -> g.name().equalsIgnoreCase(createGameCommand.getName()))
                .findFirst()
                .orElseThrow(NameNotFoundException::new);
        return buildGame(game, createGameCommand.getGameVariables()).play();
    }

    private IPlayable buildGame(Game game, Map<String, Object> mapOfGameVariables)
            throws GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException {
        IGameConverter converter = converters.stream()
                .filter(iGameConverter -> iGameConverter.canConvert(game))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
        return converter.build(mapOfGameVariables);
    }

}
