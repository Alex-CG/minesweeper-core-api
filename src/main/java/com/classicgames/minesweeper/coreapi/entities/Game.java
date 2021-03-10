package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private String id;
    private String name;
    private int size;
    private int numMines;
    private int numFlags;
    private FaceType face;
    private Square[][] board;
    private int openings;

    public Game(int size, int numMines) {
        this.size = size;
        this.numMines = numMines;
        this.initialize();
    }

    private void initialize() {
        this.face = FaceType.HAPPY;
        this.board = new Square[size][size];
    }

}
