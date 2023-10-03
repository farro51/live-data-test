package com.sportradar.liveData.demo.model;

import java.util.Calendar;
import java.util.Date;

public class Match {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private Date creationDate;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.creationDate = Calendar.getInstance().getTime();;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return homeTeam.getName() +
                " - " + awayTeam.getName() +
                ": " + homeScore +
                " - " + awayScore;
    }
}
