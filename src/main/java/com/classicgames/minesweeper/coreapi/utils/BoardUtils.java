package com.classicgames.minesweeper.coreapi.utils;

import com.classicgames.minesweeper.coreapi.entities.Board;

import java.util.Map;
import java.util.UUID;

public final class BoardUtils {

    private BoardUtils() { }

    public static boolean isValidAccess(int row, int col, int rows, int cols) {
        return (row >= 0 && col >= 0 && row < rows && col < cols);
    }

    public static UUID getUniqueUUID(Map<String, Board> data) {
        UUID uuid = UUID.randomUUID();
        while (data.get(uuid.toString()) != null) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }

}
