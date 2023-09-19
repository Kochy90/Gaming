package com.gaming_platform.core.service;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.core.converters.multi_player_multi_bet.IMultiPlayerMultiBetGameConverter;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.model.game.MultiPlayerMultiBetGame;
import com.gaming_platform.core.model.player.MultiBetPlayer;
import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MultiPlayerMultiBetGameService<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet> {

    @Autowired
    private List<IMultiPlayerMultiBetGameConverter<T, S, U>> multiPlayerMultiBetGameConverters;

    @Autowired
    private List<IMultiPlayerMultiBetPlayable<T, S, U>> multiPlayerMultiBetPlayableServices;

    public MultiPlayerMultiBetGameResult playGame(CreateMultiPlayerMultiBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        GameType gameType = validateAndReturnGame(createGameCommand.getGameType());
        T multiPlayerGame = convertCommandToGame(gameType, createGameCommand);
        IMultiPlayerMultiBetPlayable<T, S, U> playableService = allocateGameService(gameType);
        return playableService.play(multiPlayerGame);
    }

    private T convertCommandToGame(GameType gameType, CreateMultiPlayerMultiBetGameCommand command)
            throws GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        IMultiPlayerMultiBetGameConverter<T, S, U> converter = multiPlayerMultiBetGameConverters.stream()
                .filter(iMultiPlayerMultiBetGameConverter -> iMultiPlayerMultiBetGameConverter.canConvert(gameType))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
        return converter.convert(command);
    }

    private IMultiPlayerMultiBetPlayable<T, S, U> allocateGameService(GameType gameType) throws GameTypeNotConvertibleException {
        return multiPlayerMultiBetPlayableServices.stream()
                .filter(service -> service.canPlay(gameType))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
    }

    private GameType validateAndReturnGame(String gameName) throws NameNotFoundException {
        return Arrays.stream(GameType.values())
                .filter(g -> g.name().equalsIgnoreCase(gameName))
                .findFirst()
                .orElseThrow(NameNotFoundException::new);
    }

}
