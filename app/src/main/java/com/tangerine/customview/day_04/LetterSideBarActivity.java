package com.tangerine.customview.day_04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

import com.tangerine.customview.R;

public class LetterSideBarActivity extends AppCompatActivity implements LetterSideBar.LetterTouchListener {
    private AppCompatTextView mTvLetter;
    private LetterSideBar mLetterSideBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_side_bar);
        mTvLetter = findViewById(R.id.tv_letter);
        mLetterSideBar = findViewById(R.id.letterSideBar);
        mLetterSideBar.setOnLetterTouchListener(this);
    }

    @Override
    public void touch(String currentLetter) {
        mTvLetter.setText(currentLetter);
        mTvLetter.setVisibility(View.VISIBLE);
    }

    @Override
    public void up() {
        mTvLetter.setVisibility(View.GONE);
    }
}
