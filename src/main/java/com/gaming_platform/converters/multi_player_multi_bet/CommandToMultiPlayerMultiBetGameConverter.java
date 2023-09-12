package com.gaming_platform.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.commands.CreatePlayerCommand;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player.model.MultiBetPlayer;
import com.gaming_platform.games.multi_player.model.MultiPlayerMultiBetGame;
import com.gaming_platform.games.multi_player.multi_bet.Bet;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandToMultiPlayerMultiBetGameConverter<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet>
        implements IMultiPlayerMultiBetGameConverter<T, S, U> {

    @Override
    public T convert(CreateMultiPlayerMultiBetGameCommand gameCommand) {
        List<S> players = new ArrayList<>();
        T multiPlayerMultiBetGame = instantiateMultiPlayerMultiBetGame();

        for (CreatePlayerCommand player : gameCommand.getCreatePlayerCommands()) {
            try {
                players.add(convertPlayerCommandToMultiBetPlayer(multiPlayerMultiBetGame.getGameId(), player));
            } catch (InvalidPlayerException e) {
                e.getMessage();
            }
        }
        multiPlayerMultiBetGame.setPlayers(players);
        return multiPlayerMultiBetGame;
    }

    protected abstract S convertPlayerCommandToMultiBetPlayer(Long gameId, CreatePlayerCommand createPlayerCommand) throws InvalidPlayerException;

    protected abstract T instantiateMultiPlayerMultiBetGame();

}
