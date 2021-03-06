package com.classicgames.minesweeper.coreapi.repositories;

import com.classicgames.minesweeper.coreapi.entities.Board;

public interface BoardRepository {

    Board create(int size, int numMines);

    Board get(String id);

    Board update(Board board);

}
