package com.gaming_platform.games.multi_player.multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreatePlayerCommand;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player.multi_bet.roulette.RouletteBetFactory;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.RouletteBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandToRoulettePlayerConverter {

    @Autowired
    RouletteBetFactory rouletteBetFactory;

    public RoulettePlayer convertPlayerCommandToRoulettePlayer(CreatePlayerCommand playerCommand) throws InvalidPlayerException {
        validatePlayerCommand(playerCommand);
        List<RouletteBet> rouletteBets = playerCommand.getCreateBetCommands().stream()
                .map(this::generateRouletteBet)
                .collect(Collectors.toList());
        return new RoulettePlayer(playerCommand.getPlayerId(), rouletteBets);
    }

    private RouletteBet generateRouletteBet(CreateBetCommand createBetCommand) {
        try {
            return rouletteBetFactory.buildRouletteBet(createBetCommand);
        } catch (IncorrectBetTypeException | InvalidFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void validatePlayerCommand(CreatePlayerCommand playerCommand) throws InvalidPlayerException {
        if (playerCommand.getPlayerId() == null) {
            throw new InvalidPlayerException("Invalid Player Id");
        }

        if (playerCommand.getCreateBetCommands() == null || playerCommand.getCreateBetCommands().isEmpty()) {
            throw new InvalidPlayerException("Player's bets missing'");
        }
    }

}

