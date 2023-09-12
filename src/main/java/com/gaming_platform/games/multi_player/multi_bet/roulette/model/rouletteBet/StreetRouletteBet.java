package com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet;

import com.gaming_platform.exceptions.IncorrectBetTypeException;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Street
 * This is a Bet on any number within a row of three numbers. For example:
 *
 * | 1, 2, 3 | 4, 5, 6 | 7, 8, 9 | … | 34, 35, 36 |
 * If the ball lands on any number within the trio, you’re paid 11:1.
 * These bets are also called trio or steam bets.
 */

@Getter
public class StreetRouletteBet extends RouletteBet {

    public static final String betName = RouletteBetType.STREET.name();
    private final int payout = 11;
    private final List<Integer> bet;

    @Builder
    public StreetRouletteBet(long betId, long playerId, long gameId, double amount, List<Integer> bet) {
        super(betId, playerId, gameId, amount);
        this.bet = bet;
    }

    @Override
    protected boolean isWinningBet(int roll) throws IncorrectBetTypeException {
        return bet.contains(roll);
    }
}
