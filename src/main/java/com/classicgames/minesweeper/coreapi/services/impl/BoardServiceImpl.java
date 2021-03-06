package com.classicgames.minesweeper.coreapi.services.impl;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.entities.FlagType;
import com.classicgames.minesweeper.coreapi.entities.Square;
import com.classicgames.minesweeper.coreapi.entities.ValueType;
import com.classicgames.minesweeper.coreapi.repositories.BoardRepository;
import com.classicgames.minesweeper.coreapi.services.BoardService;
import com.classicgames.minesweeper.coreapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.classicgames.minesweeper.coreapi.utils.BoardUtils.isValidAccess;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repository;

    private int[] dirRow = {-1, 0, 1, 1, 1, 0, -1, -1};
    private int[] dirCol = {1, 1, 1, 0, -1, -1, -1, 0};

    @Override
    public Board createBoard(int size, int numMines) {
        Board newBoard = new Board(size, numMines);
        placeMines(newBoard);
        placeNumbers(newBoard);
        return repository.save(newBoard);
    }

    @Override
    public Board flagSquare(String boardId, int row, int col) {
        Board board = repository.get(boardId);

        // if the square is already opened no other actions are allowed
        if (board.getMatrix()[row][col].isOpened()) return board;

        FlagType currentFlag = board.getMatrix()[row][col].getFlag();
        switch (currentFlag) {
            case FLAG:
                board.getMatrix()[row][col].setFlag(FlagType.QUESTION);
                break;
            case QUESTION:
                board.getMatrix()[row][col].setFlag(FlagType.NONE);
                break;
            case NONE:
                board.getMatrix()[row][col].setFlag(FlagType.FLAG);
                break;
        }
        return repository.update(board);
    }

    @Override
    public Board revealSquare(String boardId, int row, int col) {
        Board board = repository.get(boardId);
        FlagType currentFlag = board.getMatrix()[row][col].getFlag();

        // if the square is flagged no other actions are allowed
        if (FlagType.FLAG == currentFlag || FlagType.QUESTION == currentFlag) return board;

        if (isAMine(board.getMatrix()[row][col])) {
            revealAllBoard(board);
            board.setHappy(false);
        } else if (board.getMatrix()[row][col].getValue() == 0) {
            revealAllAround(board, row, col);
        } else {
            board.getMatrix()[row][col].setOpened(true);
        }

        return repository.update(board);
    }

    private void placeMines(Board board) {
        int minesToPlace = board.getNumMines();
        while (minesToPlace > 0) {
            int row = Utils.getRandomInt(0, board.getSize());
            int col = Utils.getRandomInt(0, board.getSize());
            // put a mine here if it does not contain a value
            if (Objects.isNull(board.getMatrix()[row][col])) {
                board.getMatrix()[row][col] = new Square(ValueType.MINE);
                minesToPlace--;
            }
        }
    }

    private void placeNumbers(Board board) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                // put a number here if it does not contain a value
                if (Objects.isNull(board.getMatrix()[row][col])) {
                    int totalMines = countMinesAround(board, row, col);
                    Square number = new Square(ValueType.NUMBER);
                    number.setValue(totalMines);
                    board.getMatrix()[row][col] = number;
                }
            }
        }
    }

    private int countMinesAround(Board board, int row, int col) {
        int mineCount = 0;
        for (int i = 0; i < 8; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (isValidAccess(newRow, newCol, board.getSize(), board.getSize())
                    && Objects.nonNull(board.getMatrix()[newRow][newCol])
                    && isAMine(board.getMatrix()[newRow][newCol])) {
                mineCount++;
            }
        }
        return mineCount;
    }

    private void revealAllBoard(Board board) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.getMatrix()[row][col].setOpened(true);
            }
        }
    }

    private void revealAllAround(Board board, int row, int col) {
        if (board.getMatrix()[row][col].isOpened()) return;

        board.getMatrix()[row][col].setOpened(true);

        for (int i = 0; i < 8; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (isValidAccess(newRow, newCol, board.getSize(), board.getSize())) {
                if (board.getMatrix()[row][col].getValue() == 0) {
                    revealAllAround(board, newRow, newCol);
                } else {
                    board.getMatrix()[row][col].setOpened(true);
                }
            }
        }
    }

    private boolean isAMine(Square square) {
        return square.getType() == ValueType.MINE;
    }

}
