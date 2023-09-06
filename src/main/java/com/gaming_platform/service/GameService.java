package com.gaming_platform.service;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.converters.IMultiPlayerMultiBetGameConverter;
import com.gaming_platform.converters.ISinglePlayerSingleBetGameConverter;
import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.IMultiPlayerMultiBetPlayable;
import com.gaming_platform.games.ISinglePlayerSingleBetPlayable;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private List<ISinglePlayerSingleBetGameConverter> singlePlayerSingleBetGameConverters;

    @Autowired
    private List<IMultiPlayerMultiBetGameConverter> multiPlayerMultiBetGameConverters;

    public MultiPlayerMultiBetGameResult playGame(CreateMultiPlayerMultiBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        Game game = validateAndReturnGame(createGameCommand.getGameName());
        return buildGame(game, createGameCommand).play();
    }

    private IMultiPlayerMultiBetPlayable buildGame(Game game, CreateMultiPlayerMultiBetGameCommand command)
            throws GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        IMultiPlayerMultiBetGameConverter converter = multiPlayerMultiBetGameConverters.stream()
                .filter(iMultiPlayerMultiBetGameConverter -> iMultiPlayerMultiBetGameConverter.canConvert(game))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
        return converter.build(command);
    }

    public SinglePlayerSingleBetGameResult playGame(CreateSinglePlayerSingleBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        Game game = validateAndReturnGame(createGameCommand.getGameName());
        return buildGame(game, createGameCommand).play();
    }

    private ISinglePlayerSingleBetPlayable buildGame(Game game, CreateSinglePlayerSingleBetGameCommand command)
            throws GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidPlayerException, InvalidFieldException {
        ISinglePlayerSingleBetGameConverter converter = singlePlayerSingleBetGameConverters.stream()
                .filter(iSinglePlayerSingleBetGameConverter -> iSinglePlayerSingleBetGameConverter.canConvert(game))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
        return converter.build(command);
    }

    private Game validateAndReturnGame(String gameName) throws NameNotFoundException {
        return Arrays.stream(Game.values())
                .filter(g -> g.name().equalsIgnoreCase(gameName))
                .findFirst()
                .orElseThrow(NameNotFoundException::new);
    }

}
