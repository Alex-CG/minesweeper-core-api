package com.classicgames.minesweeper.coreapi.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardUtilsTest {

    @Test
    public void canValidateATopLeftAccessTest() {
        int row = 0;
        int col = 0;
        int rows = 5;
        int cols = 4;

        boolean isValid = BoardUtils.isValidAccess(row, col, rows, cols);

        Assertions.assertTrue(isValid);
    }

    @Test
    public void canValidateABottomRightAccessTest() {
        int row = 4;
        int col = 3;
        int rows = 5;
        int cols = 4;

        boolean isValid = BoardUtils.isValidAccess(row, col, rows, cols);

        Assertions.assertTrue(isValid);
    }

    @Test
    public void canInvalidateAOutOfBoundAccessTest() {
        int row = 6;
        int col = 7;
        int rows = 6;
        int cols = 7;

        boolean isValid = BoardUtils.isValidAccess(row, col, rows, cols);

        Assertions.assertFalse(isValid);
    }

    @Test
    public void canInvalidateAOutOfBound2AccessTest() {
        int row = -1;
        int col = -1;
        int rows = 2;
        int cols = 3;

        boolean isValid = BoardUtils.isValidAccess(row, col, rows, cols);

        Assertions.assertFalse(isValid);
    }

}
