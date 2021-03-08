package com.classicgames.minesweeper.coreapi.utils;

import com.classicgames.minesweeper.coreapi.entities.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameUtilsTest {

    public Map<String, Game> GAME_DATA;

    @BeforeEach
    public void initializeData() {
        GAME_DATA = new HashMap<>();
    }

    @Test
    public void canGenerateAUniqueUUID() {
        for (int i = 0; i < 1000; i++) {
            UUID uuidX = UUID.randomUUID();
            Game gameX = new Game(10, 20);
            gameX.setId(uuidX.toString());
            GAME_DATA.put(gameX.getId(), gameX);
        }

        UUID uuuid = GameUtils.getUniqueUUID(GAME_DATA);

        Assertions.assertNotNull(uuuid.toString());
        Assertions.assertNull(GAME_DATA.get(uuuid.toString()));
    }

}
