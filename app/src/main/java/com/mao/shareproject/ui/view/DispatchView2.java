package com.mao.shareproject.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DispatchView2 extends View {
    public DispatchView2(Context context) {
        super(context);
    }

    public DispatchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i("maomao","view2:onTouchEvent");

        return super.onTouchEvent(event);
    }



}
