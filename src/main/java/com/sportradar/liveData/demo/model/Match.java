package com.sportradar.liveData.demo.model;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
}
