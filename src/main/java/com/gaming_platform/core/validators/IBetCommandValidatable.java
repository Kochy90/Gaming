package com.gaming_platform.core.validators;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.exceptions.InvalidFieldException;

public interface IBetCommandValidatable {

    void validate(CreateBetCommand command) throws InvalidFieldException;
}
