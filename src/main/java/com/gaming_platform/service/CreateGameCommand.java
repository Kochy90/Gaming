package com.gaming_platform.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CreateGameCommand {

    private String name;
    private Map<String, Object> gameVariables;
}
