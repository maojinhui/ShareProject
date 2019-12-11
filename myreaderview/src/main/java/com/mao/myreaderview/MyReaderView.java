package com.mao.myreaderview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyReaderView extends View {





    static final String TAG = "maomao";

    /**
     * 默认宽高
     */
    int defaultWidth = 500;
    int defaultHeight = 500;
    /**
     * 最大宽高
     */
    int maxSize = Integer.MAX_VALUE - 8;

    int mWidth;
    int mHeight;


    public static final String STYLE_TOP_RIGHT = "STYLE_TOP_RIGHT";//f点在右上角
    public static final String STYLE_LOWER_RIGHT = "STYLE_LOWER_RIGHT";//f点在右下角


    public MyReaderView(Context context) {
        super(context);
        init();
    }

    public MyReaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyReaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = measureView(defaultWidth, widthMeasureSpec);
        mHeight = measureView(defaultHeight, heightMeasureSpec);
        Log.d(TAG, "onMeasure:width:" + mWidth + ",height:" + mHeight);
        setMeasuredDimension(mWidth, mHeight);
        a.x = -1;
        a.y = -1;

    }

    private String  content = "1、Canvas转Bitmap，而且可以先指定Bitmap的大小\n" +
            "\n" +
            "Bitmap bitmap=Bitmap.createBitmap(400, 200, Config.ARGB_8888);//这里400和200换成你自己想要的长和宽\n" +
            "Canvas canvas=new Canvas(bitmap);\n" +
            "//接下来该怎么draw就怎么draw\n" +
            "\n" +
            "2、Bitmap转ImageView，这个简单了\n" +
            "\n" +
            "ImageView iv = new ImageView(this);\n" +
            "\n" +
            "iv.setImageBitmap(bitmap);\n" +
            "————————————————\n" +
            "版权声明：本文为CSDN博主「厦门李工」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\n" +
            "原文链接：https://blog.csdn.net/mrlixirong/article/details/46322941" ;

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 用来测量view
     *
     * @param defaultSize
     * @param widthMeasureSpec
     * @return
     */
    private int measureView(int defaultSize, int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mysize = defaultSize;
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                mysize = maxSize;
                break;
            case MeasureSpec.AT_MOST:
                mysize = Math.max(mysize, size);
                break;
            case MeasureSpec.EXACTLY:
                mysize = size;
                break;
        }
        return mysize;
    }


    private Paint pathAPaint, pathBPaint, pathCPaint;

    private Point a, b, c, d, e, f, g, h, i, j, k;

    private Path pathA, pathB, pathC;

    private void init() {


        pathAPaint = new Paint();
        pathAPaint.setColor(Color.GREEN);
        pathAPaint.setAntiAlias(true);//设置抗锯齿

        pathBPaint = new Paint();
        pathBPaint.setColor(Color.GRAY);
        pathBPaint.setAntiAlias(true);//设置抗锯齿
        pathBPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        pathCPaint = new Paint();
        pathCPaint.setColor(Color.YELLOW);
        pathCPaint.setAntiAlias(true);//设置抗锯齿
        pathCPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));


        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setSubpixelText(true);//设置自像素。如果该项为true，将有助于文本在LCD屏幕上的显示效果。
        textPaint.setTextSize(30);




        a = new Point();
        f = new Point();
        g = new Point();
        e = new Point();
        h = new Point();
        c = new Point();
        j = new Point();
        b = new Point();
        k = new Point();
        d = new Point();
        i = new Point();

        pathA = new Path();
        pathB = new Path();
        pathC = new Path();


    }



    private Bitmap bitmap;//缓存bitmap
    private Canvas bitmapCanvas;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint = new Paint();
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setAntiAlias(true);
//        mPaint.setStrokeWidth(32);
//        mPaint.setColor(Color.WHITE);
//        mPaint.setTextSize(100);
//          camera =new Camera();
//        canvas.save();
//        camera.save();
//        canvas.translate(mWidth/2,mHeight/2);
//        camera.rotateX(15);
//        canvas.translate(-mWidth/2,-mHeight/2);
//        camera.applyToCanvas(canvas);
//
//        camera.restore();
//        canvas.drawText("Hello World",100,100,mPaint);
//        canvas.restore();
//        getd




        bitmap = Bitmap.createBitmap((int) mWidth, (int) mHeight, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);



        if (a.x == -1 && a.y == -1) {
//            bitmapCanvas.drawPath(getPathDefault(), pathAPaint);
            drawPathAContent(bitmapCanvas,getPathDefault(),pathAPaint);
        } else {
            if (f.x == mWidth && f.y == 0) {
//                bitmapCanvas.drawPath(getPathAFromTopRight(), pathAPaint);
                drawPathAContent(bitmapCanvas,getPathAFromTopRight(),pathAPaint);
            } else if (f.x == mWidth && f.y == mHeight) {
//                bitmapCanvas.drawPath(getPathAFromLowerRight(), pathAPaint);
                drawPathAContent(bitmapCanvas,getPathAFromLowerRight(),pathAPaint);

            }
            bitmapCanvas.clipPath(getPathB());

            bitmapCanvas.drawPath(getPathC(), pathCPaint);
//            bitmapCanvas.drawPath(getPathB(), pathBPaint);
        }
        canvas.drawBitmap(bitmap, 0, 0, null);
    }


    Paint textPaint;


    /**
     * 绘制A区域内容
     * @param canvas
     * @param pathA
     * @param pathPaint
     */
    private void drawPathAContent(Canvas canvas, Path pathA, Paint pathPaint){
        Bitmap contentBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.RGB_565);
        Canvas contentCanvas = new Canvas(contentBitmap);

        //下面开始绘制区域内的内容...
        contentCanvas.drawPath(pathA,pathPaint);//绘制一个背景，用来区分各区域
        contentCanvas.drawText(content, mWidth-260, mHeight-100, textPaint);

        //结束绘制区域内的内容...

        canvas.save();
        canvas.clipPath(pathA, Region.Op.INTERSECT);//对绘制内容进行裁剪，取和A区域的交集
        canvas.drawBitmap(contentBitmap, 0, 0, null);
        canvas.restore();
    }


    /**
     * 获取f点在右下角的pathA
     *
     * @return
     */
    private Path getPathAFromLowerRight() {
        pathA.reset();
        pathA.lineTo(0, mHeight);//移动到左下角
        pathA.lineTo(c.x, c.y);//移动到c点
        pathA.quadTo(e.x, e.y, b.x, b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x, a.y);//移动到a点
        pathA.lineTo(k.x, k.y);//移动到k点
        pathA.quadTo(h.x, h.y, j.x, j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(mWidth, 0);//移动到右上角
        pathA.close();//闭合区域
        return pathA;
    }

    /**
     * 绘制区域B
     *
     * @return
     */
    private Path getPathB() {
        pathB.reset();
        pathB.lineTo(0, mHeight);//移动到左下角
        pathB.lineTo(mWidth, mHeight);//移动到右下角
        pathB.lineTo(mWidth, 0);//移动到右上角
        pathB.close();//闭合区域
        return pathB;
    }

    /**
     * 绘制区域C
     *
     * @return
     */
    private Path getPathC() {
        pathC.reset();
        pathC.moveTo(i.x, i.y);//移动到i点
        pathC.lineTo(d.x, d.y);//移动到d点
        pathC.lineTo(b.x, b.y);//移动到b点
        pathC.lineTo(a.x, a.y);//移动到a点
        pathC.lineTo(k.x, k.y);//移动到k点
        pathC.close();//闭合区域
        return pathC;
    }

    /**
     * 计算各点坐标
     *
     * @param a
     * @param f
     */
    private void calcPointsXY(Point a, Point f) {
        g.x = (a.x + f.x) / 2;
        g.y = (a.y + f.y) / 2;

        e.x = g.x - (f.y - g.y) * (f.y - g.y) / (f.x - g.x);
        e.y = f.y;

        h.x = f.x;
        h.y = g.y - (f.x - g.x) * (f.x - g.x) / (f.y - g.y);

        c.x = e.x - (f.x - e.x) / 2;
        c.y = f.y;

        j.x = f.x;
        j.y = h.y - (f.y - h.y) / 2;

        b = getIntersectionPoint(a, e, c, j);
        k = getIntersectionPoint(a, h, c, j);

        d.x = (c.x + 2 * e.x + b.x) / 4;
        d.y = (2 * e.y + c.y + b.y) / 4;

        i.x = (j.x + 2 * h.x + k.x) / 4;
        i.y = (2 * h.y + j.y + k.y) / 4;
    }

    /**
     * 计算两线段相交点坐标
     *
     * @param lineOne_My_pointOne
     * @param lineOne_My_pointTwo
     * @param lineTwo_My_pointOne
     * @param lineTwo_My_pointTwo
     * @return 返回该点
     */
    private Point getIntersectionPoint(Point lineOne_My_pointOne, Point lineOne_My_pointTwo, Point lineTwo_My_pointOne, Point lineTwo_My_pointTwo) {
        float x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = lineOne_My_pointOne.x;
        y1 = lineOne_My_pointOne.y;
        x2 = lineOne_My_pointTwo.x;
        y2 = lineOne_My_pointTwo.y;
        x3 = lineTwo_My_pointOne.x;
        y3 = lineTwo_My_pointOne.y;
        x4 = lineTwo_My_pointTwo.x;
        y4 = lineTwo_My_pointTwo.y;
        float pointX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
                / ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));
        float pointY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
                / ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));
        return new Point((int) pointX, (int) pointY);
    }


    /**
     * 设置触摸点
     *
     * @param x
     * @param y
     * @param style
     */
    public void setTouchPoint(float x, float y, String style) {
        try {
            switch (style) {
                case STYLE_TOP_RIGHT:
                    f.x = mWidth;
                    f.y = 0;
                    break;
                case STYLE_LOWER_RIGHT:
                    f.x = mWidth;
                    f.y = mHeight;
                    break;
                default:
                    break;
            }
            //如果大于0则设置a点坐标重新计算各标识点位置，否则a点坐标不变
            Point touchPoint = new Point((int) x, (int) y);
            if (calcPointCX(touchPoint, f) > 0) {
                a.x = (int) x;
                a.y = (int) y;
                calcPointsXY(a, f);
            } else {
                calcPointsXY(a, f);
            }
            postInvalidate();
        }catch (Exception e){

        }
    }

    /**
     * 回到默认状态
     */
    public void setDefaultPath() {
        a.x = -1;
        a.y = -1;
        postInvalidate();
    }

    /**
     * 绘制默认的界面
     *
     * @return
     */
    private Path getPathDefault() {
        pathA.reset();
        pathA.lineTo(0, mHeight);
        pathA.lineTo(mWidth, mHeight);
        pathA.lineTo(mWidth, 0);
        pathA.close();
        return pathA;
    }

    /**
     * 获取f点在右上角的pathA
     *
     * @return
     */
    private Path getPathAFromTopRight() {
        pathA.reset();
        pathA.lineTo(c.x, c.y);//移动到c点
        pathA.quadTo(e.x, e.y, b.x, b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x, a.y);//移动到a点
        pathA.lineTo(k.x, k.y);//移动到k点
        pathA.quadTo(h.x, h.y, j.x, j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(mWidth, mHeight);//移动到右下角
        pathA.lineTo(0, mHeight);//移动到左下角
        pathA.close();
        return pathA;
    }


    /**
     * 计算C点的X值
     *
     * @param a
     * @param f
     * @return
     */
    private float calcPointCX(Point a, Point f) {
        Point g, e;
        g = new Point();
        e = new Point();
        g.x = (a.x + f.x) / 2;
        g.y = (a.y + f.y) / 2;

        e.x = g.x - (f.y - g.y) * (f.y - g.y) / (f.x - g.x);
        e.y = f.y;

        return e.x - (f.x - e.x) / 2;
    }

    public float getmWidth() {
        return mWidth;
    }

    public float getmHeight() {
        return mHeight;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getY() < getmHeight()/2){
                    setTouchPoint(event.getX(),event.getY(),STYLE_TOP_RIGHT);
                }else if(event.getY() >= getmHeight()/2) {
                    setTouchPoint(event.getX(),event.getY(),STYLE_LOWER_RIGHT);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                setTouchPoint(event.getX(),event.getY(),"");
                break;
            case MotionEvent.ACTION_UP:
                setDefaultPath();
                break;
        }
        return true;
    }
}
