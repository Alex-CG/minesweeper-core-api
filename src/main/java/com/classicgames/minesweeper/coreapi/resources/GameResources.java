package com.classicgames.minesweeper.coreapi.resources;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameResources {

    @Autowired
    private BoardService service;

    private int DEFAULT_BOARD_SIZE = 10;
    private int DEFAULT_NUM_MINES = 20;

    @PostMapping
    public ResponseEntity newGame() {
        Board board = service.createBoard(DEFAULT_BOARD_SIZE, DEFAULT_NUM_MINES);
        return ResponseEntity.ok(board);
    }

    @PatchMapping(path = "/{id}/flag/{row}/{col}")
    public ResponseEntity flagSquare(@PathVariable("id") String id,
                                     @PathVariable("row") Integer row,
                                     @PathVariable("col") Integer col) {
        Board board = service.flagSquare(id, row, col);
        return ResponseEntity.ok(board);
    }

    @PatchMapping(path = "/{id}/reveal/{row}/{col}")
    public ResponseEntity revealSquare(@PathVariable("id") String id,
                                       @PathVariable("row") Integer row,
                                       @PathVariable("col") Integer col) {
        Board board = service.revealSquare(id, row, col);
        return ResponseEntity.ok(board);
    }

}
