package com.classicgames.minesweeper.coreapi.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void canCreateA10Size20MinesGameCorrectlyTest() {
        int boardSize = 10;
        int numMines = 20;

        Game game = new Game(boardSize, numMines);

        Assertions.assertEquals(10, game.getSize());
        Assertions.assertEquals(20, game.getNumMines());
        Assertions.assertEquals(10, game.getBoard().length);
        Assertions.assertEquals(10, game.getBoard()[0].length);
        Assertions.assertTrue(game.isHappy());
        Assertions.assertNull(game.getId());
    }

    @Test
    public void canCreateA20Size40MinesGameCorrectlyTest() {
        int boardSize = 20;
        int numMines = 40;

        Game game = new Game(boardSize, numMines);

        Assertions.assertEquals(20, game.getSize());
        Assertions.assertEquals(40, game.getNumMines());
        Assertions.assertEquals(20, game.getBoard().length);
        Assertions.assertEquals(20, game.getBoard()[0].length);
        Assertions.assertTrue(game.isHappy());
        Assertions.assertNull(game.getId());
    }

}
