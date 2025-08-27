package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class JavaActivity extends AppCompatActivity {

    TextView backjava;
    LinearLayout javaIntroduction, javaDataType, javaOperators, javaInput, javaStatements, javaLoops, javaArrays,
            javaStrings, javaMethods, javaOOPs, javaConstructors, javaInheritance, javaPolymorphism, javaAbstraction, javaException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_java);

        backjava = findViewById(R.id.backJava );
        javaIntroduction = findViewById(R.id.javaIntroduction);
        javaDataType = findViewById(R.id.javaDataType);
        javaOperators = findViewById(R.id.javaOperators);
        javaInput = findViewById(R.id.javaInput);
        javaStatements = findViewById(R.id.javaStatements);
        javaLoops = findViewById(R.id.javaLoops);
        javaArrays = findViewById(R.id.javaArrays);
        javaStrings = findViewById(R.id.javaStrings );
        javaMethods = findViewById(R.id.javaMethods);
        javaOOPs = findViewById(R.id.javaOOPs);
        javaConstructors = findViewById(R.id.javaConstructors);
        javaInheritance = findViewById(R.id.javaInheritance);
        javaPolymorphism= findViewById(R.id.javaPolymorphism);
        javaAbstraction = findViewById(R.id.javaAbstraction);
        javaException = findViewById(R.id.javaException);

        backjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }) ;

        javaIntroduction.setOnClickListener(v -> {
            showQuestionLimitDialog(javaIntroduction, "Introduction to Java & Setup");
        });

        javaDataType.setOnClickListener(v -> {
            showQuestionLimitDialog(javaDataType, "Data Types & Variables");
        });

        javaOperators.setOnClickListener(v -> {
            showQuestionLimitDialog(javaOperators, "Operators");
        });

        javaInput.setOnClickListener(v -> {
            showQuestionLimitDialog(javaInput, "Input & Output");
        });

        javaStatements.setOnClickListener(v -> {
            showQuestionLimitDialog(javaStatements, "Control Statements");
        });

        javaLoops.setOnClickListener(v -> {
            showQuestionLimitDialog(javaLoops, "Loops");
        });

        javaArrays.setOnClickListener(v -> {
            showQuestionLimitDialog(javaArrays, "Arrays");
        });

        javaStrings.setOnClickListener(v -> {
            showQuestionLimitDialog(javaStrings, "Strings in Java");
        });

        javaMethods.setOnClickListener(v -> {
            showQuestionLimitDialog(javaMethods, "Methods in Java");
        });

        javaOOPs.setOnClickListener(v -> {
            showQuestionLimitDialog(javaOOPs, "Object-Oriented Programming");
        });

        javaConstructors.setOnClickListener(v -> {
            showQuestionLimitDialog(javaConstructors, "Constructors & Destructors");
        });

        javaInheritance.setOnClickListener(v -> {
            showQuestionLimitDialog(javaInheritance, "Inheritance");
        });

        javaPolymorphism.setOnClickListener(v -> {
            showQuestionLimitDialog(javaPolymorphism, "Polymorphism");
        });

        javaAbstraction.setOnClickListener(v -> {
            showQuestionLimitDialog(javaAbstraction, "Abstraction & Encapsulation");
        });

        javaException.setOnClickListener(v -> {
            showQuestionLimitDialog(javaException, "Exception Handling");
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
                Intent intent = new Intent(JavaActivity.this, QuizActivity.class);
                intent.putExtra("language", "Java");
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
