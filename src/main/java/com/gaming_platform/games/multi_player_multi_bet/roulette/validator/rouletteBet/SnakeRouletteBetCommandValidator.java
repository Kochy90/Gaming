package com.gaming_platform.games.multi_player_multi_bet.roulette.validator.rouletteBet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.core.validators.BetCommandValidatorService;
import com.gaming_platform.exceptions.InvalidFieldException;

public class SnakeRouletteBetCommandValidator extends BetCommandValidatorService {
    @Override
    protected void validateBetField(CreateBetCommand createBetCommand) throws InvalidFieldException {
        // No Validation Required on field as bet values are fixed for this bet type
    }
}
