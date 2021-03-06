package com.classicgames.minesweeper.coreapi.repositories.impl;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.repositories.BoardRepository;
import com.classicgames.minesweeper.coreapi.utils.BoardUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    public static Map<String, Board> BOARDS_DATA = new HashMap<>();

    @Override
    public Board save(Board board) {
        UUID uuid = BoardUtils.getUniqueUUID(BOARDS_DATA);
        board.setId(uuid.toString());
        BOARDS_DATA.put(board.getId(), board);
        return board;
    }

    @Override
    public Board get(String id) {
        return BOARDS_DATA.get(id);
    }

    @Override
    public Board update(Board board) {
        BOARDS_DATA.put(board.getId(), board);
        return board;
    }


}
