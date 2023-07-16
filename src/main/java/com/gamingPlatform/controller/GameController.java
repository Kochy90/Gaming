package com.gamingPlatform.controller;

import com.gamingPlatform.service.CreateGameCommand;
import com.gamingPlatform.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    GameService gameService;

    @PostMapping("/play")
    public ResponseEntity<Float> playGame(@RequestBody CreateGameCommand createGameCommand) {
        Float result = gameService.playGame(createGameCommand);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}

