package com.sportradar.liveData.demo.useCases;

import com.sportradar.liveData.demo.model.IScoreBoardRepository;
import com.sportradar.liveData.demo.model.Match;
import com.sportradar.liveData.demo.model.Team;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardUseCase {
    private IScoreBoardRepository scoreBoardRepo;

    public ScoreBoardUseCase(IScoreBoardRepository scoreBoardRepo) {
        this.scoreBoardRepo = scoreBoardRepo;
    }

    public String createScoreBoard() {
        scoreBoardRepo.startGame(new Team("Mexico"), new Team("Canada"));
        scoreBoardRepo.startGame(new Team("Spain"), new Team("Brazil"));
        scoreBoardRepo.startGame(new Team("Germany"), new Team("France"));
        scoreBoardRepo.startGame(new Team("Uruguay"), new Team("Italy"));
        scoreBoardRepo.startGame(new Team("Argentina"), new Team("Australia"));
        Match finishGame = scoreBoardRepo.startGame(new Team("Colombia"), new Team("Norway"));

        scoreBoardRepo.finishGame(finishGame.getId());
        scoreBoardRepo.updateScore(10, 0, 5);

        List<Match> matches = scoreBoardRepo.getMatches();
        scoreBoardRepo.updateScore(matches.get(0).getId(), 0, 5);
        scoreBoardRepo.updateScore(matches.get(1).getId(), 10, 2);
        scoreBoardRepo.updateScore(matches.get(2).getId(), 2, 2);
        scoreBoardRepo.updateScore(matches.get(3).getId(), 6, 6);
        scoreBoardRepo.updateScore(matches.get(4).getId(), 3, 1);

        StringBuilder stringBuilder = new StringBuilder();
        scoreBoardRepo.getMatchesSortByTotalScore()
                .stream()
                .forEach(match -> stringBuilder.append(match.toString() + System.lineSeparator()));
        return stringBuilder.toString();
    }
}
