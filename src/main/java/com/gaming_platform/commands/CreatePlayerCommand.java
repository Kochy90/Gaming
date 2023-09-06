package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreatePlayerCommand {
    Long playerId;
    List<CreateBetCommand> createBetCommands;
}
