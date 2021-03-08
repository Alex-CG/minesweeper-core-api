package com.classicgames.minesweeper.coreapi;

import com.classicgames.minesweeper.coreapi.configs.ApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApiConfig.class)
public class MinesweeperCoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperCoreApiApplication.class, args);
	}

}
