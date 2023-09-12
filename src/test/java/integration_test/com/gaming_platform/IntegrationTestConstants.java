package integration_test.com.gaming_platform;

import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.games.Game;

import java.util.Map;
import java.util.Random;

public class IntegrationTestConstants {

    public static CreateSinglePlayerSingleBetGameCommand generateHigherOrLowerGameCommand(Double bet, Integer playersNumber) {
        return new CreateSinglePlayerSingleBetGameCommand(Game.HIGHER_OR_LOWER.name(), new Random().nextLong(), Map.of("Bet", bet, "playersNumber", playersNumber));
    }
}
