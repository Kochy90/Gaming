package com.gamingPlatform;

import com.gamingPlatform.service.CreateGameCommand;

import java.util.Map;

public class TestConstants {

    public static CreateGameCommand generateHigherOrLowerGameCommand(Float bet, Integer playersNumber) {
        return new CreateGameCommand("HIGHER_OR_LOWER", Map.of("bet", bet, "playersNumber", playersNumber));
    }
}
