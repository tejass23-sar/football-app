package com.football.standings.iface;

import java.util.List;

import com.football.standings.model.Country;
import com.football.standings.model.FootballStandings;
import com.football.standings.model.League;
import com.football.standings.model.Team;

public interface FootballService {
    String getStandings(String country, String league, String team);
    List<FootballStandings> getFilteredStandings(String country, String league, String team);

    List<Country> getCountries();

    List<League> getLeagues(String country);

    List<Team> getTeams(String league);

}
