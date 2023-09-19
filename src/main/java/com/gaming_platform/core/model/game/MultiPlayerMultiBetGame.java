package com.gaming_platform.core.model.game;

import com.gaming_platform.core.model.bet.Bet;
import com.gaming_platform.core.model.player.MultiBetPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
@AllArgsConstructor
public abstract class MultiPlayerMultiBetGame<T extends MultiBetPlayer<S>, S extends Bet> {
    protected static final Random RANDOM = new Random();
    protected final Long gameId = RANDOM.nextLong();
    protected List<T> players;
    protected Integer roll;

    public MultiPlayerMultiBetGame() {
    }

    public MultiPlayerMultiBetGame(List<T> players) {
        this.players = players;
    }

    public void setPlayers(List<T> players) {
        this.players = players;
    }
}
