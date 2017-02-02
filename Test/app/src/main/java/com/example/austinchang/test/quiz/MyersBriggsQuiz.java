package com.example.austinchang.test.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyersBriggsQuiz implements Quiz {
    // Types for each question
    private static final int TYPE_EI = 0;
    private static final int TYPE_SN = 1;
    private static final int TYPE_TF = 2;
    private static final int TYPE_JP = 3;

    // Categories for each answer
    private static final int CATEGORY_A = 0;
    private static final int CATEGORY_B = 1;

    // List containing 70 Myers-Briggs questions
    private List<QuizQuestion> questionList;
    public List<QuizQuestion> getQuestions() {
        return this.questionList;
    }

    private class Question implements QuizQuestion{
        String questionText;
        Answer answers[];
        int type;

        public Question(String questionText, Answer answers[], int type) {
            this.questionText = questionText;
            this.answers = answers;
            this.type = type;
        }

        public QuizEntity[] getAnswers()
        {
            return answers;
        }

        public String getText()
        {
            return questionText;
        }

        public int getType() { return type; }
    }

    private class Answer implements QuizEntity{
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
    private int[] results;

    public MyersBriggsQuiz() {
        resetResults();

        // Init list of questions in the C constructor
        questionList = new ArrayList<QuizQuestion>(Arrays.asList(
                new Question(
                        "At a party do you:",
                        new Answer[]{
                                new Answer("Interact with many, including strangers", CATEGORY_A),
                                new Answer("Interact with a few, known to you", CATEGORY_B)
                        },
                        TYPE_EI
                ),
                new Question(
                        "Are you more:",
                        new Answer[]{
                                new Answer("Realistic than speculative", CATEGORY_A),
                                new Answer("Speculative than realistic", CATEGORY_B)
                        },
                        TYPE_SN
                ),
                new Question(
                        "Is it worse to:",
                        new Answer[]{
                                new Answer("Have your \"head in the clouds\"", CATEGORY_A),
                                new Answer("Be \"in a rut\"", CATEGORY_B)
                        },
                        TYPE_SN
                ),
                new Question(
                        "Are you more impressed by:",
                        new Answer[]{
                                new Answer("Principles", CATEGORY_A),
                                new Answer("Emotions", CATEGORY_B)
                        },
                        TYPE_TF
                ),
                new Question(
                        "Are more drawn toward the:",
                        new Answer[]{
                                new Answer("Convincing", CATEGORY_A),
                                new Answer("Touching", CATEGORY_B)
                        },
                        TYPE_TF
                )
        ));
    }

    public String getName()
    {
        return "MyersBriggsQuiz";
    }

    public String getResult()
    {
        int idxE = MyersBriggsQuiz.CATEGORY_A * 4 + MyersBriggsQuiz.TYPE_EI;
        int idxI = MyersBriggsQuiz.CATEGORY_B * 4 + MyersBriggsQuiz.TYPE_EI;
        int idxS = MyersBriggsQuiz.CATEGORY_A * 4 + MyersBriggsQuiz.TYPE_SN;
        int idxN = MyersBriggsQuiz.CATEGORY_B * 4 + MyersBriggsQuiz.TYPE_SN;
        int idxT = MyersBriggsQuiz.CATEGORY_A * 4 + MyersBriggsQuiz.TYPE_TF;
        int idxF = MyersBriggsQuiz.CATEGORY_B * 4 + MyersBriggsQuiz.TYPE_TF;
        int idxJ = MyersBriggsQuiz.CATEGORY_A * 4 + MyersBriggsQuiz.TYPE_JP;
        int idxP = MyersBriggsQuiz.CATEGORY_B * 4 + MyersBriggsQuiz.TYPE_JP;

        StringBuilder sb = new StringBuilder()
                .append(results[idxE] > results[idxI] ? "E" : "I")
                .append(results[idxS] > results[idxN] ? "S" : "N")
                .append(results[idxT] > results[idxF] ? "T" : "F")
                .append(results[idxJ] > results[idxP] ? "J" : "P");

        return sb.toString();
    }

    public void recordResponse(QuizQuestion q, QuizEntity a)
    {
        Question question = (Question)q;
        Answer answer = (Answer)a;
        results[answer.category * 4 + question.type] += 1;
    }

    public void resetResults()
    {
        // Reset the results to 0
        results = new int[2 * 4];
        for (int i = 0; i < results.length; i++) {
            results[i] = 0;
        }
    }
}

