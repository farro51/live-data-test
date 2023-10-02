package com.sportradar.liveData.demo.adapters;

import com.sportradar.liveData.demo.model.Match;
import com.sportradar.liveData.demo.model.Team;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardInMemoryAdapter {

    private List<Match> matches;

    public ScoreBoardInMemoryAdapter() {
        matches = new ArrayList<>();
    }
    public List<Match> getMatches() {
        return matches;
    }

    public Match startGame(Team homeTeam, Team awayTeam) {
        if (!isStartGameParametersValid(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("Provide a valid home and away team");
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    private boolean isStartGameParametersValid(Team homeTeam, Team awayTeam) {
        return (homeTeam != null && awayTeam != null);
    }
}
