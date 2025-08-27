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


public class JavaScriptActivity extends AppCompatActivity {
    TextView backJavaScript;
    LinearLayout jsIntroduction, jsDataType, jsOperators, jsInput, jsStatements, jsLoops, jsFunctions,
            jsArrays, jsStrings, jsObjects, jsDOM, jsEvents, jsClasses, jsErrorHandling, jsAsync, jsPromises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_java_script);

        backJavaScript = findViewById(R.id.backJavaScript);
        jsIntroduction= findViewById(R.id.jsIntroduction);
        jsDataType = findViewById(R.id.jsDataType);
        jsOperators = findViewById(R.id.jsOperators);
        jsInput = findViewById(R.id.jsInput);
        jsStatements= findViewById(R.id.jsStatements);
        jsLoops = findViewById(R.id.jsLoops);
        jsFunctions = findViewById(R.id.jsFunctions);
        jsArrays = findViewById(R.id.jsArrays);
        jsStrings = findViewById(R.id.jsStrings);
        jsObjects = findViewById(R.id.jsObjects);
        jsDOM = findViewById(R.id.jsDOM);
        jsEvents = findViewById(R.id.jsEvents);
        jsClasses = findViewById(R.id.jsClasses);
        jsErrorHandling = findViewById(R.id.jsErrorHandling);
        jsAsync = findViewById(R.id.jsAsync);
        jsPromises = findViewById(R.id.jsPromises);

        backJavaScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        jsIntroduction.setOnClickListener(v -> {
            showQuestionLimitDialog(jsIntroduction, "Introduction to JavaScript");
        });

        jsDataType.setOnClickListener(v -> {
            showQuestionLimitDialog(jsDataType, "Variables & Data Types");
        });

        jsOperators.setOnClickListener(v -> {
            showQuestionLimitDialog(jsOperators, "Operators");
        });

        jsInput.setOnClickListener(v -> {
            showQuestionLimitDialog(jsInput, "Input & Output");
        });

        jsStatements.setOnClickListener(v -> {
            showQuestionLimitDialog(jsStatements, "Control Statements");
        });

        jsLoops.setOnClickListener(v -> {
            showQuestionLimitDialog(jsLoops, "Loops");
        });

        jsFunctions.setOnClickListener(v -> {
            showQuestionLimitDialog(jsFunctions, "Functions");
        });

        jsArrays.setOnClickListener(v -> {
            showQuestionLimitDialog(jsArrays, "Arrays");
        });

        jsStrings.setOnClickListener(v -> {
            showQuestionLimitDialog(jsStrings, "Strings");
        });

        jsObjects.setOnClickListener(v -> {
            showQuestionLimitDialog(jsObjects, "Objects");
        });

        jsDOM.setOnClickListener(v -> {
            showQuestionLimitDialog(jsDOM, "DOM Manipulation");
        });

        jsEvents.setOnClickListener(v -> {
            showQuestionLimitDialog(jsEvents, "Events");
        });

        jsClasses.setOnClickListener(v -> {
            showQuestionLimitDialog(jsClasses, "Classes & OOPs");
        });

        jsErrorHandling.setOnClickListener(v -> {
            showQuestionLimitDialog(jsErrorHandling, "Error & Exception Handling");
        });

        jsAsync.setOnClickListener(v -> {
            showQuestionLimitDialog(jsAsync, "Async or Await");
        });

        jsPromises.setOnClickListener(v -> {
            showQuestionLimitDialog(jsPromises, "Promises");
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
                Intent intent = new Intent(JavaScriptActivity.this, QuizActivity.class);
                intent.putExtra("language", "JavaScript");
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