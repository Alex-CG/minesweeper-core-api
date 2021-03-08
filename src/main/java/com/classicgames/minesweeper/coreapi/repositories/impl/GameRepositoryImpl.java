package com.classicgames.minesweeper.coreapi.repositories.impl;

import com.classicgames.minesweeper.coreapi.entities.Game;
import com.classicgames.minesweeper.coreapi.repositories.GameRepository;
import com.classicgames.minesweeper.coreapi.utils.GameUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class GameRepositoryImpl implements GameRepository {

    public static Map<String, Game> GAME_DATA = new HashMap<>();

    @Override
    public Game save(Game game) {
        UUID uuid = GameUtils.getUniqueUUID(GAME_DATA);
        game.setId(uuid.toString());
        GAME_DATA.put(game.getId(), game);
        return game;
    }

    @Override
    public Game get(String id) {
        return GAME_DATA.get(id);
    }

    @Override
    public Game update(Game game) {
        GAME_DATA.put(game.getId(), game);
        return game;
    }


}
