package com.tangerine.customview.day_00;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.tangerine.customview.R;

public class CustomView extends View {
    private int outerColor = Color.BLUE;
    private int innerColor = Color.RED;
    private int borderWidth = 16;
    private int stepTextColor = Color.YELLOW;
    private int stepTextSize = 16;
    private Paint mOuterPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;
    private int mMaxStep; //最大步数
    private int mCurrentStep;//当前步数

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1.分析效果
        //2.确定自定义属性,编写attrs.xml
        //3.在布局中使用自定义属性
        //4.在自定义View中获取自定义属性
        //5.onMeasure
        //6.画外圆弧，内圆弧，文字
        //7.其他
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        outerColor = array.getColor(R.styleable.QQStepView_outerColor, outerColor);
        innerColor = array.getColor(R.styleable.QQStepView_innerColor, innerColor);
        borderWidth = array.getDimensionPixelSize(R.styleable.QQStepView_borderWidth, borderWidth);
        stepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, stepTextColor);
        stepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, stepTextSize);
        array.recycle();
        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setStrokeWidth(borderWidth);//宽度
        mOuterPaint.setColor(outerColor);
        mOuterPaint.setStyle(Paint.Style.STROKE);


        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(borderWidth);//宽度
        mInnerPaint.setColor(innerColor);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(stepTextColor);
        mTextPaint.setTextSize(stepTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //调用者，在布局文件中可能是  wrap_content 可能是宽度高度不一致
        //获取模式 AT_MOST
        //宽度高度不一致 取最小值，确保是一个正方形
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize > heightSize ? heightSize : widthSize, widthSize > heightSize ? heightSize : widthSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画画啦！
        //1.画外弧
        //描边有宽度，这个宽度是
        int center = getWidth() / 2;//长度的中心点
        int radius = getWidth() / 2 - borderWidth / 2;//半径
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        canvas.drawArc(rectF, 135, 270, false, mOuterPaint);
        //2.画内圆弧 ，圆弧的跨度不能写死，百分比，接口传入
        if (mMaxStep == 0) {
            return;
        }
        float sweepAngle = (float) mCurrentStep / mMaxStep;
        canvas.drawArc(rectF, 135, sweepAngle * 270, false, mInnerPaint);
        //3.画文字
        String stepText = mCurrentStep + "";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        int textX = getWidth() / 2 - textBounds.width() / 2;
        //基线
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int textY = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + textY;
        canvas.drawText(stepText, textX, baseLine, mTextPaint);
    }

    //设置MaxStep CurrentStep
    private  void setStepMax(int maxStep) {
        this.mMaxStep = maxStep;
    }
    //保证多线程操作不会出问题
    private  synchronized void setCurrentStep(int currentStep){
        this.mCurrentStep = currentStep;
        // 不断进行绘制
        invalidate();
    }

    /**
     * @param stepMax  The counter is displayed at most
     * @param duration  Animation delay
     * @param offsetStart Start animation vector offset
     * @param offsetEnd End animation vector offset
     */
    private void build(int stepMax,int duration,int offsetStart,int offsetEnd){
        setStepMax(stepMax);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(offsetStart,offsetEnd);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new DecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setCurrentStep((int) value);
            }
        });
        valueAnimator.start();
    }

}
