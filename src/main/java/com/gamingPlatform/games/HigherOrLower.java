package com.gamingPlatform.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
//@Component
@AllArgsConstructor
public class HigherOrLower implements IPlayable {

    public static final String name = "HIGHER_OR_LOWER";
    private Float bet;
    private Integer playersNumber;

    public Float play() {
        boolean playerWins = playerWins();

        if (playerWins) {
            return calculateWinnings();
        } else {
            return 0f;
        }
    }

    private boolean playerWins() {
        return playersNumber > generateIntegerBetween1And100();
    }

    private Float calculateWinnings() {
        return bet * (99 / (100 - playersNumber));
    }

    private Integer generateIntegerBetween1And100() {
        return (int) (Math.random() * 100);
    }

}
