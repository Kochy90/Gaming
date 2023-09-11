package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateMultiPlayerMultiBetGameCommand {
    // Consider adding validation logic for your command objects.Check Spring provides annotations like @Valid and validation constraints like @NotNull, @Size, etc.
    private String gameName;
    private List<CreatePlayerCommand> createPlayerCommands;
}
