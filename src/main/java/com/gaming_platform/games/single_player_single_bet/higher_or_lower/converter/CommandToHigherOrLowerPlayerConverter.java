package com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.core.converters.single_player.CommandToSingleBetBetPlayerConverter;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandToHigherOrLowerPlayerConverter extends CommandToSingleBetBetPlayerConverter<HigherOrLowerPlayer, HigherOrLowerBet> {

    @Autowired
    HigherOrLowerBetConverter higherOrLowerBetConverter;

    @Override
    protected void validatePlayerCommand(CreateSingleBetPlayerCommand playerCommand) throws InvalidPlayerException {
        if (playerCommand.getPlayerId() == null) {
            throw new InvalidPlayerException("Invalid Player Id");
        }

        if (playerCommand.getCreateBetCommand() == null) {
            throw new InvalidPlayerException("Player's Higher or Lower bet missing");
        }
    }

    @Override
    protected HigherOrLowerBet generateBet(Long gameId, Long playerId, CreateBetCommand createBetCommand) {
        try {
            return higherOrLowerBetConverter.convertCommandToHigherOrLowerBet(gameId, playerId, createBetCommand);
        } catch (InvalidFieldException | ValueOutOfBoundsException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    protected HigherOrLowerPlayer instantiateSingleBetPlayer(Long playerId, HigherOrLowerBet bet) {
        return new HigherOrLowerPlayer(playerId, bet);
    }
}
