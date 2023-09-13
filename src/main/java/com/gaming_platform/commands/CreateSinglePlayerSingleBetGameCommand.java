package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CreateSinglePlayerSingleBetGameCommand {
    private String gameName;
    private Long playerId;
    private CreateSingleBetPlayerCommand createSingleBetPlayerCommand;
    private Map<String, Object> gameVariables;
}
