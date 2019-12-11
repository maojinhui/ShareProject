package com.mao.shareproject.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MyRelativieLayout extends RelativeLayout {
    public MyRelativieLayout(Context context) {
        super(context);
    }

    public MyRelativieLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativieLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
