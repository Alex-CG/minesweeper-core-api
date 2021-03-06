package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private int size;
    private int numMines;
    private int[][] matrix;

    public Board(int size, int numMines) {
        this.size = size;
        this.numMines = numMines;
        this.initialize();
    }

    private void initialize() {
        this.matrix = new int[size][size];
    }

}
