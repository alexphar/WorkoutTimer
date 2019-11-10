package com.example.lab4;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class run_program_timer extends AppCompatActivity {

    int i = 0;

    ArrayList<WorkOutPartBase> parts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_program_timer);

        parts = (ArrayList<WorkOutPartBase>) getIntent().getSerializableExtra("PART");

        startTimer();
    }

    void startTimer() {
        final TextView text = findViewById(R.id.text_for_part);
        final TextView textSeconds = findViewById(R.id.text_for_seconds);

        new CountDownTimer(parts.get(i).getWorkOutTime()*1000 + 1000, 1000)
        {
            public void onTick(long millisUntilFinished) {
                text.setText(parts.get(i).getWorkOutName()); 
                textSeconds.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                text.setText("Done");
                i++;

                if (i < parts.size()) {
                    startTimer();
                }
                else {
                    finish();
                }
                    }
        }.start();
    }

}
