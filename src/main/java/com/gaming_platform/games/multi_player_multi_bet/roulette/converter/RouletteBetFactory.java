package com.gaming_platform.games.multi_player_multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouletteBetFactory {

    public RouletteBet buildRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        return switch (RouletteBetType.valueOf(command.getBetName())) {
            case STRAIGHT -> validateAndBuildStraightRouletteBet(gameId, playerId, command);
            case ODD_EVEN -> validateAndBuildOddEvenRouletteBet(gameId, playerId, command);
            case RED_BLACK -> validateAndBuildRedBlackRouletteBet(gameId, playerId, command);
            case DOZENS -> validateAndBuildDozensRouletteBet(gameId, playerId, command);
            case COLUMNS -> validateAndBuildColumnsRouletteBet(gameId, playerId, command);
            case HIGH_LOW -> validateAndBuildHighLowRouletteBet(gameId, playerId, command);
            case SPLIT -> validateAndBuildSplitRouletteBet(gameId, playerId, command);
            case STREET -> validateAndBuildStreetRouletteBet(gameId, playerId, command);
            case CORNER -> validateAndBuildCornerRouletteBet(gameId, playerId, command);
            case LINE -> validateAndBuildLineRouletteBet(gameId, playerId, command);
            case FIVE_NUMBER_BET -> validateAndBuildFiveNumberRouletteBet(gameId, playerId, command);
            case BASKET -> validateAndBuildBasketRouletteBet(gameId, playerId, command);
            case SNAKE -> validateAndBuildSnakeRouletteBet(gameId, playerId, command);
        };
    }

    private StraightRouletteBet validateAndBuildStraightRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof Integer) {
            return StraightRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((int) command.getBet()).build();
        } else {
            throw new InvalidFieldException("incorrect object type for Straight roulette Bet");
        }
    }

    private OddEvenRouletteBet validateAndBuildOddEvenRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return OddEvenRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
        } else {
            throw new InvalidFieldException("incorrect object type for odd/even roulette Bet");
        }
    }

    private RedBlackRouletteBet validateAndBuildRedBlackRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return RedBlackRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
        } else {
            throw new InvalidFieldException("incorrect object type for red/black roulette Bet");
        }
    }

    private DozensRouletteBet validateAndBuildDozensRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return DozensRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
        } else {
            throw new InvalidFieldException("incorrect object type for dozens roulette Bet");
        }
    }

    private ColumnsRouletteBet validateAndBuildColumnsRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return ColumnsRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
        } else {
            throw new InvalidFieldException("incorrect object type for columns roulette Bet");
        }
    }

    private HighLowRouletteBet validateAndBuildHighLowRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return HighLowRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                    .amount(command.getAmount()).bet((String) command.getBet()).build();
        } else {
            throw new InvalidFieldException("incorrect object type for high/low roulette Bet");
        }
    }

    private SplitRouletteBet validateAndBuildSplitRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for split roulette Bet");
        }
        List<Integer> splitBet = (List<Integer>)command.getBet();
        if (splitBet.size() != 2) {
            throw new InvalidFieldException("incorrect number of elements for split roulette Bet");
        }
        return SplitRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(splitBet).build();
    }

    private StreetRouletteBet validateAndBuildStreetRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for street roulette Bet");
        }
        List<Integer> streetBet = (List<Integer>)command.getBet();
        if (streetBet.size() != 3) {
            throw new InvalidFieldException("incorrect number of elements for street roulette Bet");
        }
        return StreetRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(streetBet).build();
    }

    private CornerRouletteBet validateAndBuildCornerRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for corner roulette Bet");
        }
        List<Integer> cornerBet = (List<Integer>)command.getBet();
        if (cornerBet.size() != 4) {
            throw new InvalidFieldException("incorrect number of elements for corner roulette Bet");
        }
        return CornerRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(cornerBet).build();
    }

    private LineRouletteBet validateAndBuildLineRouletteBet(Long gameId, Long playerId, CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for line roulette Bet");
        }
        List<Integer> lineBet = (List<Integer>)command.getBet();
        if (lineBet.size() != 6) {
            throw new InvalidFieldException("incorrect number of elements for line roulette Bet");
        }
        return LineRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).bet(lineBet).build();
    }

    private FiveNumberRouletteBet validateAndBuildFiveNumberRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        return FiveNumberRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).build();
    }

    private BasketRouletteBet validateAndBuildBasketRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        return BasketRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).build();
    }

    private SnakeRouletteBet validateAndBuildSnakeRouletteBet(Long gameId, Long playerId, CreateBetCommand command) {
        return SnakeRouletteBet.builder().betId(command.getId()).playerId(playerId).gameId(gameId)
                .amount(command.getAmount()).build();
    }

}
