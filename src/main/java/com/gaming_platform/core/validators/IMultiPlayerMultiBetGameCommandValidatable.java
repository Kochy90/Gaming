package com.gaming_platform.core.validators;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.core.model.game.GameType;

public interface IMultiPlayerMultiBetGameCommandValidatable {

    void validate(CreateMultiPlayerMultiBetGameCommand command);

    boolean canValidate(GameType gameType);
}
