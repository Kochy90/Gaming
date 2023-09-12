package com.gaming_platform.games.multi_player.multi_bet.roulette.converter;

import com.gaming_platform.commands.CreatePlayerCommand;
import com.gaming_platform.converters.multi_player_multi_bet.CommandToMultiPlayerMultiBetGameConverter;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.Roulette;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.RouletteBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandToRouletteGameConverter extends CommandToMultiPlayerMultiBetGameConverter<Roulette, RoulettePlayer, RouletteBet> {

    @Autowired
    CommandToRoulettePlayerConverter commandToRoulettePlayerConverter;

    @Override
    public boolean canConvert(Game game) {
        return game == Game.ROULETTE;
    }

    @Override
    protected RoulettePlayer convertPlayerCommandToMultiBetPlayer(Long gameId, CreatePlayerCommand createPlayerCommand) throws InvalidPlayerException {
        return commandToRoulettePlayerConverter.convertCommandToMultiBetPlayer(gameId, createPlayerCommand);
    }

    @Override
    protected Roulette instantiateMultiPlayerMultiBetGame() {
        return new Roulette();
    }
}

