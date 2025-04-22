package com.football.standings.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Team {
    @JsonProperty("team_key")
    private String teamId;      // Renaming team_key to teamId
    
    @JsonProperty("team_name")
    private String teamName;
    
    @JsonProperty("team_country")
    private String teamCountry;
    
    @JsonProperty("team_founded")
    private String teamFounded;
}
