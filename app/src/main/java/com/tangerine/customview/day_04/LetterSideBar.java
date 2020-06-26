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
    private Paint mSideBarOriginTextPaint;
    private Paint mSideBarChangeTextPaint;

    private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private String mCurrentTouchLetter; //当前触摸位置的字母

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

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
        mOriginTextColor = typedArray.getColor(R.styleable.LetterSideBar_textOriginColor, mOriginTextColor);
        mChangeTextColor = typedArray.getColor(R.styleable.LetterSideBar_textChangeColor, mChangeTextColor);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.LetterSideBar_textSize, mTextSize);
        mSideBarOriginTextPaint = new Paint();
        mSideBarOriginTextPaint.setAntiAlias(true);
        mSideBarOriginTextPaint.setTextSize(mTextSize);
        mSideBarOriginTextPaint.setColor(mOriginTextColor);


        mSideBarChangeTextPaint = new Paint();
        mSideBarChangeTextPaint.setAntiAlias(true);
        mSideBarChangeTextPaint.setTextSize(mTextSize);
        mSideBarChangeTextPaint.setColor(mChangeTextColor);



        typedArray.recycle();

    }

    //定义控件宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算指定宽度 =  padding + 字体宽度
        int textWidth = (int) mSideBarOriginTextPaint.measureText("W");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                float currentMoveY = event.getY();
                //当前的高度/字母的高度   通过位置获取字母
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / letters.length; //拿到每一个字母应该有的高度
                int currentPosition = (int) (currentMoveY /itemHeight ) ; //拿到每一个字母应该有的高度
                if (currentPosition < 0){
                    currentPosition = 0;
                }
                if (currentPosition > letters.length - 1){
                    currentPosition = letters.length -1;
                }
                if (!letters[currentPosition].equals(mCurrentTouchLetter)){
                    mCurrentTouchLetter = letters[currentPosition];

                    if (mLetterTouchListener != null){
                        mLetterTouchListener.touch(mCurrentTouchLetter);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mLetterTouchListener != null){
                    mLetterTouchListener.up();
                }
                mCurrentTouchLetter="";
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //x 绘制在最中间 = getWidth /2 - 文字/2
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / letters.length; //拿到每一个字母应该有的高度
        for (int i = 0; i < letters.length; i++) {
            int itemCenterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            Paint.FontMetrics fontMetrics = mSideBarOriginTextPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLineY = itemCenterY + dy;
            int textWidth = (int) mSideBarOriginTextPaint.measureText(letters[i]);
            int x = getWidth() / 2 - textWidth / 2;

            //当前字母高亮
            if(letters[i].equals(mCurrentTouchLetter)){
                canvas.drawText(letters[i],x ,baseLineY,mSideBarChangeTextPaint);
            }else{
                canvas.drawText(letters[i],x,baseLineY,mSideBarOriginTextPaint);
            }
        }
    }
    private LetterTouchListener mLetterTouchListener;
    public void setOnLetterTouchListener(LetterTouchListener letterTouchListener){
        this.mLetterTouchListener = letterTouchListener;
    }

    public  interface  LetterTouchListener{
        void touch(String currentLetter);
        void up();
    }
}
