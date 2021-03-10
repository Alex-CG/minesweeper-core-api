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

    public static Game cleanGame(Game game) {
//        for (int row = 0; row < game.getNumMines(); row++) {
//            for (int col = 0; col < game.getNumMines(); col++) {
//                if (!game.getBoard()[row][col].isOpened()) {
//                    game.getBoard()[row][col].setValue(-1);
//                    game.getBoard()[row][col].setType(null);
//                }
//            }
//        }
        return game;
    }

}
