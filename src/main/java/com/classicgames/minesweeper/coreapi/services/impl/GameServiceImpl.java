package com.classicgames.minesweeper.coreapi.services.impl;

import com.classicgames.minesweeper.coreapi.entities.Game;
import com.classicgames.minesweeper.coreapi.entities.FlagType;
import com.classicgames.minesweeper.coreapi.entities.Square;
import com.classicgames.minesweeper.coreapi.entities.ValueType;
import com.classicgames.minesweeper.coreapi.repositories.GameRepository;
import com.classicgames.minesweeper.coreapi.services.GameService;
import static com.classicgames.minesweeper.coreapi.utils.BoardUtils.isValidAccess;
import com.classicgames.minesweeper.coreapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    private final int[] dirRow = {-1, 0, 1, 1, 1, 0, -1, -1};
    private final int[] dirCol = {1, 1, 1, 0, -1, -1, -1, 0};

    @Override
    public Game createGame(int boardSize, int numMines) {
        Game newGame = new Game(boardSize, numMines);
        placeMines(newGame);
        placeNumbers(newGame);
        return repository.save(newGame);
    }

    @Override
    public Game flagSquare(String gameId, int row, int col) {
        Game game = repository.get(gameId);

        // if the square is already opened no other actions are allowed
        if (game.getBoard()[row][col].isOpened()) return game;

        FlagType currentFlag = game.getBoard()[row][col].getFlag();
        switch (currentFlag) {
            case FLAG:
                game.getBoard()[row][col].setFlag(FlagType.QUESTION);
                break;
            case QUESTION:
                game.getBoard()[row][col].setFlag(FlagType.NONE);
                break;
            case NONE:
                game.getBoard()[row][col].setFlag(FlagType.FLAG);
                break;
        }
        return repository.update(game);
    }

    @Override
    public Game revealSquare(String gameId, int row, int col) {
        Game game = repository.get(gameId);
        FlagType currentFlag = game.getBoard()[row][col].getFlag();

        // if the square is flagged no other actions are allowed
        if (FlagType.FLAG == currentFlag || FlagType.QUESTION == currentFlag) return game;

        if (isAMine(game.getBoard()[row][col])) {
            revealAllBoard(game);
            game.setHappy(false);
        } else if (game.getBoard()[row][col].getValue() == 0) {
            revealAllAround(game, row, col);
        } else {
            game.getBoard()[row][col].setOpened(true);
        }

        return repository.update(game);
    }

    private void placeMines(Game game) {
        int minesToPlace = game.getNumMines();
        while (minesToPlace > 0) {
            int row = Utils.getRandomInt(0, game.getSize());
            int col = Utils.getRandomInt(0, game.getSize());
            // put a mine here if it does not contain a value
            if (Objects.isNull(game.getBoard()[row][col])) {
                Square mine = new Square(ValueType.MINE);
                mine.setRow(row);
                mine.setCol(col);
                game.getBoard()[row][col] = mine;
                minesToPlace--;
            }
        }
    }

    private void placeNumbers(Game game) {
        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                // put a number here if it does not contain a value
                if (Objects.isNull(game.getBoard()[row][col])) {
                    int totalMines = countMinesAround(game, row, col);
                    Square number = new Square(ValueType.NUMBER);
                    number.setRow(row);
                    number.setCol(col);
                    number.setValue(totalMines);
                    game.getBoard()[row][col] = number;
                }
            }
        }
    }

    private int countMinesAround(Game game, int row, int col) {
        int mineCount = 0;
        for (int i = 0; i < 8; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (isValidAccess(newRow, newCol, game.getSize(), game.getSize())
                    && Objects.nonNull(game.getBoard()[newRow][newCol])
                    && isAMine(game.getBoard()[newRow][newCol])) {
                mineCount++;
            }
        }
        return mineCount;
    }

    private void revealAllBoard(Game game) {
        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                game.getBoard()[row][col].setOpened(true);
            }
        }
    }

    private void revealAllAround(Game game, int row, int col) {
        if (game.getBoard()[row][col].isOpened()) return;

        game.getBoard()[row][col].setOpened(true);

        for (int i = 0; i < 8; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (isValidAccess(newRow, newCol, game.getSize(), game.getSize())) {
                if (game.getBoard()[row][col].getValue() == 0) {
                    revealAllAround(game, newRow, newCol);
                } else {
                    game.getBoard()[row][col].setOpened(true);
                }
            }
        }
    }

    private boolean isAMine(Square square) {
        return square.getType() == ValueType.MINE;
    }

}
