package com.gaming_platform.games.multi_player_multi_bet.roulette.validator;

import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.validators.MultiPlayerMultiBetGameCommandValidationService;
import org.springframework.stereotype.Service;

@Service
public class RouletteGameCommandValidationService extends MultiPlayerMultiBetGameCommandValidationService<RoulettePlayerCommandValidatorService> {

    @Override
    public boolean canValidate(GameType gameType) {
        return GameType.ROULETTE == gameType;
    }

    @Override
    protected RoulettePlayerCommandValidatorService instantiatePlayerCommandValidationService() {
        return new RoulettePlayerCommandValidatorService();
    }

}
