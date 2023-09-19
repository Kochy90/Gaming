package com.gaming_platform.controller;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.core.service.MultiPlayerMultiBetGameService;
import com.gaming_platform.core.service.SinglePlayerSingleBetGameService;
import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NameNotFoundException;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    @Autowired
    MultiPlayerMultiBetGameService multiPlayerMultiBetGameService;

    @Autowired
    SinglePlayerSingleBetGameService singlePlayerSingleBetGameService;

    @PostMapping("/multi-player-multi-bet/play")
    public ResponseEntity<MultiPlayerMultiBetGameResult> playGame(@RequestBody CreateMultiPlayerMultiBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        MultiPlayerMultiBetGameResult result = multiPlayerMultiBetGameService.playGame(createGameCommand);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @PostMapping("/single-player-single-bet/play")
    public ResponseEntity<SinglePlayerSingleBetGameResult> playGame(@RequestBody CreateSinglePlayerSingleBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        SinglePlayerSingleBetGameResult result = singlePlayerSingleBetGameService.playGame(createGameCommand);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}

