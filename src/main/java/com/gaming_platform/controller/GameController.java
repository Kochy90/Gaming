package com.gaming_platform.controller;

import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.result_dto.MultiPlayerMultiBetGameResult;
import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
import com.gaming_platform.service.GameService;
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
    GameService gameService;

    @PostMapping("/mpmb/play")
    public ResponseEntity<MultiPlayerMultiBetGameResult> playGame(@RequestBody CreateMultiPlayerMultiBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        MultiPlayerMultiBetGameResult result = gameService.playGame(createGameCommand);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @PostMapping("/spsb/play")
    public ResponseEntity<SinglePlayerSingleBetGameResult> playGame(@RequestBody CreateSinglePlayerSingleBetGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException, InvalidPlayerException {
        SinglePlayerSingleBetGameResult result = gameService.playGame(createGameCommand);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}

