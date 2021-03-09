package com.classicgames.minesweeper.coreapi.repositories;

import com.classicgames.minesweeper.coreapi.entities.Game;

import java.util.List;

public interface GameRepository {

    Game save(Game game);

    Game get(String id);

    List<Game> getAll();

    Game update(Game game);

}
