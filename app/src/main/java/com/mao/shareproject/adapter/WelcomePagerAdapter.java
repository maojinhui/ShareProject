package com.mao.shareproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mao.shareproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WelcomePagerAdapter extends PagerAdapter {

    List<Integer> data;
    Context context;

    public WelcomePagerAdapter(List<Integer> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.acitivity_welcome_pager_item, null);
        ViewHolder holder = new ViewHolder(view);
        holder.imageView.setImageResource(data.get(position));

//        if (position == getCount() - 1) {
//            holder.imageView.setOnTouchListener(new View.OnTouchListener() {
//                float startX;
//                float startY;
//
//                float moveX;
//                float moveY;
//
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
////                    v.performClick();
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//                            startX = event.getRawX();
//                            startY= event.getRawY();
//                            Log.i("maomao",startX+"=="+startY);
//                            break;
//                        case MotionEvent.ACTION_MOVE:
//
//                            break;
//                        case MotionEvent.ACTION_CANCEL:
//                            moveX = event.getRawX();
//                            moveY = event.getRawY();
//                            Log.i("maomao","move:"+startX+"=="+startY);
//                            break;
//                    }
//                    return false;
//                }
//            });
//        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }


    static class ViewHolder {
        @BindView(R.id.activity_welcom_pager_item_img)
        ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
