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

public class KotlinActivity extends AppCompatActivity {

    TextView backKotlin;
    LinearLayout kotlinIntroduction, kotlinDataType, kotlinOperators, kotlinInput, kotlinStatements, kotlinLoops, kotlinFunctions,
            kotlinNull, kotlinArrays, kotlinCollections, kotlinStrings, kotlinClasses, kotlinInheritance,kotlinDataClasses, kotlinException;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kotlin);

        backKotlin  = findViewById(R.id.backKotlin );
        kotlinIntroduction = findViewById(R.id.kotlinIntroduction);
        kotlinDataType = findViewById(R.id.kotlinDataType);
        kotlinOperators = findViewById(R.id.kotlinOperators);
        kotlinInput = findViewById(R.id.kotlinInput);
        kotlinStatements = findViewById(R.id.kotlinStatements);
        kotlinLoops = findViewById(R.id.kotlinLoops);
        kotlinFunctions = findViewById(R.id.kotlinFunctions);
        kotlinNull = findViewById(R.id.kotlinNull);
        kotlinArrays = findViewById(R.id.kotlinArrays);
        kotlinCollections = findViewById(R.id.kotlinCollections);
        kotlinStrings = findViewById(R.id.kotlinStrings);
        kotlinClasses = findViewById(R.id.kotlinClasses);
        kotlinInheritance = findViewById(R.id.kotlinInheritance);
        kotlinDataClasses = findViewById(R.id.kotlinDataClasses);
        kotlinException = findViewById(R.id.kotlinException);

        backKotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        kotlinIntroduction.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinIntroduction, "Introduction to Kotlin");
        });

        kotlinDataType.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinDataType, "Variables & DataTypes");
        });

        kotlinOperators.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinOperators, "Operators");
        });

        kotlinInput.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinInput, "Input & Output");
        });

        kotlinStatements.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinStatements, "Control Statements");
        });

        kotlinLoops.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinLoops, "Loops");
        });

        kotlinFunctions.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinFunctions, "Functions");
        });

        kotlinNull.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinNull, "Null Safety");
        });

        kotlinArrays.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinArrays, "Arrays");
        });

        kotlinCollections.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinCollections, "Collections");
        });

        kotlinStrings.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinStrings, "Strings");
        });

        kotlinClasses.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinClasses, "Classes & Objects");
        });

        kotlinInheritance.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinInheritance, "Inheritance & Interfaces");
        });

        kotlinDataClasses.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinDataClasses, "Data Classes & Object");
        });

        kotlinException.setOnClickListener(v -> {
            showQuestionLimitDialog(kotlinException, "Exception Handling");
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
        int[] options = {5, 10, 25, 30};
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
                Intent intent = new Intent(KotlinActivity.this, QuizActivity.class);
                intent.putExtra("language", "Kotlin");
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