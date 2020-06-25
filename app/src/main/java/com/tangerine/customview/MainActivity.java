package com.tangerine.customview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tangerine.customview.day_00.QQStepViewActivity;
import com.tangerine.customview.day_01.ViewPagerActivity;
import com.tangerine.customview.day_02.ShapeViewActivity;
import com.tangerine.customview.day_03.RatingBarActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btnQQStepView;
    private AppCompatButton btnColorTrackTextView;
    private AppCompatButton btnShapeView;
    private AppCompatButton btnRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnQQStepView = findViewById(R.id.btn_toQQStep);
        btnColorTrackTextView = findViewById(R.id.btn_toColorTrackTextView);
        btnShapeView = findViewById(R.id.btn_toShapeView);
        btnRatingBar = findViewById(R.id.btn_toRatingBar);



        btnQQStepView.setOnClickListener(this);
        btnColorTrackTextView.setOnClickListener(this);
        btnShapeView.setOnClickListener(this);
        btnRatingBar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v == btnQQStepView){
            intent.setClass(this, QQStepViewActivity.class);
        }else if (v == btnColorTrackTextView ){
            intent.setClass(this, ViewPagerActivity.class);
        }else if (v == btnShapeView){
            intent.setClass(this,ShapeViewActivity.class);
        }else  if (v == btnRatingBar){
            intent.setClass(this, RatingBarActivity.class);
        }else{
            throw new RuntimeException("plz set the intent value");
        }
        startActivity(intent);
    }
}
