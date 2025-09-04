package com.example.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvCounter, tvCTitle;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3, option4;
    private Button btnBack, btnSubmit, btnFinalSubmit;
    private LinearLayout loadingLayout;
    private ProgressBar progressBar;

    private List<Question> questionList = new ArrayList<>();
    private int currentIndex = 0;

    private HashMap<Integer, Integer> userAnswers = new HashMap<>();

    private DatabaseReference ref;
    private String language, topic;
    private int numQuestions = 0;  //  user selected limit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        tvQuestion = findViewById(R.id.tvQuestion);
        tvCounter = findViewById(R.id.tvCounter);
        tvCTitle = findViewById(R.id.CTitle);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);
        loadingLayout = findViewById(R.id.loadingLayout);
        btnFinalSubmit = findViewById(R.id.btnFinalSubmit);
        progressBar = findViewById(R.id.progressbar);

        language = getIntent().getStringExtra("language");
        topic = getIntent().getStringExtra("topic");
        numQuestions = getIntent().getIntExtra("numQuestions", 0); // read limit

        if (language == null || topic == null) {
            Toast.makeText(this, "Invalid quiz data!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Hide quiz until questions load
        loadingLayout.setVisibility(View.VISIBLE);
        tvQuestion.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        tvCounter.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);
        btnBack.setVisibility(View.GONE);
        tvCTitle.setVisibility(View.GONE);
        btnFinalSubmit.setVisibility(View.GONE);

        // status bar styling
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
                decorView.setSystemUiVisibility(flags & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decorView.setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        ref = FirebaseDatabase.getInstance().getReference("questions")
                .child(language)
                .child(topic);

        loadTopicTitle();
        loadQuestions();

        btnSubmit.setOnClickListener(v -> submitAndNext());
        btnBack.setOnClickListener(v -> prevQuestion());

        btnFinalSubmit.setOnClickListener(v -> {
            saveAnswer();
            openResultActivity();
        });
    }

    private void loadTopicTitle() {
        ref.child("title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    tvCTitle.setText(snapshot.getValue(String.class));
                } else {
                    tvCTitle.setText(topic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                tvCTitle.setText(topic);
            }
        });
    }

    private void loadQuestions() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questionList.clear();
                for (DataSnapshot qSnap : snapshot.getChildren()) {
                    if (!qSnap.getKey().equals("title")) {
                        Question q = qSnap.getValue(Question.class);
                        if (q != null) questionList.add(q);
                    }
                }

                if (!questionList.isEmpty()) {
                    // ✅ Randomize and limit questions
                    Collections.shuffle(questionList);
                    if (numQuestions > 0 && numQuestions < questionList.size()) {
                        questionList = new ArrayList<>(questionList.subList(0, numQuestions));
                    }

                    currentIndex = 0;
                    showQuestion(currentIndex);

                    loadingLayout.setVisibility(View.GONE);
                    tvQuestion.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    tvCounter.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.VISIBLE);
                    btnBack.setVisibility(View.VISIBLE);
                    tvCTitle.setVisibility(View.VISIBLE);

                } else {
                    loadingLayout.setVisibility(View.GONE);
                    Toast.makeText(QuizActivity.this, "No questions found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingLayout.setVisibility(View.GONE);
                Toast.makeText(QuizActivity.this, "Failed to load questions!", Toast.LENGTH_SHORT).show();
                Log.e("QuizActivity", "Firebase error: " + error.getMessage());
            }
        });
    }

    private void showQuestion(int index) {
        if (index < 0 || index >= questionList.size()) return;

        Question q = questionList.get(index);
        tvQuestion.setText(q.getQuestion());
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());

        tvCounter.setText((index + 1) + " / " + questionList.size());
        radioGroup.clearCheck();

        if (userAnswers.containsKey(index)) {
            int savedAnswer = userAnswers.get(index);
            if (savedAnswer == 1) option1.setChecked(true);
            else if (savedAnswer == 2) option2.setChecked(true);
            else if (savedAnswer == 3) option3.setChecked(true);
            else if (savedAnswer == 4) option4.setChecked(true);
        }

        if (index == questionList.size() - 1) {
            btnSubmit.setEnabled(false);
            btnSubmit.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY)); // 🔹 Disabled pe grey
            btnFinalSubmit.setVisibility(View.VISIBLE);
        } else {
            btnSubmit.setEnabled(true);
            btnSubmit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7CFC00"))); // 🔹 Enabled pe green
            btnFinalSubmit.setVisibility(View.GONE);
        }
    }


    private void submitAndNext() {
        saveAnswer();
        if (currentIndex < questionList.size() - 1) {
            currentIndex++;
            showQuestion(currentIndex);
        } else {
            openResultActivity();
        }
    }

    private void prevQuestion() {
        if (currentIndex > 0) {
            currentIndex--;
            showQuestion(currentIndex);
        }
    }

    private void saveAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        int answerIndex = -1;

        if (selectedId == R.id.option1) answerIndex = 1;
        else if (selectedId == R.id.option2) answerIndex = 2;
        else if (selectedId == R.id.option3) answerIndex = 3;
        else if (selectedId == R.id.option4) answerIndex = 4;

        if (answerIndex != -1) {
            userAnswers.put(currentIndex, answerIndex);
        }
    }

    private void openResultActivity() {
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("questionList", new ArrayList<>(questionList));
        i.putExtra("userAnswers", userAnswers);
        i.putExtra("language", language);
        i.putExtra("topic", topic);
        i.putExtra("numQuestions", numQuestions); //  Pass limit forward
        startActivity(i);
        finish();
    }


    private boolean isDarkModeOn() {
        int nightModeFlags =
                getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    @Override
    public void onBackPressed() {
        if (!questionList.isEmpty()) {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Exit Quiz?")
                    .setMessage("Are you sure you want to exit? Your progress will be lost.")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> super.onBackPressed())
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        } else {
            super.onBackPressed();
        }
    }
}
