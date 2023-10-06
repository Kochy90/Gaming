package com.gaming_platform.core.validators;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.exceptions.InvalidFieldException;

public abstract class BetCommandValidatorService implements IBetCommandValidatable{

    public void validate(CreateBetCommand command) throws InvalidFieldException {
        if (command.getBetName() == null) {
            throw new InvalidFieldException("BetName missing from roulette Bet command");
        }

        if (command.getId() == null) {
            throw new InvalidFieldException("BetId missing from " + command.getBetName() + " roulette Bet command");
        }

        if (command.getAmount() == null) {
            throw new InvalidFieldException("Bet Amount missing from " + command.getBetName() + " roulette Bet command");
        }

        if (command.getAmount() <= 0L) {
            throw new InvalidFieldException("Bet Amount must be larger than 0 for " + command.getBetName() + " roulette Bet command");
        }

        validateBetField(command);
    }

    protected abstract void validateBetField(CreateBetCommand createBetCommand) throws InvalidFieldException;
}
