package com.tangerine.customview.day_03;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.tangerine.customview.R;

/**
 * Created by Tangerine on 2020-6-25.
 */
public class RatingBar extends View {
    private static final String TAG = "RatingBar";
    private Bitmap mBitMapNormal;
    private Bitmap mBitMapFocus;
    private int mGradeNumber = 5;
    private int mHorizontalDirectionPadding = 100;
    private int mVerticalDirectionPadding = 100;
    private int mCurrentGradeNumber = 0;

    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        int startNormalId = typedArray.getResourceId(R.styleable.RatingBar_starNormal, 0);
        if (startNormalId == 0) {
            throw new RuntimeException("the startNormalId must be set");
        }
        mBitMapNormal = BitmapFactory.decodeResource(getResources(), startNormalId);
        int focusId = typedArray.getResourceId(R.styleable.RatingBar_starFocus, 0);
        if (focusId == 0) {
            throw new RuntimeException("the focusId must be set");
        }
        mBitMapFocus = BitmapFactory.decodeResource(getResources(), focusId);
        mGradeNumber = typedArray.getInt(R.styleable.RatingBar_gradeNumber, mGradeNumber);
        mHorizontalDirectionPadding = typedArray.getDimensionPixelSize(R.styleable.RatingBar_starHorizontalPadding,mHorizontalDirectionPadding);
        mVerticalDirectionPadding = typedArray.getDimensionPixelSize(R.styleable.RatingBar_starVerticalPadding,mVerticalDirectionPadding);
        typedArray.recycle();


    }

    //指定控件的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //双指wrap_content;
        //双指match_parent;
        //单指match_parent,wrap_content
        //height  the height of pic
        int height = mBitMapFocus.getHeight() + mVerticalDirectionPadding;
        int width = mBitMapFocus.getWidth() * mGradeNumber + mHorizontalDirectionPadding; // add padding value ;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mGradeNumber; i++) {

            if (mCurrentGradeNumber> i ){
                canvas.drawBitmap(mBitMapFocus, i * mBitMapFocus.getWidth() + (float) (i * mHorizontalDirectionPadding / (mGradeNumber - 1)), (float) (mVerticalDirectionPadding / 2), null);
            }else{
                canvas.drawBitmap(mBitMapNormal, i * mBitMapNormal.getWidth() + (float) (i * mHorizontalDirectionPadding / (mGradeNumber - 1)), (float) (mVerticalDirectionPadding / 2), null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //移动 按下 抬起 处理逻辑都是一样的 ，判断手指的位置 ，根据当前的位置计算出分数，再去刷新显示
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX(); //event.getRawX()获取屏幕的x位置，event.getX()获取当前控件的x位置
                Log.e(TAG, "onTouchEvent: ----->" + moveX );
                int currentGrade  = (int) (moveX/(mBitMapNormal.getWidth() +mHorizontalDirectionPadding / (mGradeNumber - 1) )) + 1;
                Log.e(TAG, "onTouchEvent: ----->" + currentGrade );


                if (currentGrade < 0){
                    currentGrade = 0;
                }
                if (currentGrade >mGradeNumber){
                    currentGrade = mGradeNumber;
                }
                if (mCurrentGradeNumber != currentGrade){
                    mCurrentGradeNumber = currentGrade;
                    invalidate();
                }

//                if (moveX < 0){
//                    mGradeNumber = 0;
//                }
//                if (currentGrade > mGradeNumber){
//                    currentGrade =mGradeNumber;
//                }
//
//                invalidate();
                break;
            //                if (moveX < 0){
//                    mGradeNumber = 0;
//                }
//                if (currentGrade > mGradeNumber){
//                    currentGrade =mGradeNumber;
//                }
//
//                invalidate();
            default:
                break;
        }
        return true; //onTouch false不消费 第一次 down false down以后的事件都不会进来
    }
    public int getCurrentGrade(){
        return mCurrentGradeNumber;
    }
}
