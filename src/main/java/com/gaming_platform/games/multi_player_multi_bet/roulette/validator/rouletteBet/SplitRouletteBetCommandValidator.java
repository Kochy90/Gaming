package com.gaming_platform.games.multi_player_multi_bet.roulette.validator.rouletteBet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.core.validators.BetCommandValidatorService;
import com.gaming_platform.exceptions.InvalidFieldException;

import java.util.List;

public class SplitRouletteBetCommandValidator extends BetCommandValidatorService {

    @Override
    protected void validateBetField(CreateBetCommand createBetCommand) throws InvalidFieldException {
        if (!(createBetCommand.getBet() instanceof List)) {
            throw new InvalidFieldException("Incorrect object type for split roulette Bet");
        }
        List<Integer> splitBet = (List<Integer>) createBetCommand.getBet();
        if (splitBet.size() != 2) {
            throw new InvalidFieldException("Incorrect number of elements for split roulette Bet");
        }
    }
}
