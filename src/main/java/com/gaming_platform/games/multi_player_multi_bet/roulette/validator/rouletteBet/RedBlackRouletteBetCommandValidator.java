package com.gaming_platform.games.multi_player_multi_bet.roulette.validator.rouletteBet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.core.validators.BetCommandValidatorService;
import com.gaming_platform.exceptions.InvalidFieldException;

public class RedBlackRouletteBetCommandValidator extends BetCommandValidatorService {

    @Override
    protected void validateBetField(CreateBetCommand createBetCommand) throws InvalidFieldException {
        if (!(createBetCommand.getBet() instanceof String)) {
            throw new InvalidFieldException("Incorrect object type for red/black roulette Bet");
        }
    }
}
