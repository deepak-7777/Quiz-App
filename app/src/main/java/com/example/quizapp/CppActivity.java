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

public class CppActivity extends AppCompatActivity {

    TextView backCpp;
    LinearLayout cppBasic, cppDataType, cppOperators, cppStatements, cppLoops, cppFunction, cppArrays,
            cppStrings, cppPointers, cppOPPs, cppConstructors, cppInheritance, cppPolymorphism, cppException, cppFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cpp);

        backCpp = findViewById(R.id.backCpp );
        cppBasic = findViewById(R.id.cppBasic );
        cppDataType= findViewById(R.id.cppDataType );
        cppOperators = findViewById(R.id.cppOperators );
        cppStatements = findViewById(R.id.cppStatement );
        cppLoops = findViewById(R.id.cppLoops );
        cppFunction = findViewById(R.id.cppFunctions );
        cppArrays = findViewById(R.id.cppArrays );
        cppStrings = findViewById(R.id.cppStrings );
        cppPointers = findViewById(R.id.cppPointers );
        cppOPPs = findViewById(R.id.cppOpp );
        cppConstructors = findViewById(R.id.cppConstructors );
        cppInheritance = findViewById(R.id.cppInheritance );
        cppPolymorphism = findViewById(R.id.cppPolymorphism );
        cppException = findViewById(R.id.cppException );
        cppFile = findViewById(R.id.cppFile );

        backCpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }) ;

        cppBasic.setOnClickListener(v -> {
            showQuestionLimitDialog(cppBasic, "Basics of C++");
        });

        cppDataType.setOnClickListener(v -> {
            showQuestionLimitDialog(cppDataType, "DataTypes & Variables");
        });

        cppOperators.setOnClickListener(v -> {
            showQuestionLimitDialog(cppOperators, "Operators");
        });

        cppStatements.setOnClickListener(v -> {
            showQuestionLimitDialog(cppStatements, "Control & Statements");
        });

        cppLoops.setOnClickListener(v -> {
            showQuestionLimitDialog(cppLoops, "Loops");
        });

        cppFunction.setOnClickListener(v -> {
            showQuestionLimitDialog(cppFunction, "Functions");
        });

        cppArrays.setOnClickListener(v -> {
            showQuestionLimitDialog(cppArrays, "Arrays");
        });

        cppStrings.setOnClickListener(v -> {
            showQuestionLimitDialog(cppStrings, "Strings");
        });

        cppPointers.setOnClickListener(v -> {
            showQuestionLimitDialog(cppPointers, "Pointers");
        });

        cppOPPs.setOnClickListener(v -> {
            showQuestionLimitDialog(cppOPPs, "Object-Oriented Programming");
        });

        cppConstructors.setOnClickListener(v -> {
            showQuestionLimitDialog(cppConstructors, "Constructors & Destructors");
        });

        cppInheritance.setOnClickListener(v -> {
            showQuestionLimitDialog(cppInheritance, "Inheritance");
        });

        cppPolymorphism.setOnClickListener(v -> {
            showQuestionLimitDialog(cppPolymorphism, "Polymorphism");
        });

        cppException.setOnClickListener(v -> {
            showQuestionLimitDialog(cppException, "Exception & Handling");
        });

        cppFile.setOnClickListener(v -> {
            showQuestionLimitDialog(cppFile, "File & Handling");
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
                Intent intent = new Intent(CppActivity.this, QuizActivity.class);
                intent.putExtra("language", "C++");
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
