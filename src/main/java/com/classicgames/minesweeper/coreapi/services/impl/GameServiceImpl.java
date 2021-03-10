package com.classicgames.minesweeper.coreapi.services.impl;

import com.classicgames.minesweeper.coreapi.entities.*;
import com.classicgames.minesweeper.coreapi.repositories.GameRepository;
import com.classicgames.minesweeper.coreapi.services.GameService;
import static com.classicgames.minesweeper.coreapi.utils.BoardUtils.isValidAccess;
import com.classicgames.minesweeper.coreapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                game.setNumFlags(game.getNumFlags() - 1);
                break;
            case QUESTION:
                game.getBoard()[row][col].setFlag(FlagType.NONE);
                break;
            case NONE:
                if (game.getNumFlags() == game.getNumMines()) return game;
                game.getBoard()[row][col].setFlag(FlagType.FLAG);
                game.setNumFlags(game.getNumFlags() + 1);
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
            game.getBoard()[row][col].setWrong(true);
            game.setFace(FaceType.SAD);
            revealAllMines(game);
        } else if (game.getBoard()[row][col].getValue() == 0) {
            revealAllAround(game, row, col);
        } else {
            game.getBoard()[row][col].setOpened(true);
            game.setOpenings(game.getOpenings() + 1);
            hasWon(game);
        }

        return repository.update(game);
    }

    @Override
    public Game getGame(String id) {
        return repository.get(id);
    }

    @Override
    public List<GameInfo> getAll() {
        List<GameInfo> gamesInfo = new ArrayList<>();
        List<Game> games = repository.getAll();

        games.forEach(game -> {
            if (Objects.nonNull(game.getName())) {
                gamesInfo.add(new GameInfo(game.getId(), game.getName()));
            }
        });

        return gamesInfo;
    }

    @Override
    public List<GameInfo> saveGame(String id, GameInfo gameInfo) {
        Game game = repository.get(id);
        game.setName(gameInfo.getName());
        repository.update(game);
        return getAll();
    }

    @Override
    public List<GameInfo> deleteGame(String id) {
        repository.delete(id);
        return getAll();
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

    private void revealAllMines(Game game) {
        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                if (isAMine(game.getBoard()[row][col])) {
                    game.getBoard()[row][col].setOpened(true);
                }
            }
        }
    }

    private void revealAllAround(Game game, int row, int col) {
        if (game.getBoard()[row][col].isOpened()) return;

        game.getBoard()[row][col].setOpened(true);
        game.setOpenings(game.getOpenings() + 1);
        hasWon(game);

        for (int i = 0; i < 8; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (isValidAccess(newRow, newCol, game.getSize(), game.getSize())) {
                if (game.getBoard()[row][col].getValue() == 0) {
                    revealAllAround(game, newRow, newCol);
                }
            }
        }
    }

    private void hasWon(Game game) {
        if (game.getSize() * game.getSize() - game.getOpenings() == game.getNumMines()) {
            game.setFace(FaceType.EXCITED);
        }
    }

    private boolean isAMine(Square square) {
        return square.getType() == ValueType.MINE;
    }

}
