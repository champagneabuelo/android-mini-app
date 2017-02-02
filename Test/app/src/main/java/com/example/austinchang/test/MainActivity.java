package com.example.austinchang.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import static android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TabHost host;
    TabHost.TabSpec spec;
    Button myers_briggs_button, myers_briggs_button_result,
            trivia_button, trivia_button_result;

    private static final String TAG = "Main Activity State";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Main Activity: onCreate()");

        // Create a Tab Host to store the tabs for unfinished and past quizzes
        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //New Quiz Tabs
        spec = host.newTabSpec("New Quizzes");
        spec.setContent(R.id.New);
        spec.setIndicator("New Quizzes");
        host.addTab(spec);

        //Past Quiz Tab
        spec = host.newTabSpec("Quiz Results");
        spec.setContent(R.id.Past);
        spec.setIndicator("Quiz Results");
        host.addTab(spec);

        //Buttons for quizzes, corresponding results
        myers_briggs_button = (Button) findViewById(R.id.myersbriggsquiz);
        trivia_button = (Button) findViewById(R.id.anotherquiz);
        myers_briggs_button_result = (Button) findViewById(R.id.myersbriggsquiz_results);
        trivia_button_result = (Button) findViewById(R.id.anotherquiz_results);

        //Event listeners for unfinished quiz buttons
        OnClickListener clickMB = new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuiz(Quiz1.class, 1);
            }
        };
        myers_briggs_button.setOnClickListener(clickMB);

        OnClickListener clickAQ = new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuiz(Quiz2.class, 2);
            }
        };
        trivia_button.setOnClickListener(clickAQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // remove button from unfinished quiz view, make result button visible
                myers_briggs_button.setVisibility(View.GONE);
                myers_briggs_button_result.setVisibility(View.VISIBLE);

                // set onclicklistener that displays the result from MB quiz.
                OnClickListener clickMBResults = new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = getApplicationContext();
                        Bundle bundle = data.getExtras();
                        int duration = Toast.LENGTH_SHORT;
                        String resultString = bundle.getString("resultString");
                        Toast resultPopUp = Toast.makeText(context, resultString, duration);
                        resultPopUp.show();
                    }
                };
                myers_briggs_button_result.setOnClickListener(clickMBResults);
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                // remove button from unfinished quiz view, make result button visible
                trivia_button.setVisibility(View.GONE);
                trivia_button_result.setVisibility(View.VISIBLE);

                // set onclicklistener that displays the result from trivia quiz.
                OnClickListener clickTQResults = new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = getApplicationContext();
                        Bundle bundle = data.getExtras();
                        int duration = Toast.LENGTH_SHORT;
                        String resultString = bundle.getString("resultString");
                        Toast resultPopUp = Toast.makeText(context, resultString, duration);
                        resultPopUp.show();
                    }
                };
                trivia_button_result.setOnClickListener(clickTQResults);
            }
        }
    }

    public void goToQuiz(Class cls, int requestCode) {
        Intent quiz_intent = new Intent(MainActivity.this, cls);
        startActivityForResult(quiz_intent, requestCode);
    }
}
