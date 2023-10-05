package integration_test.com.gaming_platform;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.model.game.GameType;

import java.util.Random;

public class IntegrationTestConstants {

    private static final Random RANDOM = new Random();

    public static CreateSinglePlayerSingleBetGameCommand.CreateSinglePlayerSingleBetGameCommandBuilder createHigherOrLowerGameCommandBuilder() {
        return CreateSinglePlayerSingleBetGameCommand.builder()
                .gameType(GameType.HIGHER_OR_LOWER.name())
                .gameId(RANDOM.nextLong());
    }

    public static CreateBetCommand.CreateBetCommandBuilder generateHigherOrLowerBetCommandBuilder() {
        return CreateBetCommand.builder()
                .id(RANDOM.nextLong())
                .betName(GameType.HIGHER_OR_LOWER.name());
    }

    public static CreateSinglePlayerSingleBetGameCommand createHigherOrLowerGameCommand(Double betAmount, Integer bet) {
        CreateBetCommand betCommand = generateHigherOrLowerBetCommandBuilder().amount(betAmount).bet(bet).build();
        CreateSingleBetPlayerCommand playerCommand = new CreateSingleBetPlayerCommand(RANDOM.nextLong(), betCommand);
        return createHigherOrLowerGameCommandBuilder().createSingleBetPlayerCommand(playerCommand).build();
    }

}
