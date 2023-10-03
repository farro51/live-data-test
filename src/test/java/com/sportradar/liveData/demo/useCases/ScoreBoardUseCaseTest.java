package com.sportradar.liveData.demo.useCases;

import com.sportradar.liveData.demo.adapters.ScoreBoardInMemoryAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardUseCaseTest {

    @Test
    void createScoreBoardTest() {
        ScoreBoardUseCase useCase = new ScoreBoardUseCase(new ScoreBoardInMemoryAdapter());
        System.out.println(useCase.createScoreBoard());
    }
}