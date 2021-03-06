package com.classicgames.minesweeper.coreapi;

import com.classicgames.minesweeper.coreapi.entities.Board;
import com.classicgames.minesweeper.coreapi.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MinesweeperCoreApiApplication implements CommandLineRunner {

	@Autowired
	private BoardService service;

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperCoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createBoard();
	}

	private void createBoard() {
		int boardSize = 10;
		int numMines = 30;
		Board myBoard = service.createBoard(boardSize, numMines);
		System.out.println(Arrays.deepToString(myBoard.getMatrix()));
	}
}
