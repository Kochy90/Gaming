package com.gaming_platform.result_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlayerResult {
    Long playerId;
    List<BetResult> results;
}
