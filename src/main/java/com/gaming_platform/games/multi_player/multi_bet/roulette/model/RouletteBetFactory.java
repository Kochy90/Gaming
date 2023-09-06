package com.gaming_platform.games.multi_player.multi_bet.roulette;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.exceptions.IncorrectBetTypeException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouletteBetFactory {

    public RouletteBet buildRouletteBet(CreateBetCommand command) throws IncorrectBetTypeException, InvalidFieldException {
        return switch (RouletteBetType.valueOf(command.getBetName())) {
            case STRAIGHT -> validateAndBuildStraightRouletteBet(command);
            case ODD_EVEN -> validateAndBuildOddEvenRouletteBet(command);
            case RED_BLACK -> validateAndBuildRedBlackRouletteBet(command);
            case DOZENS -> validateAndBuildDozensRouletteBet(command);
            case COLUMNS -> validateAndBuildColumnsRouletteBet(command);
            case HIGH_LOW -> validateAndBuildHighLowRouletteBet(command);
            case SPLIT -> validateAndBuildSplitRouletteBet(command);
            case STREET -> validateAndBuildStreetRouletteBet(command);
            case CORNER -> validateAndBuildCornerRouletteBet(command);
            case LINE -> validateAndBuildLineRouletteBet(command);
            case FIVE_NUMBER_BET -> validateAndBuildFiveNumberRouletteBet(command);
            case BASKET -> validateAndBuildBasketRouletteBet(command);
            case SNAKE -> validateAndBuildSnakeRouletteBet(command);
            default -> throw new IncorrectBetTypeException("Invalid roulette bet name");
        };
    }

    private StraightRouletteBet validateAndBuildStraightRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof Integer) {
            return new StraightRouletteBet(command.getAmount(), command.getId(), (int) command.getBet());
        } else {
            throw new InvalidFieldException("incorrect object type for Straight roulette bet");
        }
    }

    private OddEvenRouletteBet validateAndBuildOddEvenRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return new OddEvenRouletteBet(command.getAmount(), command.getId(), (String) command.getBet());
        } else {
            throw new InvalidFieldException("incorrect object type for odd/even roulette bet");
        }

    }

    private RedBlackRouletteBet validateAndBuildRedBlackRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return new RedBlackRouletteBet(command.getAmount(), command.getId(), (String) command.getBet());
        } else {
            throw new InvalidFieldException("incorrect object type for red/black roulette bet");
        }
    }

    private DozensRouletteBet validateAndBuildDozensRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return new DozensRouletteBet(command.getAmount(), command.getId(), (String) command.getBet());
        } else {
            throw new InvalidFieldException("incorrect object type for dozens roulette bet");
        }
    }

    private ColumnsRouletteBet validateAndBuildColumnsRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return new ColumnsRouletteBet(command.getAmount(), command.getId(), (String) command.getBet());
        } else {
            throw new InvalidFieldException("incorrect object type for columns roulette bet");
        }
    }

    private HighLowRouletteBet validateAndBuildHighLowRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (command.getBet() instanceof String) {
            return new HighLowRouletteBet(command.getAmount(), command.getId(), (String) command.getBet());
        } else {
            throw new InvalidFieldException("incorrect object type for high/low roulette bet");
        }
    }

    private SplitRouletteBet validateAndBuildSplitRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for split roulette bet");
        }
        List<Integer> splitBet = (List<Integer>)command.getBet();
        if (splitBet.size() != 2) {
            throw new InvalidFieldException("incorrect number of elements for split roulette bet");
        }
        return new SplitRouletteBet(command.getAmount(), command.getId(), splitBet);
    }

    private RouletteBet validateAndBuildStreetRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for street roulette bet");
        }
        List<Integer> streetBet = (List<Integer>)command.getBet();
        if (streetBet.size() != 3) {
            throw new InvalidFieldException("incorrect number of elements for street roulette bet");
        }
        return new StreetRouletteBet(command.getAmount(), command.getId(), streetBet);
    }

    private RouletteBet validateAndBuildCornerRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for corner roulette bet");
        }
        List<Integer> cornerBet = (List<Integer>)command.getBet();
        if (cornerBet.size() != 4) {
            throw new InvalidFieldException("incorrect number of elements for corner roulette bet");
        }
        return new CornerRouletteBet(command.getAmount(), command.getId(), cornerBet);
    }

    private RouletteBet validateAndBuildLineRouletteBet(CreateBetCommand command) throws InvalidFieldException {
        if (!(command.getBet() instanceof List)) {
            throw new InvalidFieldException("incorrect object type for line roulette bet");
        }
        List<Integer> lineBet = (List<Integer>)command.getBet();
        if (lineBet.size() != 6) {
            throw new InvalidFieldException("incorrect number of elements for line roulette bet");
        }
        return new LineRouletteBet(command.getAmount(), command.getId(), lineBet);
    }

    private RouletteBet validateAndBuildFiveNumberRouletteBet(CreateBetCommand command) {
        return new FiveNumberRouletteBet(command.getAmount(), command.getId());
    }

    private RouletteBet validateAndBuildBasketRouletteBet(CreateBetCommand command) {
        return new BasketRouletteBet(command.getAmount(), command.getId());
    }

    private RouletteBet validateAndBuildSnakeRouletteBet(CreateBetCommand command) {
        return new SnakeRouletteBet(command.getAmount(), command.getId());
    }

}
