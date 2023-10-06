package com.gaming_platform.games.multi_player_multi_bet.roulette.validator.rouletteBet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.core.validators.BetCommandValidatorService;
import com.gaming_platform.exceptions.InvalidFieldException;

import java.util.List;

public class CornerRouletteBetCommandValidator extends BetCommandValidatorService {

    @Override
    protected void validateBetField(CreateBetCommand createBetCommand) throws InvalidFieldException {
        if (!(createBetCommand.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for corner roulette Bet");
        }
        List<Integer> cornerBet = (List<Integer>)createBetCommand.getBet();
        if (cornerBet.size() != 4) {
            throw new InvalidFieldException("incorrect number of elements for corner roulette Bet");
        }
    }
}
