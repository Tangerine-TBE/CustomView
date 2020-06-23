package com.tangerine.customview.day_00;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import com.tangerine.customview.R;
import com.tangerine.customview.day_01.ColorTrackTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ColorTrackTextView mColorTrackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColorTrackTextView = findViewById(R.id.colortrackTextView);
        AppCompatButton rightToLeft = findViewById(R.id.right_to_left);
        AppCompatButton leftToRight = findViewById(R.id.left_to_right);
        rightToLeft.setOnClickListener(this);
        leftToRight.setOnClickListener(this);
    }

    private void rightToLeft(){
        mColorTrackTextView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        ValueAnimator valueAnimator  = ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProcess = (float) animation.getAnimatedValue();
                mColorTrackTextView.setCurrentProcess(currentProcess);
            }
        });
        valueAnimator.start();

    }
    private void leftToRight(){
        mColorTrackTextView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
        ValueAnimator valueAnimator  = ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProcess = (float) animation.getAnimatedValue();
                mColorTrackTextView.setCurrentProcess(currentProcess);
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_to_left:
                rightToLeft();
                break;
            case R.id.left_to_right:
                leftToRight();
                break;
            default:
                break;
        }
    }
}
