package com.gaming_platform.core.validators;

import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.exceptions.InvalidPlayerException;

public abstract class MultiBetPlayerCommandValidationService implements IMultiBetPlayerCommandValidatable {

    @Override
    public void validate(CreateMultiBetPlayerCommand command) throws InvalidPlayerException {
        validateFields(command);
        validateBetCommands(command);
    }

    private void validateFields(CreateMultiBetPlayerCommand command) throws InvalidPlayerException {
        if (command.getPlayerId() == null) {
            throw new InvalidPlayerException("Invalid Player Id");
        }

        if (command.getCreateBetCommands() == null || command.getCreateBetCommands().isEmpty()) {
            throw new InvalidPlayerException("Player's bets missing for player id: " + command.getPlayerId());
        }
    }

    protected abstract void validateBetCommands(CreateMultiBetPlayerCommand command);

}
