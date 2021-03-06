package com.classicgames.minesweeper.coreapi.repositories.impl;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.entities.Square;
import com.classicgames.minesweeper.coreapi.entities.ValueType;
import com.classicgames.minesweeper.coreapi.repositories.BoardRepository;
import com.classicgames.minesweeper.coreapi.utils.BoardUtils;
import com.classicgames.minesweeper.coreapi.utils.Utils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.classicgames.minesweeper.coreapi.utils.BoardUtils.isValidAccess;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    public static Map<String, Board> BOARDS_DATA = new HashMap<>();

    @Override
    public Board create(int size, int numMines) {
        UUID uuid = BoardUtils.getUniqueUUID(BOARDS_DATA);
        Board board = new Board(uuid.toString(), size, numMines);
        BOARDS_DATA.put(uuid.toString(), board);
        placesMines(board);
        fillNumbers(board);
        BOARDS_DATA.put(uuid.toString(), board);
        return board;
    }

    @Override
    public Board get(String id) {
        return BOARDS_DATA.get(id);
    }

    @Override
    public Board update(Board board) {
        BOARDS_DATA.put(board.getId(), board);
        return board;
    }


    private void placesMines(Board board) {
        int minesToPlace = board.getNumMines();
        while (minesToPlace > 0) {
            int x = Utils.getRandomInt(0, board.getSize());
            int y = Utils.getRandomInt(0, board.getSize());
            // put a mine here if it does not contain a value
            if (Objects.isNull(board.getMatrix()[x][y])) {
                board.getMatrix()[x][y] = new Square(ValueType.MINE);
                minesToPlace--;
            }
        }
    }

    private void fillNumbers(Board board) {
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                // put a number here if it does not contain a value
                if (Objects.isNull(board.getMatrix()[x][y])) {
                    int totalMines = countMinesAround(board, x, y);
                    Square number = new Square(ValueType.NUMBER);
                    number.setValue(totalMines);
                    board.getMatrix()[x][y] = number;
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
                    && Objects.nonNull(board.getMatrix()[newX][newY])
                    && isAMine(board.getMatrix()[newX][newY])) {
                mineCount++;
            }
        }
        return mineCount;
    }

    private boolean isAMine(Square square) {
        return square.getType() == ValueType.MINE;
    }

}
