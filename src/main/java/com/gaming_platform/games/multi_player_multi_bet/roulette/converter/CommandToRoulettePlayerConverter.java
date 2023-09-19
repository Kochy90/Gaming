package com.gaming_platform.games.multi_player_multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.core.converters.multi_player_multi_bet.CommandToMultiBetPlayerConverter;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandToRoulettePlayerConverter extends CommandToMultiBetPlayerConverter<RoulettePlayer, RouletteBet> {

    @Autowired
    RouletteBetFactory rouletteBetFactory;

    protected void validatePlayerCommand(CreateMultiBetPlayerCommand playerCommand) throws InvalidPlayerException {
        if (playerCommand.getPlayerId() == null) {
            throw new InvalidPlayerException("Invalid Player Id");
        }

        if (playerCommand.getCreateBetCommands() == null || playerCommand.getCreateBetCommands().isEmpty()) {
            throw new InvalidPlayerException("Player's bets missing'");
        }
    }

    @Override
    protected RouletteBet generateBet(Long gameId, Long playerId, CreateBetCommand createBetCommand) {
        try {
            return rouletteBetFactory.buildRouletteBet(gameId, playerId, createBetCommand);
        } catch (InvalidFieldException e) {
            e.getStackTrace();
            return null;
        }
    }

    @Override
    protected RoulettePlayer instantiateMultiBetPlayer(Long playerId, List<RouletteBet> bets) {
        return new RoulettePlayer(playerId, bets);
    }

}

