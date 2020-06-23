package com.tangerine.customview.day_02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ShapeView extends View {
    private Shape mCurrentShape = Shape.CirCly;
    private Paint mPaint;

    public ShapeView(Context context) {
        this(context,null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //保证他是正方形
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mCurrentShape) {
            case CirCly:
                //CirCly
                int center = getWidth()/2;
                mPaint.setColor(Color.YELLOW);
                canvas.drawCircle(center,center,center,mPaint);
                break;
            case Square:
                //Square
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
                break;
            case Triangle:
                //Triangle
                break;
            default:
                break;
        }

    }

    public enum Shape {
        CirCly, Square, Triangle
    }
}
