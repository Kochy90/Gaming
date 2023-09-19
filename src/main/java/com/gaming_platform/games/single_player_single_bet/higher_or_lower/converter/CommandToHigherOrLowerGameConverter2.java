package com.gaming_platform.games.single_player_single_bet.higher_or_lower.converter;

import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.core.converters.single_player.CommandToSinglePlayerSingleBetGameConverter;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerGame;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandToHigherOrLowerGameConverter2 extends CommandToSinglePlayerSingleBetGameConverter<HigherOrLowerGame, HigherOrLowerPlayer, HigherOrLowerBet> {

    @Autowired
    CommandToHigherOrLowerPlayerConverter commandToHigherOrLowerPlayerConverter;

    @Override
    public boolean canConvert(GameType gameType) {
        return gameType == GameType.HIGHER_OR_LOWER;
    }

    @Override
    protected HigherOrLowerPlayer convertPlayerCommandToSingleBetPlayer(Long gameId, CreateSingleBetPlayerCommand createSingleBetPlayerCommand) throws InvalidPlayerException {
        return commandToHigherOrLowerPlayerConverter.convertCommandToSingleBetPlayer(gameId, createSingleBetPlayerCommand);
    }

    @Override
    protected HigherOrLowerGame instantiateMultiPlayerMultiBetGame() {
        return new HigherOrLowerGame();
    }

}
