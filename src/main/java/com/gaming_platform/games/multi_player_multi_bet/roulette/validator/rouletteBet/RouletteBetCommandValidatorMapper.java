package com.gaming_platform.games.multi_player_multi_bet.roulette.validator.rouletteBet;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.core.validators.IBetCommandValidatable;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.RouletteBetType;
import org.springframework.stereotype.Service;

@Service
public class RouletteBetCommandValidatorMapper {

    public IBetCommandValidatable assignValidator(CreateBetCommand command) {
        return switch (RouletteBetType.valueOf(command.getBetName())) {
            case STRAIGHT -> new StraightRouletteBetCommandValidator();
            case ODD_EVEN -> new OddEvenRouletteBetCommandValidator();
            case RED_BLACK -> new RedBlackRouletteBetCommandValidator();
            case DOZENS -> new DozensRouletteBetCommandValidator();
            case COLUMNS -> new ColumnsRouletteBetCommandValidator();
            case HIGH_LOW -> new HighLowRouletteBetCommandValidator();
            case SPLIT -> new SplitRouletteBetCommandValidator();
            case STREET -> new StreetRouletteBetCommandValidator();
            case CORNER -> new CornerRouletteBetCommandValidator();
            case LINE -> new LineRouletteBetCommandValidator();
            case FIVE_NUMBER_BET -> new FiveNumberRouletteBetCommandValidator();
            case BASKET -> new BasketRouletteBetCommandValidator();
            case SNAKE -> new SnakeRouletteBetCommandValidator();
        };
    }

}
