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
        Team homeTeam = new Team();
        Team awayTeam = new Team();
        // Act
        Match match = adapter.startGame(homeTeam, awayTeam);
        // Assert
        Assertions.assertNotNull(match);
    }

    @Test
    void shouldStartGameWithInitialScore(){
        // Arrange
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Team homeTeam = new Team();
        Team awayTeam = new Team();
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
        Team homeTeam = new Team();
        Team awayTeam = new Team();
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
        Match match = adapter.startGame(new Team(), new Team());
        int matchesCount = adapter.getMatches().size();
        // Act
        boolean success = adapter.finishGame(match.getId());
        // Arrange
        Assertions.assertTrue(success);
        Assertions.assertEquals(matchesCount - 1, adapter.getMatches().size());
    }

    @Test
    void finishGameShouldNotRemoveMatchWhenIdNotFound() {
        ScoreBoardInMemoryAdapter adapter = new ScoreBoardInMemoryAdapter();
        Match match = adapter.startGame(new Team(), new Team());
        int matchesCount = adapter.getMatches().size();
        // Act
        boolean success = adapter.finishGame(10);
        // Arrange
        Assertions.assertFalse(success);
        Assertions.assertEquals(matchesCount, adapter.getMatches().size());
    }
}
