package com.football.standings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Football Standings API", version = "v1", description = "API for fetching football standings"))
public class FootballStandingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballStandingsApplication.class, args);
	}

}
