package com.mao.shareproject.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class DispatchViewGroup extends ViewGroup {


    private static final String TAG = DispatchViewGroup.class.getName();


    private int maxWidth = 500;

    private int maxHeight = 500;



    public DispatchViewGroup(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public DispatchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public DispatchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }


//    public DispatchViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: ACTION_DOWN");

                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: ACTION_MOVE");

                break;
            case MotionEvent.ACTION_MASK:
                Log.d(TAG, "dispatchTouchEvent: ACTION_MASK");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: ACTION_CANCEL");

                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: ACTION_up");
                break;


        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("maomao", "viewgroup:onTouchEvent");
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) {
        super.measureChildren(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);

        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);




        int measureWidth = 0;
        int measureHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);

            if(view.getVisibility()==View.GONE){
                continue;
            }
            MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
//            measureChildWithMargins(view,widthMeasureSpec,0,heightMeasureSpec,0);
            measureWidth += view.getMeasuredWidth()+ layoutParams.leftMargin+layoutParams.rightMargin;
            measureHeight += view.getMeasuredHeight() +layoutParams.topMargin+layoutParams.bottomMargin;

        }

        switch (wMode){
            case MeasureSpec.UNSPECIFIED:
                measureWidth = Math.max(measureWidth, maxWidth);
                break;
                case MeasureSpec.EXACTLY:
                    measureWidth=wSize;
                    break;
                    case MeasureSpec.AT_MOST:
                        break;

        }

        switch (hMode){
            case MeasureSpec.UNSPECIFIED:
                measureHeight = Math.max(measureHeight, maxHeight);
                break;
            case MeasureSpec.EXACTLY:
                measureHeight=hSize;
                break;
            case MeasureSpec.AT_MOST:
                break;

        }

        setMeasuredDimension(measureWidth,measureHeight);

        Log.i(TAG,measureWidth+"=="+measureHeight);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int mPainterPosX = l;//当前绘制光标X坐标
        int mPainterPosY = t;//当前绘制光标Y坐标


        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() == View.GONE) {
                continue;
            }
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();

            //如果剩余控件不够，则移到下一行开始位置
            if (mPainterPosX + width > measuredWidth) {
                mPainterPosX = l;
                mPainterPosY += height;
            }
            MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();


            view.layout(mPainterPosX + layoutParams.leftMargin, mPainterPosY + layoutParams.topMargin,
                    mPainterPosX + width + layoutParams.leftMargin, mPainterPosY + height + layoutParams.topMargin);

            mPainterPosX = mPainterPosX + width + layoutParams.leftMargin + layoutParams.rightMargin;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams layoutParams = new MarginLayoutParams(getContext(), attrs);
        return layoutParams;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG," ViewGroup ondraw");
        Paint paint =new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

        Path path = new Path();
        path.moveTo(0  ,0);
        path.lineTo(getMeasuredWidth(),0);
        path.lineTo(getMeasuredWidth(),getMeasuredHeight());
        path.lineTo(0,getMeasuredHeight());
        path.lineTo(0,0);

        canvas.drawPath(path,paint);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);


    }
}
