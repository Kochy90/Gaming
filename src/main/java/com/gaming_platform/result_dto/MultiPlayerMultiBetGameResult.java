package com.gaming_platform.result_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MultiPlayerMultiBetGameResult {
    private final long gameId;
    private final int roll;
    private final List<PlayerResult> results;
}
