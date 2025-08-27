package com.example.quizapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class SolutionActivity extends AppCompatActivity {

    private RecyclerView rvSolution;
    private ArrayList<Question> questionList;
    private HashMap<Integer, Integer> userAnswers;
    LinearLayout homeContainer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        rvSolution = findViewById(R.id.rvSolution);
        homeContainer = findViewById(R.id.homeContainer);
        rvSolution.setLayoutManager(new LinearLayoutManager(this));

        homeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SolutionActivity.this , MainActivity.class));
                finish();
            }
        });

        // Receive data from ResultActivity
        questionList = (ArrayList<Question>) getIntent().getSerializableExtra("questionList");
        userAnswers = (HashMap<Integer, Integer>) getIntent().getSerializableExtra("userAnswers");

        if (questionList == null || userAnswers == null) {
            Toast.makeText(this, "No data to show!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        SolutionAdapter adapter = new SolutionAdapter(this, questionList, userAnswers);
        rvSolution.setAdapter(adapter);

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
}
