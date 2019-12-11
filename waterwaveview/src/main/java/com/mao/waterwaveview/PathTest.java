package com.mao.waterwaveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.Nullable;

public class PathTest extends View {

    private static final String TAG = "MAOMAO";

    private Paint mPaint;

    int mWidth;

    int mHeight;


    int maxWidth = Integer.MAX_VALUE - 8;

    int maxHeight = Integer.MAX_VALUE - 8;


    int defaultWidth = 400;
    int defaultHeight = 400;


    public PathTest(Context context) {
        super(context);
        init();
    }

    public PathTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW); //画笔颜色
        mPaint.setStyle(Paint.Style.STROKE); //描边
        mPaint.setStrokeWidth(10); // 边框宽度

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);

        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        ViewGroup parent = (ViewGroup) getParent();


        switch (wMode) {
            case MeasureSpec.UNSPECIFIED:
                mWidth = maxWidth;
                break;
            case MeasureSpec.EXACTLY:
                mWidth = wSize;
                break;
            case MeasureSpec.AT_MOST:
                mWidth = defaultWidth;
                break;
        }

        switch (hMode) {
            case MeasureSpec.UNSPECIFIED:
                mHeight = maxHeight;
                break;
            case MeasureSpec.EXACTLY:
                mHeight = hSize;
                break;
            case MeasureSpec.AT_MOST:
                mHeight=defaultHeight;

                break;
        }
        setMeasuredDimension(mWidth,maxHeight);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);//移动画布坐标

        Path path = new Path();
        path.lineTo(200, 200);
        path.moveTo(0, 0);
        path.lineTo(-150, -200);
        path.lineTo(-200,-150);
        path.close();
//        path.lineTo(200,0);
        canvas.drawPath(path, mPaint);


    }
}
