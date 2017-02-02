package com.example.austinchang.test.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriviaQuiz implements Quiz {
    // Categories for each answer
    private static final int CORRECT = 0;
    private static final int INCORRECT = 1;

    // List containing questions
    private List<QuizQuestion> questionList;
    public List<QuizQuestion> getQuestions() {
        return this.questionList;
    }

    private class Question implements QuizQuestion{
        String questionText;
        Answer answers[];

        public Question(String questionText, Answer answers[]) {
            this.questionText = questionText;
            this.answers = answers;
        }

        public QuizEntity[] getAnswers()
        {
            return answers;
        }

        public String getText()
        {
            return questionText;
        }
    }

    private class Answer implements QuizEntity {
        String answerText;
        int category;

        public Answer(String answerText, int category) {
            this.answerText = answerText;
            this.category = category;
        }

        public String getText()
        {
            return answerText;
        }
    }

    // Array for tracking quiz results
    private int results = 0;

    public TriviaQuiz() {
        resetResults();

        // Init list of questions in the C constructor
        questionList = new ArrayList<QuizQuestion>(Arrays.asList(
                new Question(
                        "Samite is a type of:",
                        new Answer[]{
                                new Answer("Fabric", CORRECT),
                                new Answer("Stone", INCORRECT)
                        }
                ),
                new Question(
                        "Something annular is in the shape of a what?",
                        new Answer[]{
                                new Answer("Leaf", INCORRECT),
                                new Answer("Ring", CORRECT)
                        }
                ),
                new Question(
                        "Sfumato is a technique in what?",
                        new Answer[]{
                                new Answer("Cooking", INCORRECT ),
                                new Answer("Painting", CORRECT),
                        }
                ),
                new Question(
                        "If something coruscates, what does it do?",
                        new Answer[]{
                                new Answer("Sparkles", CORRECT),
                                new Answer("Expands", INCORRECT)
                        }
                ),
                new Question(
                        "A nide is a nest of which type of bird?",
                        new Answer[]{
                                new Answer("Emus", INCORRECT),
                                new Answer("Pheasant", CORRECT)
                        }
                ),
                new Question(
                        "Evo Morales became president of which country in 2006?",
                        new Answer[]{
                                new Answer("Peru", INCORRECT),
                                new Answer("Bolivia", CORRECT )
                        }
                ),
                new Question(
                        "An anemometer is a gauge used for recording what?",
                        new Answer[]{
                                new Answer("Tire Pressure", INCORRECT),
                                new Answer("Wind", CORRECT)
                        }
                ),
                new Question(
                        "'Plantar' relates to which part of the human body?",
                        new Answer[]{
                                new Answer("Head", INCORRECT),
                                new Answer("Foot", CORRECT)

                        }
                ),
                new Question(
                        "A caparison is an ornamental cloth used to cover what?",
                        new Answer[]{
                                new Answer("Horse", CORRECT),
                                new Answer("Car", INCORRECT)

                        }
                ),
                new Question(
                        "Vermillion is a shade of which color?",
                        new Answer[]{
                                new Answer("Green", INCORRECT),
                                new Answer("Red", CORRECT)
                        }
                )
        ));
    }

    public String getName() {
        return "TriviaQuiz";
    }

    public String getResult() {
        return Integer.toString(results);
    }


    public void recordResponse(QuizQuestion q, QuizEntity a) {
        Question question = (Question) q;
        Answer answer = (Answer) a;
        if (answer.category == CORRECT) {
            results += 1;
        }
    }

    public void resetResults() {
        // Reset the results to 0
        results = 0;
    }
}

