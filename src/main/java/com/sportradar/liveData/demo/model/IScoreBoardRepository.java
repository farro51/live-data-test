package com.sportradar.liveData.demo.model;

import java.util.List;

public interface IScoreBoardRepository {
    List<Match> getMatches();
    Match startGame(Team homeTeam, Team awayTeam);
    boolean finishGame(int matchId);
    Match updateScore(int matchId, int homeScore, int awayScore);
    List<Match> getMatchesSortByTotalScore();
}
