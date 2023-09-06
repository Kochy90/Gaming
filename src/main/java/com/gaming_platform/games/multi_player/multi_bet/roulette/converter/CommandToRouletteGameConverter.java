package com.gaming_platform.games.multi_player.multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.commands.CreatePlayerCommand;
import com.gaming_platform.converters.IMultiPlayerMultiBetGameConverter;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.IMultiPlayerMultiBetPlayable;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.Roulette;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RoulettePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandToRouletteGameConverter implements IMultiPlayerMultiBetGameConverter {

    @Autowired
    CommandToRoulettePlayerConverter commandToRoulettePlayerConverter;

    @Override
    public boolean canConvert(Game game) {
        return game == Game.ROULETTE;
    }

    public IMultiPlayerMultiBetPlayable build(CreateMultiPlayerMultiBetGameCommand gameCommand) throws InvalidPlayerException {
        List<RoulettePlayer> roulettePlayers = new ArrayList<>();

        for (CreatePlayerCommand player : gameCommand.getCreatePlayerCommands()) {
            try {
                roulettePlayers.add(commandToRoulettePlayerConverter.convertPlayerCommandToRoulettePlayer(player));
            } catch (InvalidPlayerException e) {
                e.printStackTrace();
            }

        }
        return new Roulette(roulettePlayers);
    }
}

