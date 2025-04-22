package com.football.standings.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.football.standings.iface.FootballService;
import com.football.standings.model.FootballStandings;
import com.football.standings.model.Country;
import com.football.standings.model.League;
import com.football.standings.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballServiceImpl implements FootballService {

    private static final Logger logger = LoggerFactory.getLogger(FootballServiceImpl.class);

    @Value("${api.football.key}")
    private String apiKey;
    
    @Value("${api.football.url}")
    private String apiUrl; 

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public FootballServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    private String buildUrl(String action, String... additionalParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("action", action)
                .queryParam("APIkey", apiKey);

        for (int i = 0; i < additionalParams.length; i += 2) {
            uriBuilder.queryParam(additionalParams[i], additionalParams[i + 1]);
        }

        return uriBuilder.toUriString();
    }

    private <T> List<T> fetchData(String url, ParameterizedTypeReference<List<T>> typeReference) {
        try {
            // Pass empty HttpEntity since we don't need a body or headers for a GET request
            ResponseEntity<List<T>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(null), // Empty HttpEntity for GET request
                    typeReference
            );

            List<T> body = response.getBody();
            if (body == null || body.isEmpty()) {
                logger.warn("No data received for URL: {}", url);
                return List.of(); // Return empty list if no data
            }
            return body;
        } catch (Exception e) {
            logger.error("Error fetching data from API. URL: {}", url, e);
            return List.of(); // Return empty list on failure
        }
    }

    @Override
    @Cacheable("standings")
    public List<FootballStandings> getFilteredStandings(String country, String league, String team) {
        // If all filters are null, log and fetch all standings
        if (country == null && league == null && team == null) {
            logger.info("Fetching all standings (no filters applied).");
        }

        String url = buildUrl("get_standings", "league_id", league); // Can parameterize the league_id as needed
        List<FootballStandings> allStandings = fetchData(url, new ParameterizedTypeReference<List<FootballStandings>>() {});

        logger.info("Tejas: "+allStandings);

        return allStandings.stream()
                .filter(standing ->
                        (country == null || standing.getCountryName().equalsIgnoreCase(country)) &&
                        (league == null || standing.getLeagueId().equalsIgnoreCase(league)) &&
                        (team == null || standing.getTeamName().equalsIgnoreCase(team))
                )
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("countries")
    public List<Country> getCountries() {
        String url = buildUrl("get_countries");
        return fetchData(url, new ParameterizedTypeReference<List<Country>>() {});
    }

    @Override
    @Cacheable("leagues")
    public List<League> getLeagues(String country) {
        String url = buildUrl("get_leagues");
        List<League> leagues = fetchData(url, new ParameterizedTypeReference<List<League>>() {});

        return leagues.stream()
                .filter(league -> league.getCountryName().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("teams")
    public List<Team> getTeams(String league) {
        String url = buildUrl("get_standings", "league_id", league);
        return fetchData(url, new ParameterizedTypeReference<List<Team>>() {});
    }

    @Override
    public String getStandings(String country, String league, String team) {
        // Placeholder for future implementation
        return null;
    }
}
