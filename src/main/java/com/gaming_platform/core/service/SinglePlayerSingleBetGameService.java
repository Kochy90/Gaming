package com.gaming_platform.core.service;

import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.converters.single_player.ISinglePlayerSingleBetGameConverter;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.model.game.SinglePlayerSingleBetGame;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SinglePlayerSingleBetGameService<T extends SinglePlayerSingleBetGame<S, U>, S extends SingleBetPlayer<U>, U extends Bet> {

    @Autowired
    private List<ISinglePlayerSingleBetGameConverter<T, S, U>> singlePlayerSingleBetGameConverters;

    @Autowired
    private List<ISinglePlayerSingleBetPlayable<T, S, U>> singlePlayerSingleBetPlayableServices;

    public SinglePlayerSingleBetGameResult playGame(CreateSinglePlayerSingleBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, InvalidPlayerException {
        GameType gameType = validateAndReturnGame(createGameCommand.getGameName());
        T singlePlayerGame = convertCommandToGame(gameType, createGameCommand);
        ISinglePlayerSingleBetPlayable<T, S, U> playableService = allocateGameService(gameType);

        return playableService.play(singlePlayerGame);
    }

    private T convertCommandToGame(GameType gameType, CreateSinglePlayerSingleBetGameCommand command)
            throws GameTypeNotConvertibleException, InvalidPlayerException {
        ISinglePlayerSingleBetGameConverter<T, S, U> converter = singlePlayerSingleBetGameConverters.stream()
                .filter(iSinglePlayerSingleBetGameConverter2 -> iSinglePlayerSingleBetGameConverter2.canConvert(gameType))
                .findFirst()
                .orElseThrow(GameTypeNotConvertibleException::new);
        return converter.convert(command);
    }

    private ISinglePlayerSingleBetPlayable<T, S, U> allocateGameService(GameType gameType) throws GameTypeNotConvertibleException {
        return singlePlayerSingleBetPlayableServices.stream()
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
