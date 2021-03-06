package com.classicgames.minesweeper.coreapi.services;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    public Board createBoard(int size, int numMines) {
        Board board = new Board(size, numMines);
        placesMines(board);
        fillNumbers(board);
        return board;
    }

    private void placesMines(Board board) {
        int minesToPlace = board.getNumMines();
        while (minesToPlace > 0) {
            int x = Utils.getRandomInt(0, board.getSize());
            int y = Utils.getRandomInt(0, board.getSize());
            // put a mine here if it does not contain a mine
            if (board.getMatrix()[x][y] != -1) {
                board.getMatrix()[x][y] = -1;
                minesToPlace--;
            }
        }
    }

    private void fillNumbers(Board board) {
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                // put a number here if it does not contain a mine
                if (board.getMatrix()[x][y] != -1) {
                    int totalMines = countMinesAround(board, x, y);
                    board.getMatrix()[x][y] = totalMines;
                }
            }
        }
    }

    private int countMinesAround(Board board, int x, int y) {
        int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
        int mineCount = 0;

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (isValidAccess(newX, newY, board.getSize(), board.getSize())
                    && board.getMatrix()[newX][newY] == -1) {
                mineCount++;
            }
        }
        return mineCount;
    }

    private boolean isValidAccess(int x, int y, int n, int m) {
        return (x >= 0 && y >= 0 && x < n && y < m);
    }

}
