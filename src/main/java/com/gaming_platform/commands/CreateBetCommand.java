package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateBetCommand {
    Long id;
    String betName;
    Double amount;
    Object bet;
}
