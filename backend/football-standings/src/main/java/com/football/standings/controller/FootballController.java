package com.football.standings.controller;

import com.football.standings.iface.FootballService;
import com.football.standings.model.FootballStandings;
import com.football.standings.model.Country;
import com.football.standings.model.League;
import com.football.standings.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")  // Version 1 of the API
public class FootballController {

    private final FootballService footballService;

    @Autowired
    public FootballController(FootballService footballService) {
        this.footballService = footballService;
    }

    @GetMapping("/standings")
    public List<FootballStandings> getStandings(
            @RequestParam(required = true) String country,
            @RequestParam(required = true) String league,
            @RequestParam(required = false) String team) {
        return footballService.getFilteredStandings(country, league, team);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(footballService.getCountries());
    }

    @GetMapping("/leagues")
    public ResponseEntity<List<League>> getLeagues(@RequestParam String country) {
        return ResponseEntity.ok(footballService.getLeagues(country));
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getTeams(@RequestParam String league) {
        return ResponseEntity.ok(footballService.getTeams(league));
    }
}
