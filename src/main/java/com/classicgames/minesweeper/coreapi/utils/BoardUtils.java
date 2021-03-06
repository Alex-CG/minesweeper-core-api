package com.classicgames.minesweeper.coreapi.utils;

import com.classicgames.minesweeper.coreapi.entities.Board;

import java.util.Map;
import java.util.UUID;

public final class BoardUtils {

    private BoardUtils() { }

    public static boolean isValidAccess(int x, int y, int n, int m) {
        return (x >= 0 && y >= 0 && x < n && y < m);
    }

    public static UUID getUniqueUUID(Map<String, Board> data) {
        UUID uuid = UUID.randomUUID();
        while (data.get(uuid.toString()) != null) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }

}
