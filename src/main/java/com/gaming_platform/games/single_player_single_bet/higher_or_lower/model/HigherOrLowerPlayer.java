package com.gaming_platform.games.single_player_single_bet.higher_or_lower.model;

import com.gaming_platform.core.model.player.SingleBetPlayer;
import lombok.Getter;

@Getter
public class HigherOrLowerPlayer extends SingleBetPlayer<HigherOrLowerBet> {

    public HigherOrLowerPlayer(Long playerId, HigherOrLowerBet bet) {
        super(playerId, bet);
    }
}
