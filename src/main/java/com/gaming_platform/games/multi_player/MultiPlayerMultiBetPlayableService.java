package com.gaming_platform.games.multi_player;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.games.IMultiPlayerMultiBetPlayable;
import com.gaming_platform.games.multi_player.model.MultiBetPlayer;
import com.gaming_platform.games.multi_player.model.MultiPlayerMultiBetGame;
import com.gaming_platform.games.multi_player.multi_bet.Bet;
import com.gaming_platform.result_dto.BetResult;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import com.gaming_platform.result_dto.PlayerResult;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MultiPlayerMultiBetPlayableService<T extends MultiPlayerMultiBetGame<S, U>, S extends MultiBetPlayer<U>, U extends Bet>
        implements IMultiPlayerMultiBetPlayable<T, S, U> {

    @Override
    public MultiPlayerMultiBetGameResult play(T game) {
        int roll = game.getRoll();
        return new MultiPlayerMultiBetGameResult(game.getGameId(), roll, generatePlayersResults(game.getPlayers(), roll));
    }

    public List<PlayerResult> generatePlayersResults(List<S> players, int roll) {
        return players.stream().map(player -> new PlayerResult(player.getPlayerId(), generateBetResults(roll, player.getBets())))
                .collect(Collectors.toList());
    }

    private List<BetResult> generateBetResults(int roll, List<U> bets) {
        return bets.stream().map(bet -> {
            try {
                return bet.getResult(roll);
            } catch (IncorrectBetTypeException e) {
                e.getMessage();
            }
            return null;
        }).collect(Collectors.toList());
    }

}
