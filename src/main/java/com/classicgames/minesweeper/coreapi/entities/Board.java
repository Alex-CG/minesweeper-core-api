package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private String id;
    private int size;
    private int numMines;
    private boolean happy;
    private Square[][] matrix;

    public Board(int size, int numMines) {
        this.size = size;
        this.numMines = numMines;
        this.initialize();
    }

    private void initialize() {
        this.happy = true;
        this.matrix = new Square[size][size];
    }

}
