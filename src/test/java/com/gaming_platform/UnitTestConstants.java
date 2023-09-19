package com.gaming_platform;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;

public class UnitTestConstants {

    public static CreateSinglePlayerSingleBetGameCommand.CreateSinglePlayerSingleBetGameCommandBuilder createHigherOrLowerGameCommandBuilder() {
        return CreateSinglePlayerSingleBetGameCommand.builder()
                .gameType(GameType.HIGHER_OR_LOWER.name());
    }

//    public static CreateSingleBetPlayerCommand generateHigherOrLowerPlayerCommand(CreateBetCommand createBetCommand) {
//        return new CreateSingleBetPlayerCommand(GameType.HIGHER_OR_LOWER.name(), new Random().nextLong(), createBetCommand);
//    }

    public static CreateBetCommand.CreateBetCommandBuilder generateHigherOrLowerBetCommandBuilder() {
        return CreateBetCommand.builder()
                .betName(GameType.HIGHER_OR_LOWER.name());
    }

    private static HigherOrLowerBet generateHigherOrLowerBet(Long playerId, Long betId) {
        return HigherOrLowerBet.builder().betId(betId).playerId(playerId)
                .amount(50d).bet(9).build();
    }

}
