package com.gaming_platform.result_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SinglePlayerSingleBetGameResult {
    private final long gameId;
    private final long playerId;
    private final int roll;
    private final BetResult betResult;
}
