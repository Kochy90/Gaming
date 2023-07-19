package com.gaming_platform.games;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;


@Data
@AllArgsConstructor
public class HigherOrLower implements IPlayable {

    public static final String name = Game.HIGHER_OR_LOWER.name();
    private static final Random RANDOM = new Random();
    private double bet;
    private int playersNumber;

    public Double play() {
        return this.playerWins() ? calculateWinnings() : 0.0;
    }

    private boolean playerWins() {
        return playersNumber > generateIntegerBetween1And100();
    }

    private Double calculateWinnings() {
        return bet * 99 / (100 - playersNumber);
    }

    private int generateIntegerBetween1And100() {
        return RANDOM.nextInt(1, 100);
    }

}
