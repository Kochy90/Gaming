package com.gaming_platform.core.converters.single_player;

import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.SinglePlayerSingleBetGame;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import com.gaming_platform.exceptions.InvalidPlayerException;

public abstract class CommandToSinglePlayerSingleBetGameConverter<T extends SinglePlayerSingleBetGame<S, U>, S extends SingleBetPlayer<U>, U extends Bet>
        implements ISinglePlayerSingleBetGameConverter<T, S, U> {

    @Override
    public T convert(CreateSinglePlayerSingleBetGameCommand gameCommand) throws InvalidPlayerException {
        T singlePlayerSingleBetGame = instantiateMultiPlayerMultiBetGame();
        S player = convertPlayerCommandToSingleBetPlayer(singlePlayerSingleBetGame.getGameId(), gameCommand.getCreateSingleBetPlayerCommand());
        singlePlayerSingleBetGame.setPlayer(player);

        return singlePlayerSingleBetGame;
    }

    protected abstract S convertPlayerCommandToSingleBetPlayer(Long gameId, CreateSingleBetPlayerCommand createMultiBetPlayerCommand) throws InvalidPlayerException;

    protected abstract T instantiateMultiPlayerMultiBetGame();

}
