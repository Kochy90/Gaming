package com.gaming_platform.core.validators;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.exceptions.InvalidPlayerException;

public abstract class MultiPlayerMultiBetGameCommandValidationService<T extends MultiBetPlayerCommandValidationService>
        implements IMultiPlayerMultiBetGameCommandValidatable {

    public void validate(CreateMultiPlayerMultiBetGameCommand command) {
        T playerValidationService = instantiatePlayerCommandValidationService();

        command.getCreateMultiBetPlayerCommands().forEach(p -> {
            try {
                playerValidationService.validate(p);
            } catch (InvalidPlayerException e) {
                e.printStackTrace();
            }
        });
    }

    protected abstract T instantiatePlayerCommandValidationService();

}
