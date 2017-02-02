package com.example.austinchang.test;

import com.example.austinchang.test.quiz.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import static android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class Quiz2 extends AppCompatActivity {
    private static final String TAG = "Trivia State";

    Quiz triviaQuiz = new TriviaQuiz();

    List<QuizQuestion> quizQuestions;
    int i = 0;
    TextView questionDisplay;
    QuizQuestion currQuestion;
    QuizEntity[] answers;
    RadioButton button1, button2;
    Button nextButton, finishButton;
    private ProgressBar progressBar;
    private int progressBarStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        Log.d(TAG, "Trivia Quiz: onCreate()");

        quizQuestions = triviaQuiz.getQuestions();
        final int numberOfQuestions = quizQuestions.size();
        final int progressBarIncrement = (100/numberOfQuestions);
        currQuestion = quizQuestions.get(i);

        questionDisplay = (TextView) findViewById(R.id.textView1);
        button1 = (RadioButton) findViewById(R.id.radio0);
        button2 = (RadioButton) findViewById(R.id.radio1);
        nextButton = (Button) findViewById(R.id.button1);
        finishButton = (Button) findViewById(R.id.button2);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        questionPage();

        OnClickListener clickNext = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton selectedAnswer = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                Log.d(TAG, "You have selected '"+selectedAnswer.getText()+"'");
                String saText = (String) selectedAnswer.getText();
                QuizEntity sa = getSelectedQuizEntity(saText);
                triviaQuiz.recordResponse(currQuestion, sa);
                if (i < numberOfQuestions) {
                    currQuestion = quizQuestions.get(i);
                    questionPage();
                    progressBarStatus += (progressBarIncrement);
                    progressBar.setProgress(progressBarStatus);
                } else {
                    String result = triviaQuiz.getResult();
                    questionDisplay.setText("You got " + result + " out of 10 questions correct! \n" +
                                            "Click Finish to go back to the main menu.");
                    group.removeAllViews();
                    nextButton.setVisibility(View.GONE);
                    finishButton.setVisibility(View.VISIBLE);
                    progressBar.setProgress(100);
                }
            }
        };
        nextButton.setOnClickListener(clickNext);

        OnClickListener backToMain = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = triviaQuiz.getResult();
                String resultToSend = "You got " + result + " out of 10 questions correct.";
                Intent sendResult = new Intent(Quiz2.this, MainActivity.class);
                sendResult.putExtra("resultString", resultToSend);
                setResult(RESULT_OK, sendResult);
                finish();
            }
        };
        finishButton.setOnClickListener(backToMain);
    }

    public void questionPage() {
        String questionText = currQuestion.getText();
        questionDisplay.setText(questionText + "\n");
        answers = currQuestion.getAnswers();
        QuizEntity ans1 = answers[0];
        String ans1Text = ans1.getText();
        QuizEntity ans2 = answers[1];
        String ans2Text = ans2.getText();
        button1.setText(ans1Text);
        button2.setText(ans2Text);
        i += 1;
    }

    public QuizEntity getSelectedQuizEntity(String selected) {
        int numberOfAnswers = answers.length;
        for (int i = 0; i < numberOfAnswers; i++) {
            if (selected.equals(answers[i].getText())){
                return answers[i];
            }
        }
        return null;
    }

}
