package com.sportradar.liveData.demo.model;

import java.util.List;

public interface IScoreBoardRepository {
    List<Match> getMatches();
    Match startGame(Team homeTeam, Team awayTeam);
}
