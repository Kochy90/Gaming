package com.gaming_platform.service;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.converters.multi_player_multi_bet.IMultiPlayerMultiBetGameConverter;
import com.gaming_platform.converters.single_player.ISinglePlayerSingleBetGameConverter;
import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.IMultiPlayerMultiBetPlayable;
import com.gaming_platform.games.multi_player.model.MultiBetPlayer;
import com.gaming_platform.games.multi_player.model.MultiPlayerMultiBetGame;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet> {

    @Autowired
    private List<ISinglePlayerSingleBetGameConverter> singlePlayerSingleBetGameConverters;

    @Autowired
    private List<IMultiPlayerMultiBetGameConverter<T, S, U>> multiPlayerMultiBetGameConverters;

    @Autowired
    private List<IMultiPlayerMultiBetPlayable<T, S, U>> multiPlayerMultiBetPlayableServices;

    public MultiPlayerMultiBetGameResult playGame(CreateMultiPlayerMultiBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        Game game = validateAndReturnGame(createGameCommand.getGameName());
        T multiPlayerGame = convertCommandToGame(game, createGameCommand);
        IMultiPlayerMultiBetPlayable<T, S, U> playableService = allocateGameService(game);
        return playableService.play(multiPlayerGame);
    }

    private T convertCommandToGame(Game game, CreateMultiPlayerMultiBetGameCommand command)
            throws GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        IMultiPlayerMultiBetGameConverter<T, S, U> converter = multiPlayerMultiBetGameConverters.stream()
                .filter(iMultiPlayerMultiBetGameConverter -> iMultiPlayerMultiBetGameConverter.canConvert(game))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
        return converter.convert(command);
    }

    private IMultiPlayerMultiBetPlayable<T, S, U> allocateGameService(Game game) throws GameTypeNotConvertibleException {
        return multiPlayerMultiBetPlayableServices.stream()
                .filter(service -> service.canPlay(game))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
    }

//    public SinglePlayerSingleBetGameResult playGame(CreateSinglePlayerSingleBetGameCommand createGameCommand)
//            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
//        Game game = validateAndReturnGame(createGameCommand.getGameName());
//        return buildGame(game, createGameCommand).play();
//    }
//
//    private ISinglePlayerSingleBetPlayable buildGame(Game game, CreateSinglePlayerSingleBetGameCommand command)
//            throws GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidPlayerException, InvalidFieldException {
//        ISinglePlayerSingleBetGameConverter converter = singlePlayerSingleBetGameConverters.stream()
//                .filter(iSinglePlayerSingleBetGameConverter -> iSinglePlayerSingleBetGameConverter.canConvert(game))
//                .findFirst()
//                .orElseThrow(GameTypeNotConvertibleException::new);
//        return converter.build(command);
//    }

    private Game validateAndReturnGame(String gameName) throws NameNotFoundException {
        return Arrays.stream(Game.values())
                .filter(g -> g.name().equalsIgnoreCase(gameName))
                .findFirst()
                .orElseThrow(NameNotFoundException::new);
    }

}
