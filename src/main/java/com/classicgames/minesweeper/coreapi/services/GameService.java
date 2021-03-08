package com.classicgames.minesweeper.coreapi.services;

import com.classicgames.minesweeper.coreapi.entities.Game;

public interface GameService {

    Game createGame(int boardSize, int numMines);

    Game flagSquare(String gameId, int row, int col);

    Game revealSquare(String gameId, int row, int col);
}
