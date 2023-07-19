package com.gaming_platform;

import com.gaming_platform.games.Game;
import com.gaming_platform.service.CreateGameCommand;

import java.util.Map;

public class UnitTestConstants {

    public static CreateGameCommand generateHigherOrLowerGameCommand(Double bet, Integer playersNumber) {
        return new CreateGameCommand(Game.HIGHER_OR_LOWER.name(), Map.of("bet", bet, "playersNumber", playersNumber));
    }
}
