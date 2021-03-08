package com.classicgames.minesweeper.coreapi.repositories;

import com.classicgames.minesweeper.coreapi.entities.Game;

public interface GameRepository {

    Game save(Game game);

    Game get(String id);

    Game update(Game game);

}
