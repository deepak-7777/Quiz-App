package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    TextView backSetting;
    LinearLayout aboutQuiz, privacySetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);

        backSetting= findViewById(R.id.backSetting);
        aboutQuiz = findViewById(R.id.aboutQuiz);
        privacySetting = findViewById(R.id.privacySettings);

        backSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aboutQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        privacySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}