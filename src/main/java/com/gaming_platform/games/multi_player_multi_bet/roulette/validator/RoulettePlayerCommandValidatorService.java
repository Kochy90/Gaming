package com.gaming_platform.games.multi_player_multi_bet.roulette.validator;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.core.validators.IBetCommandValidatable;
import com.gaming_platform.core.validators.MultiBetPlayerCommandValidationService;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.games.multi_player_multi_bet.roulette.validator.rouletteBet.RouletteBetCommandValidatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoulettePlayerCommandValidatorService extends MultiBetPlayerCommandValidationService {

    @Autowired
    RouletteBetCommandValidatorMapper betCommandValidatorMapper;

    IBetCommandValidatable validator;

    @Override
    protected void validateBetCommands(CreateMultiBetPlayerCommand command) {
        command.getCreateBetCommands().forEach(createBetCommand -> {
            try {
                validateRouletteBet(createBetCommand);
            } catch (InvalidFieldException e) {
                e.printStackTrace();
            }
        });
    }

    private void validateRouletteBet(CreateBetCommand command) throws InvalidFieldException {
            validator = betCommandValidatorMapper.assignValidator(command);
            validator.validate(command);
    }

}
