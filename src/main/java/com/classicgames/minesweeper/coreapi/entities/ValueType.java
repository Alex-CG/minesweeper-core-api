package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;

public enum ValueType {

    MINE("mine"), NUMBER("number");

    @Getter
    private String type;

    private ValueType(String type) {
        this.type = type;
    }

}
