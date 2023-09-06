package com.gaming_platform;

import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.games.Game;

import java.util.Map;
import java.util.Random;

public class UnitTestConstants {

    public static CreateSinglePlayerSingleBetGameCommand generateHigherOrLowerGameCommand(Double bet, Integer playersNumber) {
        return new CreateSinglePlayerSingleBetGameCommand(Game.HIGHER_OR_LOWER.name(), new Random().nextLong(), Map.of("bet", bet, "playersNumber", playersNumber));
    }

    public static CreateSinglePlayerSingleBetGameCommand generateHigherOrLowerGameCommand(Map<String, Object> mapOfInputGameVariables) {
        return new CreateSinglePlayerSingleBetGameCommand(Game.HIGHER_OR_LOWER.name(), new Random().nextLong(), mapOfInputGameVariables);
    }
}
