package com.gaming_platform.core.converters.single_player;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import com.gaming_platform.exceptions.InvalidPlayerException;

public abstract class CommandToSingleBetPlayerConverter<T extends SingleBetPlayer<S>, S extends Bet> implements ISinglePlayerConverter<S> {

    @Override
    public T convertCommandToSingleBetPlayer(Long gameId, CreateSingleBetPlayerCommand playerCommand) throws InvalidPlayerException {
        validatePlayerCommand(playerCommand);
        S bet = generateBet(gameId, playerCommand.getPlayerId(), playerCommand.getCreateBetCommand());
        return instantiateSingleBetPlayer(playerCommand.getPlayerId(), bet);
    }

    protected abstract void validatePlayerCommand(CreateSingleBetPlayerCommand playerCommand) throws InvalidPlayerException;

    protected abstract S generateBet(Long gameId, Long playerId, CreateBetCommand createBetCommand);

    protected abstract T instantiateSingleBetPlayer(Long playerId, S bet);
}
