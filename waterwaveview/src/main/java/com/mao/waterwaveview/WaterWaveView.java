package com.mao.waterwaveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class WaterWaveView extends View {




    private Paint mPaint;
    private Path mPath;
    //波纹的宽度
    private int mItemWaveLength = 500;
    //波纹每次移动的距离
    private int dx;



    public WaterWaveView(Context context) {
        super(context);
        init();
    }

    public WaterWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("WaterWaveView","onMeasure"+System.currentTimeMillis());
//        measure(widthMeasureSpec,heightMeasureSpec);
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        int width = mMeasure(200,widthMeasureSpec);
        int height = mMeasure(200,heightMeasureSpec);
        mItemWaveLength = width/4;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("WaterWaveView","onLayout-----"+System.currentTimeMillis());
    }



    public int mMeasure(int defaultSize, int measureSpeceasureSpec) {
        int specMode = MeasureSpec.getMode(measureSpeceasureSpec);
        int specSize = MeasureSpec.getSize(measureSpeceasureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:
                defaultSize = specSize;
                Log.e("WaterWaveView", "---speMode = AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.e("WaterWaveView", "---speMode = EXACTLY");
                defaultSize = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.e("WaterWaveView", "---speMode = UNSPECIFIED");
                defaultSize = Math.max(defaultSize, specSize);
        }

      return defaultSize;
    }





    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("WaterWaveView","onSizeChanged"+System.currentTimeMillis());


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("WaterWaveView","onDraw-------"+System.currentTimeMillis());

        mPath.reset();
        //距离顶部的高度
        int originY = 0;
        //波纹宽度的一般
        int halfWaveLen = mItemWaveLength/2;
        //随着刷新，每次移动dx距离
        mPath.moveTo(-mItemWaveLength+dx,originY);
        //for循环当前屏幕中所有的波纹
        for (int i = -mItemWaveLength;i<=getWidth()+mItemWaveLength;i+=mItemWaveLength){
            mPath.rQuadTo(halfWaveLen/2,-100,halfWaveLen,0);
            mPath.rQuadTo(halfWaveLen/2,100,halfWaveLen,0);
        }
        mPath.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
        mPath.close();

        canvas.drawPath(mPath,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return super.onTouchEvent(event);
    }

    //初始化
    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }



}



