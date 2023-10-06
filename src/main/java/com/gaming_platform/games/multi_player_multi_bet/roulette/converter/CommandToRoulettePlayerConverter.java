package com.gaming_platform.games.multi_player_multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.core.converters.multi_player_multi_bet.CommandToMultiBetPlayerConverter;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBet;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandToRoulettePlayerConverter extends CommandToMultiBetPlayerConverter<RoulettePlayer, RouletteBet> {

    @Autowired
    RouletteBetFactory rouletteBetFactory;

    @Override
    protected RouletteBet convertCommandToBet(Long gameId, Long playerId, CreateBetCommand createBetCommand) {
        return rouletteBetFactory.buildRouletteBet(gameId, playerId, createBetCommand);
    }

    @Override
    protected RoulettePlayer instantiateMultiBetPlayer(Long playerId, List<RouletteBet> bets) {
        return new RoulettePlayer(playerId, bets);
    }

}

