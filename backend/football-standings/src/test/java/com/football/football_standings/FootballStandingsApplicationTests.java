package com.football.football_standings;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.football.standings.FootballStandingsApplication;

@SpringBootTest
@ContextConfiguration(classes = FootballStandingsApplication.class)  // Explicitly specify the main class
class FootballStandingsApplicationTests {

    @Test
    void contextLoads() {
    }
}
