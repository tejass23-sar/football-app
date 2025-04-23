import React, { useState, useEffect } from 'react';
import axios from 'axios';

const BASE_URL = process.env.REACT_APP_BACKEND_URL;

const Standings = () => {
    const [country, setCountry] = useState('');
    const [league, setLeague] = useState('');
    const [leagueId, setLeagueId] = useState('');
    const [team, setTeam] = useState('');
    const [standings, setStandings] = useState([]);
    const [countries, setCountries] = useState([]);
    const [leagues, setLeagues] = useState([]);
    const [teams, setTeams] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [offlineMode, setOfflineMode] = useState(false);

    useEffect(() => {
        const fetchCountries = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/api/v1/countries`);
                setCountries(response.data);
            } catch (err) {
                setError('Failed to fetch countries. Service is down');
            }
        };
        fetchCountries();
    }, []);

    useEffect(() => {
        if (country) {
            const fetchLeagues = async () => {
                try {
                    const response = await axios.get(`${BASE_URL}/api/v1/leagues`, {
                        params: { country },
                    });
                    setLeagues(response.data);
                    setLeague('');
                    setLeagueId('');
                    setTeam('');
                } catch (err) {
                    setError('Failed to fetch leagues.');
                }
            };
            fetchLeagues();
        }
    }, [country]);

    useEffect(() => {
        if (leagueId) {
            const fetchTeams = async () => {
                try {
                    const response = await axios.get(`${BASE_URL}/api/v1/teams`, {
                        params: { league: leagueId  },
                    });
                    setTeams(response.data);
                    setTeam('');
                } catch (err) {
                    setError('Failed to fetch teams.');
                }
            };
            fetchTeams();
        }
    }, [leagueId]);

    const fetchStandings = async () => {
        setLoading(true);
        setError('');
        try {
            const response = await axios.get(`${BASE_URL}/api/v1/standings`, {
                params: { country, league: leagueId, team },
            });
            setStandings(response.data);
        } catch (err) {
            setError('Failed to fetch data. Please try again later.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container">
            <h2>Football Standings</h2>

            <div style={{ display: 'flex', gap: '1rem', marginBottom: '2rem', flexWrap: 'wrap' }}>
                <select
                    value={country}
                    onChange={(e) => {
                        const newCountry = e.target.value;
                        setCountry(newCountry);
                        setLeagues([]);
                        setLeague('');
                        setLeagueId('');
                        setTeam('');
                        setTeams([]);
                        setStandings([]);
                    }}
                    disabled={!countries.length}
                >
                    <option value="">Select Country</option>
                    {countries.map((c) => (
                        <option key={c.country_id} value={c.country_name}>
                            {c.country_name}
                        </option>
                    ))}
                </select>

                <select
                    value={league}
                    onChange={(e) => {
                        const selectedLeague = leagues.find(
                            (l) => l.league_name === e.target.value
                        );
                        setLeague(e.target.value);
                        setLeagueId(selectedLeague?.league_id || '');
                        setTeam('');
                        setTeams([]);
                    }}
                    disabled={!leagues.length || !country}
                >
                    <option value="">Select League</option>
                    {leagues.map((l) => (
                        <option key={l.league_id} value={l.league_name}>
                            {l.league_name}
                        </option>
                    ))}
                </select>

                <select
                    value={team}
                    onChange={(e) => setTeam(e.target.value)}
                    disabled={!teams.length || !leagueId}
                >
                    <option value="">Select Team</option>
                    {teams.map((t) => (
                        <option key={t.team_key} value={t.team_name}>
                            {t.team_name}
                        </option>
                    ))}
                </select>

                <button onClick={fetchStandings} disabled={!country || !leagueId || !team}>
                    Get Standings
                </button>
            </div>
            <div style={{ display: 'flex', alignItems: 'center', marginBottom: '1rem' }}>
                    <input
                        type="checkbox"
                        checked={offlineMode}
                        onChange={() => setOfflineMode(!offlineMode)}
                    />
                    Offline Mode
            </div>
            {loading && <p>Loading...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            <div>
                {standings.length > 0 ? (
                    <table>
                        <thead>
                            <tr>
                                <th>Country</th>
                                <th>Team</th>
                                <th>Position</th>
                            </tr>
                        </thead>
                        <tbody>
                            {standings.map((s, index) => (
                                <tr key={index}>
                                    <td>{s.country_name}</td>
                                    <td>{s.team_name}</td>
                                    <td>{s.overall_league_position}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>No standings available for the selected filters.</p>
                )}
            </div>
        </div>
    );
};

export default Standings;
