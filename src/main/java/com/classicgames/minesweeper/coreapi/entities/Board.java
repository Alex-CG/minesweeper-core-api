package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private String id;
    private int size;
    private int numMines;
    private Square[][] matrix;

    public Board(String id, int size, int numMines) {
        this.id = id;
        this.size = size;
        this.numMines = numMines;
        this.initialize();
    }

    private void initialize() {
        this.matrix = new Square[size][size];
    }

}
