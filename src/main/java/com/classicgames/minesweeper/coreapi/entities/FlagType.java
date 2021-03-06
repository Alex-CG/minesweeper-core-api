package com.classicgames.minesweeper.coreapi.entities;

import lombok.Getter;

public enum FlagType {

    FLAG("flag"), QUESTION("question"), NONE("none");

    @Getter
    private String type;

    private FlagType(String type) {
        this.type = type;
    }

}
