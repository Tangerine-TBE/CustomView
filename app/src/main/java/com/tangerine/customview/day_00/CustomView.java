package com.tangerine.customview.day_00;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private Paint mTextPain;
    private int mStep = 99;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String willDrawText = 99+"";
        Rect rect = new Rect();
        mTextPain.getTextBounds(willDrawText,0,willDrawText.length(),rect);
        int dx = getWidth()/2 - rect.width()/2;
        Paint.FontMetricsInt fontMetricsInt = mTextPain.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 -fontMetricsInt.bottom;
        int baseLine =  getHeight()/2+ dy;
        canvas.drawText(willDrawText,dx,baseLine,mTextPain);


    }
}
