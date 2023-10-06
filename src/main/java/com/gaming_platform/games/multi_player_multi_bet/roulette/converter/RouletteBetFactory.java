package com.gaming_platform.games.multi_player_multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouletteBetFactory {

    public RouletteBet buildRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        return switch (RouletteBetType.valueOf(command.getBetName())) {
            case STRAIGHT -> buildStraightRouletteBet(gameId, playerId, command);
            case ODD_EVEN -> buildOddEvenRouletteBet(gameId, playerId, command);
            case RED_BLACK -> buildRedBlackRouletteBet(gameId, playerId, command);
            case DOZENS -> buildDozensRouletteBet(gameId, playerId, command);
            case COLUMNS -> buildColumnsRouletteBet(gameId, playerId, command);
            case HIGH_LOW -> buildHighLowRouletteBet(gameId, playerId, command);
            case SPLIT -> buildSplitRouletteBet(gameId, playerId, command);
            case STREET -> buildStreetRouletteBet(gameId, playerId, command);
            case CORNER -> buildCornerRouletteBet(gameId, playerId, command);
            case LINE -> buildLineRouletteBet(gameId, playerId, command);
            case FIVE_NUMBER_BET -> buildFiveNumberRouletteBet(gameId, playerId, command);
            case BASKET -> buildBasketRouletteBet(gameId, playerId, command);
            case SNAKE -> buildSnakeRouletteBet(gameId, playerId, command);
        };
    }

    private StraightRouletteBet buildStraightRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
            return StraightRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((int) command.getBet()).build();
    }

    private OddEvenRouletteBet buildOddEvenRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
            return OddEvenRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();

    }

    private RedBlackRouletteBet buildRedBlackRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
            return RedBlackRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
    }

    private DozensRouletteBet buildDozensRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
            return DozensRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
    }

    private ColumnsRouletteBet buildColumnsRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
            return ColumnsRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
    }

    private HighLowRouletteBet buildHighLowRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
            return HighLowRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
    }

    private SplitRouletteBet buildSplitRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        List<Integer> splitBet = (List<Integer>) command.getBet();
        return SplitRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(splitBet).build();
    }

    private StreetRouletteBet buildStreetRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        List<Integer> streetBet = (List<Integer>) command.getBet();
        return StreetRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(streetBet).build();
    }

    private CornerRouletteBet buildCornerRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        List<Integer> cornerBet = (List<Integer>) command.getBet();
        return CornerRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(cornerBet).build();
    }

    private LineRouletteBet buildLineRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        List<Integer> lineBet = (List<Integer>) command.getBet();
        return LineRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(lineBet).build();
    }

    private FiveNumberRouletteBet buildFiveNumberRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        return FiveNumberRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).build();
    }

    private BasketRouletteBet buildBasketRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        return BasketRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).build();
    }

    private SnakeRouletteBet buildSnakeRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        return SnakeRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).build();
    }

}
