package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Square {

    private ValueType type;
    private int value;
    private boolean opened;
    private FlagType flag = FlagType.NONE;
    private int row;
    private int col;
    private boolean wrong;

    public Square(ValueType type) {
        this.type = type;
    }

}
