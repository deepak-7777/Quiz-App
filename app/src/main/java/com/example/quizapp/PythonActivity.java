package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PythonActivity extends AppCompatActivity {

    TextView backPython;
    LinearLayout pythonBasic, pythonDataType, pythonOperators, pythonInput, pythonStatements, pythonLoops, pythonFunction, pythonDataStructures,
            pythonStringHandling, pythonModules, pythonFileHandling, pythonExceptionHandling, pythonOOPs, pythonIterators, pythonDecorators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_python );

        backPython  = findViewById(R.id.backPython);
        pythonBasic= findViewById(R.id.pythonBasic);
        pythonDataType = findViewById(R.id.pythonDataType);
        pythonOperators = findViewById(R.id.pythonOperators);
        pythonInput = findViewById(R.id.pythonInput);
        pythonStatements = findViewById(R.id.pythonStatements);
        pythonLoops = findViewById(R.id.pythonLoops);
        pythonFunction = findViewById(R.id.pythonFunctions);
        pythonDataStructures = findViewById(R.id.pythonDataStructures);
        pythonStringHandling = findViewById(R.id.pythonStringHandling);
        pythonModules = findViewById(R.id.pythonModules);
        pythonFileHandling = findViewById(R.id.pythonFileHandling);
        pythonExceptionHandling = findViewById(R.id.pythonExceptionHandling);
        pythonOOPs = findViewById(R.id.pythonOOPs);
        pythonIterators = findViewById(R.id.pythonIterators);
        pythonDecorators = findViewById(R.id.pythonDecorators);

        backPython .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }) ;

        pythonBasic.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonBasic, "Basics of Python");
        });

        pythonDataType.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonDataType, "Variables & Data Types");
        });

        pythonOperators.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonOperators, "Operators");
        });

        pythonInput.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonInput, "Input & Output");
        });

        pythonStatements.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonStatements, "Control Statements");
        });

        pythonLoops.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonLoops, "Loops");
        });

        pythonFunction.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonFunction, "Functions");
        });

        pythonDataStructures.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonDataStructures, "Data Structures");
        });

        pythonStringHandling.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonStringHandling, "String Handling");
        });

        pythonModules.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonModules, "Modules & Packages");
        });

        pythonFileHandling.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonFileHandling, "File Handling");
        });

        pythonExceptionHandling.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonExceptionHandling, "Exception Handling");
        });

        pythonOOPs.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonOOPs, "Object-Oriented Programming");
        });

        pythonIterators.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonIterators, "Iterators & Generators");
        });

        pythonDecorators.setOnClickListener(v -> {
            showQuestionLimitDialog(pythonDecorators, "Decorators");
        });

    }

    private void showQuestionLimitDialog(LinearLayout topicLayout, String topicName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Number of Questions");

        // Custom layout for dialog
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(50, 30, 50, 30);
        layout.setGravity(Gravity.CENTER);

        // Circle buttons
        int[] options = {5, 10, 15};
        for (int opt : options) {
            TextView tv = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, 120);
            params.setMargins(20, 0, 20, 0);
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.CENTER);
            tv.setText(String.valueOf(opt));
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(18f);
            tv.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_option_bg));
            tv.setClickable(true);
            tv.setFocusable(true);

            int finalOpt = opt;
            tv.setOnClickListener(v -> {
                // Launch QuizActivity with selected limit
                Intent intent = new Intent(PythonActivity.this, QuizActivity.class);
                intent.putExtra("language", "Python");
                intent.putExtra("topic", topicName);
                intent.putExtra("limit", finalOpt);  // user selected limit
                startActivity(intent);
                finish();
            });
            layout.addView(tv);
        }
        builder.setView(layout);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}