package com.gaming_platform.games.single_player.single_bet.higher_or_lower;

import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
import com.gaming_platform.result_dto.BetResult;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.ISinglePlayerSingleBetPlayable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public class HigherOrLower implements ISinglePlayerSingleBetPlayable {

    public static final String name = Game.HIGHER_OR_LOWER.name();
    private static final Random RANDOM = new Random();
    public static final Long gameId = RANDOM.nextLong();
    private double bet;
    private int playersNumber;
    private Long playerId;

    public SinglePlayerSingleBetGameResult play() {
        int roll = generateIntegerBetween1And100();
        return new SinglePlayerSingleBetGameResult(gameId, playerId, roll, new BetResult(gameId, calculateWinnings(roll)));
    }

    private boolean playerWins(int roll) {
        return playersNumber > roll;
    }

    private Double calculateWinnings(int roll) {
        return playerWins(roll) ? bet * 99 / (100 - playersNumber) : 0.0;
    }

    private int generateIntegerBetween1And100() {
        return RANDOM.nextInt(1, 100);
    }

}
