package com.classicgames.minesweeper.coreapi.utils;

public final class BoardUtils {

    private BoardUtils() { }

    public static boolean isValidAccess(int row, int col, int rows, int cols) {
        return (row >= 0 && col >= 0 && row < rows && col < cols);
    }

}
