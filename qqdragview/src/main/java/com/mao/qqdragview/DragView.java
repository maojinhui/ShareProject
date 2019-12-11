package com.mao.qqdragview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;

public class DragView extends View {

    //气泡半斤
    private float mBubbleRadius;
    //气泡背景色
    private int mBubbleColor;
    //气泡内文字
    private String mText;
    //气泡内文字大小
    private float mTextSize;
    //气泡内文本颜色
    private int mTextColor;


    /**
     * 不动气泡的半径
     */
    private float mBubFixedRadius;
    /**
     * 可动气泡的半径
     */
    private float mBubMovableRadius;


    /**
     * 不动气泡的圆心
     */
    private PointF mBubFixedCenter;

    /**
     * 可动气泡的圆心
     */
    private PointF mBubMovableCenter;


    /**
     * 气泡相连状态最大圆心距离
     */
    private float mMaxDist;

    /**
     * 气泡的画笔
     */
    private Paint mBubblePaint;
    /**
     * 贝塞尔曲线path
     */
    private Path mBezierPath;
    /**
     * 绘制文本的画笔
     */
    private Paint mTextPaint;
    /**
     * 文本绘制区域
     */
    private Rect mTextRect;
    /**
     * 爆炸图片的画笔
     */
    private Paint mBurstPaint;
    /**
     * 爆炸绘制区域
     */
    private Rect mBurstRect;
    /**
     * 气泡爆炸的bitmap数组
     */
    private Bitmap[] mBurstBitmapsArray;

    /**
     * 气泡爆炸的图片id数组
     */
    private int[] mBurstDrawablesArray = {
            R.mipmap.drag_bubble1,
            R.mipmap.drag_bubble2,
            R.mipmap.drag_bubble3,
            R.mipmap.drag_bubble4,
            R.mipmap.drag_bubble5
    };


    /**
     * 气泡静止
     */
    private static final int BUBBLE_STATE_DEFAULT = 0;
    /**
     * 气泡相连
     */
    private static final int BUBBLE_STATE_CONNECT = 1;
    /**
     * 气泡分离
     */
    private static final int BUBBLE_STATE_APART = 2;
    /**
     * 气泡消失
     */
    private final int BUBBLE_STATE_DISMISS = 3;
    /**
     * 气泡当前状态，默认静止
     */
    private int mBubbleState = BUBBLE_STATE_DEFAULT;


    public DragView(Context context) {
        this(context, null);
    }


    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs, defStyleAttr);
        init();

    }


    /**
     * 读取自定义属性的值
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    protected void readAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.DragView, defStyleAttr, 0);
        mBubbleRadius = array.getDimension(R.styleable.DragView_bubble_radius, mBubbleRadius);
        mBubbleColor = array.getColor(R.styleable.DragView_bubble_color, Color.RED);
        // 消息数字
        mText = array.getString(R.styleable.DragView_bubble_text);
        // 字体大小
        mTextSize = array.getDimension(R.styleable.DragView_bubble_textSize, mTextSize);
        // 消息数字颜色
        mTextColor = array.getColor(R.styleable.DragView_bubble_textColor, Color.YELLOW);
        // 回收属性数组
        array.recycle();
    }

    /**
     * 初始化这个view
     */
    protected void init() {
        // 两个圆初始半径一致
        mBubFixedRadius = mBubbleRadius;
        mBubMovableRadius = mBubFixedRadius;
        // 设置粘性拖拽最大距离
        mMaxDist = 16 * mBubbleRadius;
        // 该值是为了增大气泡对触摸事件的识别范围
        MOVE_OFFSET = mMaxDist / 4;

        //初始化气泡画笔
        // 设置抗锯齿
        mBubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置画笔颜色
        mBubblePaint.setColor(mBubbleColor);
        // 设置风格为填充
        mBubblePaint.setStyle(Paint.Style.FILL);

        // 初始化贝塞尔曲线Path，用于绘制粘性拖拽的粘性区域
        mBezierPath = new Path();

        // 初始化文本画笔
        // 设置抗锯齿
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置画笔颜色
        mTextPaint.setColor(mTextColor);
        // 设置文本字体大小
        mTextPaint.setTextSize(mTextSize);

        // 绘制文本的矩形区域
        mTextRect = new Rect();
        // 初始化爆炸画笔
        // 设置抗锯齿
        mBurstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 开启位图滤波处理（可以让图片显示更柔和）
        mBurstPaint.setFilterBitmap(true);

        // 初始化爆炸绘制区域
        mBurstRect = new Rect();

// 初始化爆炸效果的bitmap数组
        mBurstBitmapsArray = new Bitmap[mBurstDrawablesArray.length];
        for (int i = 0; i < mBurstDrawablesArray.length; i++) {
            // 将气泡爆炸的drawable转为bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mBurstDrawablesArray[i]);
            mBurstBitmapsArray[i] = bitmap;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 设置两个圆心初始位置在View中心位置，也可以通过自定义属性将此属性由外部自由设置
        //初始化不动气泡的圆心
        if (mBubFixedCenter == null) {
            mBubFixedCenter = new PointF(w / 2f, h / 2f);
        } else {
            mBubFixedCenter.set(w / 2f, h / 2f);
        }

        //初始化可动气泡的圆心
        if (mBubMovableCenter == null) {
            mBubMovableCenter = new PointF(w / 2f, h / 2f);
        } else {
            mBubMovableCenter.set(w / 2f, h / 2f);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBubbleState != BUBBLE_STATE_DISMISS) {
            // 绘制一个大小不变的气泡（可动气泡）
            canvas.drawCircle(mBubMovableCenter.x, mBubMovableCenter.y, mBubbleRadius, mBubblePaint);
            // 测量消息数的文本，并将测量数据保存在mTextRect中
            mTextPaint.getTextBounds(mText, 0, mText.length(), mTextRect);
            // 绘制文本在可动气泡的中心（参数位置是绘制区域的左下角的坐标）
            canvas.drawText(mText, mBubMovableCenter.x - mTextRect.width() / 2f,
                    mBubMovableCenter.y + mTextRect.height() / 2f, mTextPaint);
        }else if (mCurDrawableIndex < mBurstBitmapsArray.length) {
            // 爆炸效果
            // 设置图片绘制区域四点坐标，即可动圆当前位置的矩形区域(left,top,right,bottom)
            mBurstRect.set(
                    (int)(mBubMovableCenter.x - mBubMovableRadius),
                    (int)(mBubMovableCenter.y - mBubMovableRadius),
                    (int)(mBubMovableCenter.x + mBubMovableRadius),
                    (int)(mBubMovableCenter.y + mBubMovableRadius)
            );
            // 绘制爆炸图片
            canvas.drawBitmap(mBurstBitmapsArray[mCurDrawableIndex], null, mBurstRect, mBurstPaint);
        }



        if (mBubbleState == BUBBLE_STATE_CONNECT) {
            // 绘制不动气泡
            canvas.drawCircle(mBubFixedCenter.x, mBubFixedCenter.y, mBubFixedRadius, mBubblePaint);
            // 绘制贝塞尔曲线，两个二阶贝塞尔，同一个控制点(圆心连线的中点),两个起点、两个终点
            // 控制点坐标，圆心连线中心点
            int iAnchorX = (int) ((mBubFixedCenter.x + mBubMovableCenter.x) / 2);
            int iAnchorY = (int) ((mBubFixedCenter.y + mBubMovableCenter.y) / 2);

            // Theta是圆心连线在x方向上的夹角，分别求出正弦和余弦值，mDist是两个圆心当前距离
            float sinTheta = (mBubMovableCenter.y - mBubFixedCenter.y) / mDist;
            float cosTheta = (mBubMovableCenter.x - mBubFixedCenter.x) / mDist;

            //B点，第一条贝尔塞起点
            float iBubMovableStartX = mBubMovableCenter.x - sinTheta * mBubMovableRadius;
            float iBubMovableStartY = mBubMovableCenter.y + cosTheta * mBubMovableRadius;
            //A点，第一条贝塞尔终点
            float iBubFixedEndX = mBubFixedCenter.x - mBubFixedRadius * sinTheta;
            float iBubFixedEndY = mBubFixedCenter.y + mBubFixedRadius * cosTheta;

            //D，第二条贝尔塞起点
            float iBubFixedStartX = mBubFixedCenter.x + mBubFixedRadius * sinTheta;
            float iBubFixedStartY = mBubFixedCenter.y - mBubFixedRadius * cosTheta;
            //C，第二条贝塞尔终点
            float iBubMovableEndX = mBubMovableCenter.x + mBubMovableRadius * sinTheta;
            float iBubMovableEndY = mBubMovableCenter.y - mBubMovableRadius * cosTheta;

            // 设置贝赛尔曲线路径
            // 每一次绘制前重置path
            mBezierPath.reset();
            // 第一条，BA
            mBezierPath.moveTo(iBubMovableStartX, iBubMovableStartY);
            mBezierPath.quadTo(iAnchorX, iAnchorY, iBubFixedEndX, iBubFixedEndY);
            // 第二条，DC
            mBezierPath.lineTo(iBubFixedStartX, iBubFixedStartY);
            mBezierPath.quadTo(iAnchorX, iAnchorY, iBubMovableEndX, iBubMovableEndY);
            // 闭合路径
            mBezierPath.close();

            // 绘制路径
            canvas.drawPath(mBezierPath, mBubblePaint);
        }

    }


    private float mDist;
    /**
     * 手指触摸偏移量
     */
    private static float MOVE_OFFSET;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 非消失状态
                if (mBubbleState != BUBBLE_STATE_DISMISS) {
                    // 计算两个圆心的距离，当前触摸位置即为可动圆的圆心
                    mDist = (float) Math.hypot(event.getX() - mBubFixedCenter.x, event.getY() - mBubFixedCenter.y);
                    // 为了方便进行拖拽，增大拖拽识别范围
                    if (mDist < mBubbleRadius + MOVE_OFFSET) {
                        // 更改为连接状态
                        mBubbleState = BUBBLE_STATE_CONNECT;
                    } else {
                        // 重置为默认状态
                        mBubbleState = BUBBLE_STATE_DEFAULT;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 非静止状态
                if (mBubbleState != BUBBLE_STATE_DEFAULT) {
                    // 计算两个圆心的距离，当前触摸位置即为可动圆的圆心
                    mDist = (float) Math.hypot(event.getX() - mBubFixedCenter.x, event.getY() - mBubFixedCenter.y);
                    //修改可动圆的圆心为触摸点
                    mBubMovableCenter.x = event.getX();
                    mBubMovableCenter.y = event.getY();
                    // 连接状态
                    if (mBubbleState == BUBBLE_STATE_CONNECT) {
                        if (mDist < mMaxDist - MOVE_OFFSET) {
                            //当拖拽距离在指定范围内，调整不动圆半径
                            mBubFixedRadius = mBubbleRadius - mDist / 16;
                        } else {
                            //超过指定范围，分离状态
                            mBubbleState = BUBBLE_STATE_APART;
                        }
                    }
                    // 重绘
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                // 连接状态下松开
                if (mBubbleState == BUBBLE_STATE_CONNECT) {
                    // 回弹效果
                    startBubbleRestAnim();
                } else if (mBubbleState == BUBBLE_STATE_APART) {
                    // 分离状态下松开
                    if (mDist < MOVE_OFFSET) {
                        // 距离较近时，回弹，不爆炸
                        startBubbleRestAnim();
                    } else {
                        // 爆炸效果
//                        startBubbleBurstAnim();
                    }
                }
                break;


        }


        return true;
    }


    /**
     * 回弹动画
     */
    private void startBubbleRestAnim() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        // PointF估值器（Android封装好的） 起始点（当前可动气泡位置） 终止点（不动气泡的位置）
        ValueAnimator anim = null;

            anim = ValueAnimator.ofObject(new PointFEvaluator(),
                    new PointF(mBubMovableCenter.x, mBubMovableCenter.y),
                    new PointF(mBubFixedCenter.x, mBubFixedCenter.y));

        // 设置动画时长
        anim.setDuration(200);
        // 设置插值器，该插值器可以实现回弹效果，5f表示回弹时最大偏移量
        anim.setInterpolator(new OvershootInterpolator(5f));
        // 设置监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 修改当前可动气泡的圆心
                mBubMovableCenter = (PointF) animation.getAnimatedValue();
                // 重绘
                invalidate();
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 还原状态为静止
                mBubbleState = BUBBLE_STATE_DEFAULT;
            }
        });
        // 开启动画
        anim.start();

        }
    }

    private int mCurDrawableIndex;

    /**
     * 爆炸动画
     */
    private void startBubbleBurstAnim() {
        // 设置当前状态为消失状态
        mBubbleState = BUBBLE_STATE_DISMISS;
        // 初始化动画，0到mBurstBitmapsArray.length的int属性线性插值动画
        ValueAnimator anim = ValueAnimator.ofInt(0, mBurstBitmapsArray.length);
        // 设置动画时长
        anim.setDuration(500);
        // 设置线性插值器
        anim.setInterpolator(new LinearInterpolator());
        // 设置监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 修改当前爆炸效果图片的index
                mCurDrawableIndex = (int) animation.getAnimatedValue();
                // 重绘
                invalidate();
            }
        });
        // 开启动画
        anim.start();
    }



    public void reset(){
        // 重置状态
        mBubbleState = BUBBLE_STATE_DEFAULT;
        // 重置可动气泡圆心位置
        mBubMovableCenter = new PointF(mBubFixedCenter.x, mBubFixedCenter.y);
        // 重绘
        invalidate();
    }



}
