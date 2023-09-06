package com.gaming_platform.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBetCommand {
    Long id;
    String betName;
    Double amount;
    Object bet;
}
