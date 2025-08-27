package com.example.quizapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    LinearLayout homeResult;
    private TextView tvQuestionsAsked, tvQuestionsAnswered, tvCorrect, tvPercentage;

    Button btnShowSolution, btnRetake;

    private ArrayList<Question> questionList;
    private HashMap<Integer, Integer> userAnswers; // index → selectedOption

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvQuestionsAsked = findViewById(R.id.tvQuestionsAsked);
        tvQuestionsAnswered = findViewById(R.id.tvQuestionsAnswered);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvPercentage = findViewById(R.id.tvPercentage);
        btnRetake = findViewById(R.id.btnRetake);
        btnShowSolution = findViewById(R.id.btnShowSolution);
        homeResult = findViewById(R.id.homeResult);

        homeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });

        // 🔹 Data receive from QuizActivity
        questionList = (ArrayList<Question>) getIntent().getSerializableExtra("questionList");
        userAnswers = (HashMap<Integer, Integer>) getIntent().getSerializableExtra("userAnswers");

        if (questionList == null || userAnswers == null) {
            finish();
            return;
        }

        calculateResult();

        btnRetake.setOnClickListener(v -> {
            Intent i = new Intent(ResultActivity.this, QuizActivity.class);
            i.putExtra("language", getIntent().getStringExtra("language"));
            i.putExtra("topic", getIntent().getStringExtra("topic"));
            i.putExtra("limit", getIntent().getIntExtra("limit", 10)); // yahan limit pass karo
            startActivity(i);
            finish();
        });


        btnShowSolution.setOnClickListener(v -> {
            Intent i = new Intent(ResultActivity.this, SolutionActivity.class);
            i.putExtra("questionList", questionList);
            i.putExtra("userAnswers", userAnswers);
            startActivity(i);
            finish();
        });

        ///     status bar fit in toolbar  and status bar color related
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            int flags = decorView.getSystemUiVisibility();

            if (isDarkModeOn()) {
                // Dark Mode → white icons
                decorView.setSystemUiVisibility(flags & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // Light Mode → black icons
                decorView.setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    ///     status bar fit in toolbar  and status bar color related
    private boolean isDarkModeOn() {
        int nightModeFlags =
                getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    private void calculateResult() {
        int totalQ = questionList.size();
        int answered = userAnswers.size();
        int correct = 0;

        for (int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);
            if (userAnswers.containsKey(i)) {
                int userAns = userAnswers.get(i);
                if (userAns == q.getAnswer()) {
                    correct++;
                }
            }
        }

        double percentage = (totalQ > 0) ? (correct * 100.0 / totalQ) : 0;

        // 🔹 Set data to views
        tvQuestionsAsked.setText("Questions Asked : " + totalQ);
        tvQuestionsAnswered.setText("Questions Answered : " + answered);
        tvCorrect.setText("Correctly Answered : " + correct);
        tvPercentage.setText("Percentage Obtained : " + String.format("%.2f", percentage) + " %");
    }
}
