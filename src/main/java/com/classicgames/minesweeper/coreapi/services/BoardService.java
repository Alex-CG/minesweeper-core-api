package com.classicgames.minesweeper.coreapi.services;

import com.classicgames.minesweeper.coreapi.entities.Board;

public interface BoardService {

    Board createBoard(int size, int numMines);

    Board flagSquare(String boardId, int posX, int posY);

    Board revealSquare(String boardId, int posX, int posY);
}
