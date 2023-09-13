package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateMultiPlayerMultiBetGameCommand {
    private String gameName;
    private List<CreateMultiBetPlayerCommand> createMultiBetPlayerCommands;
}
