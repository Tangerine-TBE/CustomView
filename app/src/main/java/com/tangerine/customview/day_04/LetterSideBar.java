package com.tangerine.customview.day_04;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.tangerine.customview.R;

/**
 * Created by Tangerine on 2020-6-25.
 */
public class LetterSideBar extends View {
    private int mTextSize = 40;
    private int mOriginTextColor = Color.BLUE;
    private int mChangeTextColor = Color.YELLOW;
    private Paint mTextPaint;

    private String [] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.LetterSideBar);
        mOriginTextColor = typedArray.getColor(R.styleable.LetterSideBar_textOriginColor, mOriginTextColor);
        mChangeTextColor = typedArray.getColor(R.styleable.LetterSideBar_textChangeColor, mChangeTextColor);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.LetterSideBar_textSize, mTextSize);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);

        typedArray.recycle();

    }

    //定义控件宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算指定宽度 =  padding + 字体宽度
        int textWidth = (int) mTextPaint.measureText("W");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //x 绘制在最中间 = getWidth /2 - 文字/2
        int itemHeight = (getHeight() - getPaddingTop() -getPaddingBottom())/ letters.length; //拿到每一个字母应该有的高度
        for (int i =0 ; i < letters.length ; i ++){
            int itemCenterY = i *  itemHeight + itemHeight /2 + getPaddingTop();
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom);
            int baseLineY = itemCenterY + dy;
            int textWidth = (int) mTextPaint.measureText(letters[i]);
            int x  = getWidth()/2  -textWidth /2 ;
            canvas.drawText(letters[i],x,baseLineY,mTextPaint);
        }
    }
}
