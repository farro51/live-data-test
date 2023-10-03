package com.sportradar.liveData.demo.adapters;

import com.sportradar.liveData.demo.model.Match;
import com.sportradar.liveData.demo.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScoreBoardInMemoryAdapterTest {
    @Test
    void shouldGetListOfMatchesNotNull(){
        // ARRANGE
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        // ACT
        List<Match> matches = adapter.getMatches();
        // ASSERT
        Assertions.assertNotNull(matches);
    }

    @Test
    void shouldStartGameAndReturnMatch() {
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Team homeTeam = new Team("homeTeam");
        Team awayTeam = new Team("awayTeam");
        // Act
        Match match = adapter.startGame(homeTeam, awayTeam);
        // Assert
        Assertions.assertNotNull(match);
    }

    @Test
    void shouldStartGameWithInitialScore(){
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Team homeTeam = new Team("homeTeam");
        Team awayTeam = new Team("awayTeam");
        // Act
        Match match = adapter.startGame(homeTeam, awayTeam);
        // Assert
        Assertions.assertNotNull(match);
        Assertions.assertEquals(0, match.getHomeScore());
        Assertions.assertEquals(0, match.getAwayScore());
    }

    @Test
    void startGameShouldIncreaseScoreBoardMatches() {
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Team homeTeam = new Team("homeTeam");
        Team awayTeam = new Team("awayTeam");
        int beforeMatchesCount = adapter.getMatches().size();
        // Act
        Match match = adapter.startGame(homeTeam, awayTeam);
        // Assert
        Assertions.assertTrue(beforeMatchesCount < adapter.getMatches().size());
    }

    @Test
    void startGameShouldThrowErrorWhenTeamNull() {
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        // Act
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> adapter.startGame(null, null));
        // Assert
        Assertions.assertEquals("Provide a valid home and away team", exception.getMessage());
    }

    @Test
    void finishGameShouldRemoveMatch() {
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Match match = adapter.startGame(new Team("homeTeam"), new Team("awayTeam"));
        int matchesCount = adapter.getMatches().size();
        // Act
        boolean success = adapter.finishGame(match.getId());
        // Assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(matchesCount - 1, adapter.getMatches().size());
    }

    @Test
    void finishGameShouldNotRemoveMatchWhenIdNotFound() {
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Match match = adapter.startGame(new Team("homeTeam"), new Team("awayTeam"));
        int matchesCount = adapter.getMatches().size();
        // Act
        boolean success = adapter.finishGame(10);
        // Assert
        Assertions.assertFalse(success);
        Assertions.assertEquals(matchesCount, adapter.getMatches().size());
    }

    @Test
    void shouldUpdateScoreByMatchId() {
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Match match = adapter.startGame(new Team("homeTeam"), new Team("awayTeam"));
        int homeScore = 3, awayScore = 2;
        // Act
        match = adapter.updateScore(match.getId(), homeScore, awayScore);
        // Assert
        Assertions.assertEquals(awayScore, match.getAwayScore());
        Assertions.assertEquals(homeScore, match.getHomeScore());
    }

    @Test
    void shouldGetMatchesOrderedByTotalScore() {
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Match match1 = adapter.startGame(new Team("homeTeam1"), new Team("awayTeam1"));
        Match match2 = adapter.startGame(new Team("homeTeam2"), new Team("awayTeam2"));
        Match match3 = adapter.startGame(new Team("homeTeam3"), new Team("awayTeam3"));
        adapter.updateScore(match1.getId(), 1, 3);
        adapter.updateScore(match2.getId(), 4, 3);
        adapter.updateScore(match3.getId(), 3, 2);
        // Act
        List<Match> matches = adapter.getMatchesSortByTotalScore();
        // Assert
        Assertions.assertEquals(match2.getId(), matches.get(0).getId());
        Assertions.assertEquals(match3.getId(), matches.get(1).getId());
        Assertions.assertEquals(match1.getId(), matches.get(2).getId());

    }

    @Test
    void shouldSortMatchesByTotalScoreAndCreationDate() {
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Match match1 = adapter.startGame(new Team("homeTeam1"), new Team("awayTeam1"));
        Match match3 = adapter.startGame(new Team("homeTeam2"), new Team("awayTeam2"));
        Match match5 = adapter.startGame(new Team("homeTeam3"), new Team("awayTeam3"));
        Match match2 = adapter.startGame(new Team("homeTeam4"), new Team("awayTeam4"));
        Match match4 = adapter.startGame(new Team("homeTeam5"), new Team("awayTeam5"));
        adapter.updateScore(match1.getId(), 1, 3);
        adapter.updateScore(match2.getId(), 3, 2);
        adapter.updateScore(match3.getId(), 3, 1);
        adapter.updateScore(match4.getId(), 2, 0);
        adapter.updateScore(match5.getId(), 2, 2);
        // Act
        List<Match> matches = adapter.getMatchesSortByTotalScore();
        // Assert
        Assertions.assertEquals(match2.getId(), matches.get(0).getId());
        Assertions.assertEquals(match1.getId(), matches.get(1).getId());
        Assertions.assertEquals(match3.getId(), matches.get(2).getId());
        Assertions.assertEquals(match5.getId(), matches.get(3).getId());
        Assertions.assertEquals(match4.getId(), matches.get(4).getId());
    }
}
