package com.gaming_platform.controller;

import com.gaming_platform.exceptions.GameTypeNotConvertibleException;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.ValueOutOfBoundsException;
import com.gaming_platform.service.CreateGameCommand;
import com.gaming_platform.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    @Autowired
    CreateGameCommand createGameCommand;

    @Autowired
    GameService gameService;

    @PostMapping("/play")
    public ResponseEntity<Double> playGame(@RequestBody CreateGameCommand createGameCommand)
            throws NameNotFoundException, GameTypeNotConvertibleException, ValueOutOfBoundsException, InvalidFieldException {
        Double result = gameService.playGame(createGameCommand);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}

