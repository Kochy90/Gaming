package com.gaming_platform.core.service;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.game.SinglePlayerSingleBetGame;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.result_dto.BetResult;
import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
import org.springframework.stereotype.Service;

@Service
public abstract class SinglePlayerSingleBetPlayableService<T extends SinglePlayerSingleBetGame<S, U>, S extends SingleBetPlayer<U>, U extends Bet>
        implements ISinglePlayerSingleBetPlayable<T, S, U> {

    @Override
    public SinglePlayerSingleBetGameResult play(T game) {
        int roll = game.getRoll();
        return SinglePlayerSingleBetGameResult.builder()
                .roll(roll)
                .gameId(game.getGameId())
                .playerId(game.getPlayer().getPlayerId())
                .betResult(generateBetResult(roll, game.getPlayer().getBet()))
                .build();
    }

    private BetResult generateBetResult(int roll, U bet) {
        try {
            return bet.getResult(roll);
        } catch (IncorrectBetTypeException e) {
            e.getMessage();
            return null;
        }
    }


}
