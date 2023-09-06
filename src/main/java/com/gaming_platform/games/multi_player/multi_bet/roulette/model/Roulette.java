package com.gaming_platform.games.multi_player.multi_bet.roulette.model;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.IMultiPlayerMultiBetPlayable;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.RouletteBet;
import com.gaming_platform.result_dto.BetResult;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import com.gaming_platform.result_dto.PlayerResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Roulette implements IMultiPlayerMultiBetPlayable {

    private static final Random RANDOM = new Random();
    public final Long gameId = RANDOM.nextLong();
    private final List<RoulettePlayer> players;

    private int generateIntegerBetween0And36() {
        return RANDOM.nextInt(0, 37);
    }

    public MultiPlayerMultiBetGameResult play() {
        int roll = generateIntegerBetween0And36();
        return new MultiPlayerMultiBetGameResult(gameId, roll, generatePlayersResults(roll));
    }

    public List<PlayerResult> generatePlayersResults(int roll) {
        return players.stream().map(player -> new PlayerResult(player.getPlayerId(), generateBetResults(roll, player.getBets())))
                .collect(Collectors.toList());
    }

    private List<BetResult> generateBetResults(int roll, List<RouletteBet> bets) {
        return bets.stream().map(rouletteBet -> {
            try {
                return rouletteBet.getResult(roll);
            } catch (IncorrectBetTypeException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

}
