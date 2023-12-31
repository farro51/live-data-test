package com.sportradar.liveData.demo.adapters;

import com.sportradar.liveData.demo.helpers.TotalScoreComparator;
import com.sportradar.liveData.demo.model.IScoreBoardRepository;
import com.sportradar.liveData.demo.model.Match;
import com.sportradar.liveData.demo.model.Team;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardInMemoryAdapter implements IScoreBoardRepository {

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
        match.setId(matches.size() + 1);
        matches.add(match);
        return match;
    }

    public boolean finishGame(int id) {
        int matchesCount = matches.size();
        matches = matches.stream().filter(match -> match.getId() != id).toList();
        return (matchesCount - 1) == matches.size();
    }

    @Override
    public Match updateScore(int matchId, int homeScore, int awayScore) {
        return matches.stream()
                .filter(match -> match.getId() == matchId)
                .map(match -> {
                    match.setHomeScore(homeScore);
                    match.setAwayScore(awayScore);
                    return match;
                })
                .findAny().orElse(null);
    }

    @Override
    public List<Match> getMatchesSortByTotalScore() {
        return matches
                .stream()
                .sorted(new TotalScoreComparator())
                .toList();
    }

    private boolean isStartGameParametersValid(Team homeTeam, Team awayTeam) {
        return (homeTeam != null && awayTeam != null);
    }
}
