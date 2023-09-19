package com.gaming_platform.games.single_player_single_bet.higher_or_lower.model;

import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.core.model.game.SinglePlayerSingleBetGame;
import lombok.Getter;

@Getter
public class HigherOrLowerGame extends SinglePlayerSingleBetGame<HigherOrLowerPlayer, HigherOrLowerBet> {

    public static final String name = GameType.HIGHER_OR_LOWER.name();
    protected final Integer roll;

    public HigherOrLowerGame() {
        this.roll = generateIntegerBetween1And100();
    }

    private int generateIntegerBetween1And100() {
        return RANDOM.nextInt(1, 100);
    }

}
