package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Square {

    private ValueType type;
    private int value;
    private boolean openned;
    private FlagType flag = FlagType.NONE;

    public Square(ValueType type) {
        this.type = type;
    }

}
