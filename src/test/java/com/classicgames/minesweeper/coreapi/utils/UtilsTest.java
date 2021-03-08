package com.classicgames.minesweeper.coreapi.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void canGetAValidRandomIntTest() {
        int minValueInclusive = 0;
        int maxValueExclusive = 10;

        int randomValue = Utils.getRandomInt(minValueInclusive, maxValueExclusive);

        Assertions.assertTrue(randomValue >= 0);
        Assertions.assertTrue(randomValue < 10);
    }

}
