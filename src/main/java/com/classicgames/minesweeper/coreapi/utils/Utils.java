package com.classicgames.minesweeper.coreapi.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class Utils {

    private Utils() { }

    public static int getRandomInt(int minInclusive, int maxExclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive);
    }

}
