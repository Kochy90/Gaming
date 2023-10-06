package com.gaming_platform.core.validators;

import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.exceptions.InvalidPlayerException;

public interface IMultiBetPlayerCommandValidatable {

    void validate(CreateMultiBetPlayerCommand command) throws InvalidPlayerException;
}
