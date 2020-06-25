package com.tangerine.customview.day_00;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tangerine.customview.R;

public class QQStepViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_q_step_view);
        QQStepView qqStepView = findViewById(R.id.qqStepView);
        qqStepView.build(4000,2000,0,4000);
    }
}
