package com.example.austinchang.test.quiz;

import com.example.austinchang.test.quiz.QuizEntity;

/**
 * Created by Student on 9/7/2016.
 */
public interface QuizQuestion {
    QuizEntity[] getAnswers();
    String getText();
}
