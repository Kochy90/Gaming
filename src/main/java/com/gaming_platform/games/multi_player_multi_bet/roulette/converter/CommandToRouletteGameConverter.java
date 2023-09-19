package com.gaming_platform.games.multi_player_multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.core.converters.multi_player_multi_bet.CommandToMultiPlayerMultiBetGameConverter;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RouletteGame;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandToRouletteGameConverter extends CommandToMultiPlayerMultiBetGameConverter<RouletteGame, RoulettePlayer, RouletteBet> {

    @Autowired
    CommandToRoulettePlayerConverter commandToRoulettePlayerConverter;

    @Override
    public boolean canConvert(GameType gameType) {
        return gameType == GameType.ROULETTE;
    }

    @Override
    protected RoulettePlayer convertPlayerCommandToMultiBetPlayer(Long gameId, CreateMultiBetPlayerCommand createMultiBetPlayerCommand) throws InvalidPlayerException {
        return commandToRoulettePlayerConverter.convertCommandToMultiBetPlayer(gameId, createMultiBetPlayerCommand);
    }

    @Override
    protected RouletteGame instantiateMultiPlayerMultiBetGame() {
        return new RouletteGame();
    }
}

