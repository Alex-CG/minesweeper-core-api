package com.classicgames.minesweeper.coreapi.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SpringfoxConfig.class, CorsConfig.class})
public class ApiConfig {
}
