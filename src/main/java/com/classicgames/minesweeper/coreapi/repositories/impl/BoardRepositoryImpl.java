package com.classicgames.minesweeper.coreapi.repositories.impl;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.repositories.BoardRepository;
import com.classicgames.minesweeper.coreapi.utils.BoardUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    public static Map<String, Board> BOARDS_DATA = new HashMap<>();

    @Override
    public Board create(int size, int numMines) {
        UUID uuid = BoardUtils.getUniqueUUID(BOARDS_DATA);
        Board board = new Board(uuid.toString(), size, numMines);
        BOARDS_DATA.put(uuid.toString(), board);
        return board;
    }

}
