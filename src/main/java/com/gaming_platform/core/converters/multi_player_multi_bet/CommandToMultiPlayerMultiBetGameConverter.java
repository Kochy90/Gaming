package com.gaming_platform.core.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.MultiPlayerMultiBetGame;
import com.gaming_platform.core.model.player.MultiBetPlayer;
import com.gaming_platform.exceptions.InvalidPlayerException;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandToMultiPlayerMultiBetGameConverter<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet>
        implements IMultiPlayerMultiBetGameConverter<T, S, U> {

    @Override
    public T convert(CreateMultiPlayerMultiBetGameCommand gameCommand) {
        List<S> players = new ArrayList<>();
        T multiPlayerMultiBetGame = instantiateMultiPlayerMultiBetGame();

        for (CreateMultiBetPlayerCommand player : gameCommand.getCreateMultiBetPlayerCommands()) {
            try {
                players.add(convertPlayerCommandToMultiBetPlayer(multiPlayerMultiBetGame.getGameId(), player));
            } catch (InvalidPlayerException e) {
                e.getMessage();
            }
        }
        multiPlayerMultiBetGame.setPlayers(players);
        return multiPlayerMultiBetGame;
    }

    protected abstract S convertPlayerCommandToMultiBetPlayer(Long gameId, CreateMultiBetPlayerCommand createMultiBetPlayerCommand) throws InvalidPlayerException;

    protected abstract T instantiateMultiPlayerMultiBetGame();

}
