package com.tangerine.customview.day_01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.tangerine.customview.R;

public class ColorTrackTextView extends AppCompatTextView {
    private Paint mOriginTextPaint;
    private Paint mChangeTextPaint;
    private int mChangeColor = 0;
    private int mOriginColor = 0;
    private float mCurrentProcess = 0.0f ;
    private Direction mDirection = Direction.LEFT_TO_RIGHT;

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }


    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        int originColor = typedArray.getColor(R.styleable.ColorTrackTextView_originColor,  getTextColors().getDefaultColor());
        int changeColor = typedArray.getColor(R.styleable.ColorTrackTextView_changeColor, getTextColors().getDefaultColor());
        mOriginTextPaint = getPaintByColor(originColor);
        mChangeTextPaint = getPaintByColor(changeColor);
        typedArray.recycle();
    }

    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int middle = (int) (mCurrentProcess * getWidth());

        if (mDirection == Direction.LEFT_TO_RIGHT) {
            drawText(canvas, mChangeTextPaint, 0, middle);
            drawText(canvas, mOriginTextPaint, middle, getWidth());
        } else {
            drawText(canvas, mChangeTextPaint, getWidth() - middle, getWidth());
            drawText(canvas, mOriginTextPaint, 0, getWidth() - middle);
        }

    }

    private void drawText(Canvas canvas, Paint paint, int start, int end) {

        canvas.save();
        Rect rect = new Rect(start, 0, end, getHeight());
        canvas.clipRect(rect);//裁剪区域
        //字体开始画的位置 x
        String text = getText().toString();
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        int x = getWidth() / 2 - textBounds.width() / 2;
        //基线
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int y = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + y;
        canvas.drawText(text, x, baseLine, paint);
        canvas.restore();
    }

    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    public void setCurrentProcess(int process) {
        this.mCurrentProcess = process;
    }

    public void setCurrentProcess(float process) {
        this.mCurrentProcess = process;
        invalidate();
    }


    public void setChangeColor(int color) {
        this.mChangeTextPaint.setColor(color);

    }

    public void setOriginColor(int color) {
        this.mOriginColor = color;
    }
}
