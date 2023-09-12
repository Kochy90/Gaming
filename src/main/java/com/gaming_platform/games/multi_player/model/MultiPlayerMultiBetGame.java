package com.gaming_platform.games.multi_player.model;

import com.gaming_platform.games.multi_player.multi_bet.Bet;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
@AllArgsConstructor
public class MultiPlayerMultiBetGame<T extends MultiBetPlayer<U>, U extends Bet> {
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
