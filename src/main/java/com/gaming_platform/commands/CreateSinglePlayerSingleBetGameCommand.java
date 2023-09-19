package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateSinglePlayerSingleBetGameCommand {
    private String gameType;
    private Long gameId;
    private CreateSingleBetPlayerCommand createSingleBetPlayerCommand;
//    private Map<String, Object> gameVariables;
}
