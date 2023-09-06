package com.gaming_platform.result_dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PlayerResult {
    Long playerId;
    List<BetResult> results;
}
