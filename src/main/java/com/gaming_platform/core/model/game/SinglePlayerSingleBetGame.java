package com.gaming_platform.core.model.game;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.player.SingleBetPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public class SinglePlayerSingleBetGame<T extends SingleBetPlayer<S>, S extends Bet> {
    protected static final Random RANDOM = new Random();
    protected final Long gameId = RANDOM.nextLong();
    protected T player;
    protected Integer roll;

    public SinglePlayerSingleBetGame() {
    }

    public SinglePlayerSingleBetGame(T player) {
        this.player = player;
    }

    public void setPlayer(T player) {
        this.player = player;
    }
}
