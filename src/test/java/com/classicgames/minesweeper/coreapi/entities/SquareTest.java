package com.classicgames.minesweeper.coreapi.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    public void canCreateANumberSquareCorrectlyTest() {
        ValueType numberType = ValueType.NUMBER;

        Square square = new Square(numberType);
        square.setValue(2);
        square.setRow(5);
        square.setCol(3);

        Assertions.assertEquals(ValueType.NUMBER, square.getType());
        Assertions.assertEquals(2, square.getValue());
        Assertions.assertEquals(5, square.getRow());
        Assertions.assertEquals(3, square.getCol());
        Assertions.assertEquals(FlagType.NONE, square.getFlag());
        Assertions.assertFalse(square.isOpened());
    }

    @Test
    public void canCreateAMineSquareAtRow4Col5CorrectlyTest() {
        ValueType mineType = ValueType.MINE;

        Square square = new Square(mineType);
        square.setRow(4);
        square.setCol(5);

        Assertions.assertEquals(ValueType.MINE, square.getType());
        Assertions.assertEquals(4, square.getRow());
        Assertions.assertEquals(5, square.getCol());
        Assertions.assertEquals(FlagType.NONE, square.getFlag());
        Assertions.assertFalse(square.isOpened());
    }

}
