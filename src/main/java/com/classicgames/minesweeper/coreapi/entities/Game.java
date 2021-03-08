package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private String id;
    private int size;
    private int numMines;
    private boolean happy;
    private Square[][] board;

    public Game(int size, int numMines) {
        this.size = size;
        this.numMines = numMines;
        this.initialize();
    }

    private void initialize() {
        this.happy = true;
        this.board = new Square[size][size];
    }

}
