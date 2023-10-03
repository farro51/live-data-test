package com.sportradar.liveData.demo.helpers;

import com.sportradar.liveData.demo.model.Match;

import java.util.Comparator;

public class TotalScoreComparator implements Comparator<Match> {
    @Override
    public int compare(Match match1, Match match2) {
        int totalScoreM1 = match1.getAwayScore() + match1.getHomeScore();
        int totalScoreM2 = match2.getAwayScore() + match2.getHomeScore();
        if (totalScoreM1 > totalScoreM2 ||
                (totalScoreM1 == totalScoreM2 &&
                        match1.getCreationDate().getTime() > match2.getCreationDate().getTime())) {
            return -1;
        } else {
            return 1;
        }
    }
}
