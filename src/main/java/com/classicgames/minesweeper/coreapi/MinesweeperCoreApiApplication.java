package com.classicgames.minesweeper.coreapi;

import com.classicgames.minesweeper.coreapi.configs.ApiConfig;
import com.classicgames.minesweeper.coreapi.configs.CorsConfig;
import com.classicgames.minesweeper.coreapi.configs.SpringfoxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Import(ApiConfig.class)
public class MinesweeperCoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperCoreApiApplication.class, args);
	}

}
