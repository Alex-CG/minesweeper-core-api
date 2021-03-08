package com.classicgames.minesweeper.coreapi.utils;

import com.classicgames.minesweeper.coreapi.entities.Game;

import java.util.Map;
import java.util.UUID;

public final class GameUtils {

    private GameUtils() { }

    public static UUID getUniqueUUID(Map<String, Game> data) {
        UUID uuid = UUID.randomUUID();
        while (data.get(uuid.toString()) != null) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }

}
