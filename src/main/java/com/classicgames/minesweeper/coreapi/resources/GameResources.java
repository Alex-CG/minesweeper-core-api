package com.classicgames.minesweeper.coreapi.resources;

import com.classicgames.minesweeper.coreapi.entities.Game;
import com.classicgames.minesweeper.coreapi.entities.GameInfo;
import com.classicgames.minesweeper.coreapi.services.GameService;
import static com.classicgames.minesweeper.coreapi.utils.GameUtils.cleanGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/games")
public class GameResources {

    @Autowired
    private GameService service;

    private final int DEFAULT_BOARD_SIZE = 10;
    private final int DEFAULT_NUM_MINES = 25;

    @PostMapping
    public ResponseEntity<Game> newGame() {
        Game game = service.createGame(DEFAULT_BOARD_SIZE, DEFAULT_NUM_MINES);
        return ResponseEntity.ok(cleanGame(game));
    }

    @GetMapping
    public ResponseEntity<List<GameInfo>> getGames() {
        List<GameInfo> gameInfo = service.getAll();
        return ResponseEntity.ok(gameInfo);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Game> getGames(@PathVariable("id") String id) {
        Game game = service.getGame(id);
        return Objects.nonNull(game) ? ResponseEntity.ok(cleanGame(game)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<List<GameInfo>> deleteGame(@PathVariable("id") String id) {
        Game game = service.getGame(id);
        if (Objects.isNull(game)) return ResponseEntity.notFound().build();
        List<GameInfo> gamesInfo = service.deleteGame(game.getId());
        return ResponseEntity.ok(gamesInfo);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<List<GameInfo>> saveGame(@PathVariable("id") String id,
                                         @RequestBody GameInfo gameInfo) {
        List<GameInfo> gamesInfo = service.saveGame(id, gameInfo);
        return ResponseEntity.ok(gamesInfo);
    }

    @PatchMapping(path = "/{id}/flag/{row}/{col}")
    public ResponseEntity<Game> flagSquare(@PathVariable("id") String id,
                                           @PathVariable("row") Integer row,
                                           @PathVariable("col") Integer col) {
        Game game = service.flagSquare(id, row, col);
        return ResponseEntity.ok(cleanGame(game));
    }

    @PatchMapping(path = "/{id}/reveal/{row}/{col}")
    public ResponseEntity<Game> revealSquare(@PathVariable("id") String id,
                                             @PathVariable("row") Integer row,
                                             @PathVariable("col") Integer col) {
        Game game = service.revealSquare(id, row, col);
        return ResponseEntity.ok(cleanGame(game));
    }

}
