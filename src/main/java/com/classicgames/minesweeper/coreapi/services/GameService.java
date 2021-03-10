package com.classicgames.minesweeper.coreapi.services;

import com.classicgames.minesweeper.coreapi.entities.Game;
import com.classicgames.minesweeper.coreapi.entities.GameInfo;

import java.util.List;

public interface GameService {

    Game createGame(int boardSize, int numMines);

    Game flagSquare(String gameId, int row, int col);

    Game revealSquare(String gameId, int row, int col);

    Game getGame(String id);

    List<GameInfo> getAll();

    List<GameInfo> saveGame(String id, GameInfo gameInfo);

    List<GameInfo> deleteGame(String id);
}
