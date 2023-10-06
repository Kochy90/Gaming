package com.gaming_platform.games.multi_player_multi_bet.roulette.validator.rouletteBet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.core.validators.BetCommandValidatorService;
import com.gaming_platform.exceptions.InvalidFieldException;

public class StraightRouletteBetCommandValidator extends BetCommandValidatorService {

    @Override
    protected void validateBetField(CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof Integer)) {
            throw new InvalidFieldException("Incorrect object type for Straight roulette Bet, id: " + command.getId());
        }
    }

}
