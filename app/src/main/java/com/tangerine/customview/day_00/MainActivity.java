package com.tangerine.customview.day_00;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tangerine.customview.R;
import com.tangerine.customview.day_02.ShapeView;

public class MainActivity extends AppCompatActivity   {

    private Button button;
    private ShapeView shapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeView = findViewById(R.id.shapeView);
        shapeView.postExChange(1000);
    }


}
