package com.classicgames.minesweeper.coreapi;

import com.classicgames.minesweeper.coreapi.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinesweeperCoreApiApplication {

	@Autowired
	private BoardService service;

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperCoreApiApplication.class, args);
	}

}
