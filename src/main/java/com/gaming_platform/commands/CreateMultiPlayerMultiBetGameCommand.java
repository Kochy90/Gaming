package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CreateMultiPlayerMultiBetGameCommand {
    private String gameType;
    private List<CreateMultiBetPlayerCommand> createMultiBetPlayerCommands;
}
