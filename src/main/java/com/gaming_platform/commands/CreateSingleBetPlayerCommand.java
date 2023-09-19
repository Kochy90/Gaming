package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateSingleBetPlayerCommand {
    Long playerId;
    CreateBetCommand createBetCommand;
}
