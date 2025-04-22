package com.football.standings.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FootballStandings {

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("league_id")
    private String leagueId;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("team_id")
    private String teamId;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("overall_promotion")
    private String overallPromotion;

    @JsonProperty("overall_league_position")
    private String overallLeaguePosition;

    @JsonProperty("overall_league_payed")
    private String overallLeaguePlayed;

    @JsonProperty("overall_league_W")
    private String overallLeagueW;

    @JsonProperty("overall_league_D")
    private String overallLeagueD;

    @JsonProperty("overall_league_L")
    private String overallLeagueL;

    @JsonProperty("overall_league_GF")
    private String overallLeagueGF;

    @JsonProperty("overall_league_GA")
    private String overallLeagueGA;

    @JsonProperty("overall_league_PTS")
    private String overallLeaguePTS;

    @JsonProperty("home_league_position")
    private String homeLeaguePosition;

    @JsonProperty("home_promotion")
    private String homePromotion;

    @JsonProperty("home_league_payed")
    private String homeLeaguePlayed;

    @JsonProperty("home_league_W")
    private String homeLeagueW;

    @JsonProperty("home_league_D")
    private String homeLeagueD;

    @JsonProperty("home_league_L")
    private String homeLeagueL;

    @JsonProperty("home_league_GF")
    private String homeLeagueGF;

    @JsonProperty("home_league_GA")
    private String homeLeagueGA;

    @JsonProperty("home_league_PTS")
    private String homeLeaguePTS;

    @JsonProperty("away_league_position")
    private String awayLeaguePosition;

    @JsonProperty("away_promotion")
    private String awayPromotion;

    @JsonProperty("away_league_payed")
    private String awayLeaguePlayed;

    @JsonProperty("away_league_W")
    private String awayLeagueW;

    @JsonProperty("away_league_D")
    private String awayLeagueD;

    @JsonProperty("away_league_L")
    private String awayLeagueL;

    @JsonProperty("away_league_GF")
    private String awayLeagueGF;

    @JsonProperty("away_league_GA")
    private String awayLeagueGA;

    @JsonProperty("away_league_PTS")
    private String awayLeaguePTS;

    @JsonProperty("league_round")
    private String leagueRound;

    @JsonProperty("team_badge")
    private String teamBadge;

    @JsonProperty("fk_stage_key")
    private String fkStageKey;

    @JsonProperty("stage_name")
    private String stageName;
}
