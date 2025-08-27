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

public class CActivity extends AppCompatActivity {

    TextView backC;
    LinearLayout cBasic, cdataType, cKeywords, cOperators, cStatements, cLoops, cFunction,
            cRecursion, cArrays, cStrings, cPointers, cStructures, cDMA, cFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cactivity);

        backC = findViewById(R.id.backC);
        cBasic = findViewById(R.id.cBasic);
        cdataType = findViewById(R.id.cdataType);
        cKeywords = findViewById(R.id.cKeyword);
        cOperators = findViewById(R.id.cOperators);
        cStatements = findViewById(R.id.cStatements);
        cLoops = findViewById(R.id.cLoops);
        cFunction = findViewById(R.id.cFunctions);
        cRecursion = findViewById(R.id.cRecursion);
        cArrays = findViewById(R.id.cArrays);
        cStrings = findViewById(R.id.cStrings);
        cPointers = findViewById(R.id.cPointers);
        cStructures = findViewById(R.id.cStructures);
        cDMA = findViewById(R.id.cDMA);
        cFile = findViewById(R.id.cFile);

        backC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }) ;

        cBasic.setOnClickListener(v -> {
            showQuestionLimitDialog(cBasic, "Basics of C Programming");
        });

        cdataType.setOnClickListener(v -> {
            showQuestionLimitDialog(cdataType, "DataTypes_Variables");
        });

        cKeywords.setOnClickListener(v -> {
            showQuestionLimitDialog(cKeywords, "Constants_Keyword");
        });

        cOperators.setOnClickListener(v -> {
            showQuestionLimitDialog(cOperators, "Operators");
        });

        cStatements.setOnClickListener(v -> {
            showQuestionLimitDialog(cStatements, "Control_Statement");
        });

        cLoops.setOnClickListener(v -> {
            showQuestionLimitDialog(cLoops, "Loops");
        });

        cFunction.setOnClickListener(v -> {
            showQuestionLimitDialog(cFunction, "Functions");
        });

        cRecursion.setOnClickListener(v -> {
            showQuestionLimitDialog(cRecursion, "Recursion");
        });

        cArrays.setOnClickListener(v -> {
            showQuestionLimitDialog(cArrays, "Arrays");
        });

        cStrings.setOnClickListener(v -> {
            showQuestionLimitDialog(cStrings, "Strings");
        });

        cPointers.setOnClickListener(v -> {
            showQuestionLimitDialog(cPointers, "Pointers");
        });

        cStructures.setOnClickListener(v -> {
            showQuestionLimitDialog(cStructures, "Structures");
        });

        cDMA.setOnClickListener(v -> {
            showQuestionLimitDialog(cDMA, "DMA");
        });

        cFile.setOnClickListener(v -> {
            showQuestionLimitDialog(cFile, "File");
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
                Intent intent = new Intent(CActivity.this, QuizActivity.class);
                intent.putExtra("language", "C");
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