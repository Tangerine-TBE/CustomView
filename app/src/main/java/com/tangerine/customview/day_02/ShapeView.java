package com.tangerine.customview.day_02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ShapeView extends View {
    private Shape mCurrentShape = Shape.CirCly;
    private Paint mPaint;
    private Path mPath;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mCurrentShape) {
            case CirCly:
                //CirCly
                int center = getWidth() / 2;
                mPaint.setColor(Color.YELLOW);
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                //Square
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                //等边三角形
                mPaint.setColor(Color.RED);
                if (mPath == null) {
                    mPath = new Path();
                    mPath.moveTo((float)(getWidth() / 2) , 0);
                    mPath.lineTo(0, (float) ((getWidth()/2 ) *Math.sqrt(3)));
                    mPath.lineTo(getWidth(), (float) ((getWidth()/2) * Math.sqrt(3)));
                    mPath.close();
                }
                //getWith /2 ,

                canvas.drawPath(mPath,mPaint);
                //Triangle
                break;
            default:
                break;
        }

    }

    public void exchange() {
        switch (mCurrentShape) {
            case CirCly:
                mCurrentShape = Shape.Square;
                break;
            case Square:
                mCurrentShape = Shape.Triangle;
                break;
            case Triangle:
                mCurrentShape = Shape.CirCly;
                break;
            default:
                break;

        }
        postInvalidate();
    }

    public enum Shape {
        CirCly, Square, Triangle
    }
}
