package com.classicgames.minesweeper.coreapi.resources;

import com.classicgames.minesweeper.coreapi.entities.Game;
import com.classicgames.minesweeper.coreapi.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameResources {

    @Autowired
    private GameService service;

    private final int DEFAULT_BOARD_SIZE = 10;
    private final int DEFAULT_NUM_MINES = 20;

    @PostMapping
    public ResponseEntity<Game> newGame() {
        Game game = service.createGame(DEFAULT_BOARD_SIZE, DEFAULT_NUM_MINES);
        return ResponseEntity.ok(game);
    }

    @PatchMapping(path = "/{id}/flag/{row}/{col}")
    public ResponseEntity<Game> flagSquare(@PathVariable("id") String id,
                                           @PathVariable("row") Integer row,
                                           @PathVariable("col") Integer col) {
        Game game = service.flagSquare(id, row, col);
        return ResponseEntity.ok(game);
    }

    @PatchMapping(path = "/{id}/reveal/{row}/{col}")
    public ResponseEntity<Game> revealSquare(@PathVariable("id") String id,
                                             @PathVariable("row") Integer row,
                                             @PathVariable("col") Integer col) {
        Game game = service.revealSquare(id, row, col);
        return ResponseEntity.ok(game);
    }

}
