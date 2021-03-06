package com.classicgames.minesweeper.coreapi.resources;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameResources {

    @Autowired
    private BoardService service;

    private int DEFAULT_BOARD_SIZE = 10;
    private int DEFAULT_NUM_MINES = 20;

    @PostMapping(path = "/new")
    public ResponseEntity newGame() {
        Board board = service.createBoard(DEFAULT_BOARD_SIZE, DEFAULT_NUM_MINES);
        return ResponseEntity.ok(board);
    }

}
