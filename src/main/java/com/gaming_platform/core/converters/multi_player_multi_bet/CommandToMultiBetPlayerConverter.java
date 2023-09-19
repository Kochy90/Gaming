package com.gaming_platform.core.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.player.MultiBetPlayer;
import com.gaming_platform.exceptions.InvalidPlayerException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CommandToMultiBetPlayerConverter<T extends MultiBetPlayer<S>, S extends Bet> implements IMultiBetPlayerConverter<S> {

    @Override
    public T convertCommandToMultiBetPlayer(Long gameId, CreateMultiBetPlayerCommand playerCommand) throws InvalidPlayerException {
        validatePlayerCommand(playerCommand);
        List<S> bets = playerCommand.getCreateBetCommands().stream()
                .map((betCommand) -> generateBet(gameId, playerCommand.getPlayerId(), betCommand))
                .collect(Collectors.toList());
        return instantiateMultiBetPlayer(playerCommand.getPlayerId(), bets);
    }

    protected abstract void validatePlayerCommand(CreateMultiBetPlayerCommand playerCommand) throws InvalidPlayerException;

    protected abstract S generateBet(Long gameId, Long playerId, CreateBetCommand createBetCommand);

    protected abstract T instantiateMultiBetPlayer(Long playerId, List<S> bets);

}
