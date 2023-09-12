package com.gaming_platform.converters.multi_player_multi_bet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreatePlayerCommand;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player.model.MultiBetPlayer;
import com.gaming_platform.games.multi_player.multi_bet.Bet;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CommandToMultiBetPlayerConverter<T extends MultiBetPlayer<S>, S extends Bet> implements IMultiBetPlayerConverter<S> {

    @Override
    public T convertCommandToMultiBetPlayer(Long gameId, CreatePlayerCommand playerCommand) throws InvalidPlayerException {
        validatePlayerCommand(playerCommand);
        List<S> bets = playerCommand.getCreateBetCommands().stream()
                .map((betCommand) -> generateBet(gameId, playerCommand.getPlayerId(), betCommand))
                .collect(Collectors.toList());
        return instantiateMultiBetPlayer(playerCommand.getPlayerId(), bets);
    }

    protected abstract void validatePlayerCommand(CreatePlayerCommand playerCommand) throws InvalidPlayerException;

    protected abstract S generateBet(Long gameId, Long playerId, CreateBetCommand createBetCommand);

    protected abstract T instantiateMultiBetPlayer(Long playerId, List<S> bets);

}
