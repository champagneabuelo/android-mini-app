package com.example.austinchang.test.quiz;

import java.util.List;

/**
 * Created by Jason on 9/7/2016.
 *
 */
public interface Quiz {
    String getName();
    String getResult();
    void recordResponse(QuizQuestion q, QuizEntity a);
    void resetResults();
    List<QuizQuestion> getQuestions();
}
