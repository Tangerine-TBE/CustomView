package com.tangerine.customview.day_02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;

import com.tangerine.customview.R;

public class ShapeViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_view);
        ShapeView mShapeView = findViewById(R.id.ShapeView);
        mShapeView.postExChange(1000);
    }
}
