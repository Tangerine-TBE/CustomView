package com.tangerine.customview.day_00;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tangerine.customview.R;
import com.tangerine.customview.day_02.ShapeView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ShapeView shapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeView = findViewById(R.id.shapeView);
        button = findViewById(R.id.exchange);
        exchange();
    }

    private void exchange() {
         new Thread(
                 new Runnable() {
                     @Override
                     public void run() {
                         while (true){
                             shapeView.exchange();
                             try {
                                 Thread.sleep(1000);
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
                 }
         ).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange:


                break;
            default:
                break;
        }
    }
}
