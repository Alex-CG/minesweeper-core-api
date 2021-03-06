package com.classicgames.minesweeper.coreapi.services.impl;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.entities.FlagType;
import com.classicgames.minesweeper.coreapi.repositories.BoardRepository;
import com.classicgames.minesweeper.coreapi.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repository;

    @Override
    public Board createBoard(int size, int numMines) {
        return repository.create(size, numMines);
    }

    @Override
    public Board flagSquare(String boardId, int posX, int posY) {
        Board board = repository.get(boardId);
        FlagType currentFlag = board.getMatrix()[posX][posY].getFlag();
        switch (currentFlag) {
            case FLAG:
                board.getMatrix()[posX][posY].setFlag(FlagType.QUESTION);
                break;
            case QUESTION:
                board.getMatrix()[posX][posY].setFlag(FlagType.NONE);
                break;
            case NONE:
                board.getMatrix()[posX][posY].setFlag(FlagType.FLAG);
                break;
        }
        return repository.update(board);
    }

}
