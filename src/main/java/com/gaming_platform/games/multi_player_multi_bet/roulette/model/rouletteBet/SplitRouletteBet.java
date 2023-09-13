package com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Split
 * This is the opposite of a straight Bet. Instead of betting on one number, you’re placing a Bet on the line that separates two numbers.
 * If either number comes up, you win. Examples of wagers you can make include: 1|2, 2|5, 13|14, 13|16, 25|26, 26|27, 27|30.
 * As long as the two numbers sit next to each other, you can split a Bet between them. This pays 17:1.
 */

@Getter
public class SplitRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.SPLIT.name();
    private final int payout = 17;
    private final List<Integer> bet;

    @Builder
    public SplitRouletteBet(long betId, long playerId, long gameId, double amount, List<Integer> bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    protected boolean isWinningBet(int roll) {
        return bet.contains(roll);
    }

}
