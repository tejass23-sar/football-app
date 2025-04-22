# Football Standings Project

## Overview
This project provides an API for fetching football standings, teams, leagues, and countries using the [API Football](https://apiv3.apifootball.com/) service. The frontend provides an interface to view the standings of various football leagues, filter by country, league, and team, and optionally work in offline mode for resilience during API downtime.

## Design and Implementation Approach

### Backend (Spring Boot)
- **FootballService**: The service layer interacts with the external API to fetch the football data. This includes countries, leagues, teams, and standings.
- **Caching**: Caching is implemented using `@Cacheable` annotations to store results of the fetched data to reduce external API calls.
- **Error Handling**: In case of failure to fetch data from the API, the backend handles errors and returns empty lists.
- **API Endpoints**:
  - `/api/v1/countries`: Fetches a list of countries.
  - `/api/v1/leagues`: Fetches leagues for a given country.
  - `/api/v1/teams`: Fetches teams for a given league.
  - `/api/v1/standings`: Fetches standings for a given country, league, and team.
- **Offline Mode**: Caching is leveraged for supporting offline functionality. Data previously fetched is available even if the API is not reachable.

### Frontend (React)
- **User Interface**: The frontend is a simple form-based UI where users can select a country, league, and team to fetch football standings.
- **Offline Mode**: When the "Offline Mode" toggle is enabled, the app will use the cached data if the public API is unavailable.
- **API Calls**: API calls to the backend are made using Axios. In case of failure to fetch data from the backend, the offline mode serves the previously cached results.

### Technologies Used:
- **Backend**: Spring Boot, RestTemplate, Cacheable
- **Frontend**: React, Axios
- **Database**: No database used (Data is fetched from the external API and cached in-memory)
- **Caching**: Spring's built-in caching mechanism
- **UI**: Simple forms and dropdowns with state management in React

## Offline Mode Limitations
- Offline Mode will only work if data has been previously fetched and stored in cache. If the application is running for the first time or if the cache is empty and the service is down, offline mode will not work.
- **Known API Limitation**: The external API (apiv3.apifootball.com) only returns a limited number of countries (currently only England and France are available), and similarly, the leagues data is also incomplete.

## Known Issues from provided APIS of (https://apifootball.com/documentation/)
1. **Countries API**: The `get_countries` endpoint only returns a limited number of countries (currently just two: England and France). This is a limitation of the external API, not the project itself.
2. **Leagues API**: The `getLeagues` endpoint also returns incomplete data and does not include all leagues.
3. **Offline Mode**: Offline mode works only if data has been fetched previously. If the service is down on the first run and no data is cached, the app will fail to fetch data.


## Design Patterns Used
- **Singleton Pattern**: The Spring service class `FootballServiceImpl` is a Singleton and is managed by the Spring container.
- **Factory Pattern**: Not explicitly required or used in this project as the API interactions are simple and do not involve complex object creation.
- **Strategy Pattern**: The project does not implement a strategy pattern in the backend. However, the offline mode toggle functionality can be considered an optional behavior to change the way data is fetched.

## How to Run the Project

### Backend (Spring Boot)
1. Clone the repository.
2. Set up your application properties with your own API keys in `application.properties`:
   ```properties
   api.football.key=YOUR_API_KEY
   api.football.url=https://apiv3.apifootball.com/
#Run the Spring Boot application:
	mvn spring-boot:run
	The API will be available at http://localhost:8080.
	
#Frontend (React)
Clone the repository.
 Navigate to the frontend directory 
 npm start
The frontend will be available at http://localhost:3000.
