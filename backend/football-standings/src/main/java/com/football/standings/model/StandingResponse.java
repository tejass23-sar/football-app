package com.football.standings.model;


public class StandingResponse {
    private Country country;
    private League league;
    private Team team;
    private int overallPosition;

    public StandingResponse() {}

    public StandingResponse(Country country, League league, Team team, int overallPosition) {
        this.country = country;
        this.league = league;
        this.team = team;
        this.overallPosition = overallPosition;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getOverallPosition() {
        return overallPosition;
    }

    public void setOverallPosition(int overallPosition) {
        this.overallPosition = overallPosition;
    }
}

