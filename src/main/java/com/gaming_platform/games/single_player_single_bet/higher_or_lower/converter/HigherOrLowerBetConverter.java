package com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import org.springframework.stereotype.Component;

@Component
public class HigherOrLowerBetConverter {

    public HigherOrLowerBet convertCommandToHigherOrLowerBet(Long gameId, Long playerId, CreateBetCommand createBetCommand)
            throws ValueOutOfBoundsException, InvalidFieldException {
        validateIds(gameId, playerId);
        validateCreateBetCommand(createBetCommand);

        return HigherOrLowerBet.builder()
                .gameId(gameId)
                .playerId(playerId)
                .betId(createBetCommand.getId())
                .amount(createBetCommand.getAmount())
                .bet((Integer) createBetCommand.getBet())
                .build();
    }

    private void validateIds(Long gameId, Long playerId) throws InvalidFieldException {
        if (null == gameId) {
            throw new InvalidFieldException("GameId for Higher Or Lower bet is null");
        }
        if (null == playerId) {
            throw new InvalidFieldException("playerId for Higher Or Lower bet is null");
        }
    }

    private void validateCreateBetCommand(CreateBetCommand command) throws InvalidFieldException, ValueOutOfBoundsException {
        if (!(command.getBet() instanceof Integer)) {
            throw new InvalidFieldException("incorrect object type for Higher or Lower Bet");
        }
        Integer bet = (Integer) command.getBet();
        if (bet < 1 || bet > 99) {
            throw new ValueOutOfBoundsException("Value of MultiBetPlayer's number must be between 0 and 100");
        }
    }

}
