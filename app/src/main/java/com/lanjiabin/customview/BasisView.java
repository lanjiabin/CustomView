package com.lanjiabin.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class BasisView extends View {
    //画笔
    private Paint mPaint;
    //矩形类
    private RectF mRectF;
    private Rect mRect;

    //点击的坐标
    private int mX, mY;


    public BasisView(Context context) {
        super(context);
    }

    /**
     * 默认的构造方法
     */
    public BasisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化
        init();
    }

    public BasisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    public void init() {
        mPaint = new Paint();
        mRectF = new RectF();
        mRect = new Rect(100, 1411, 200, 1499);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔的基本属性
        mPaint.setAntiAlias(false); //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.orchid)); //设置画笔颜色
        mPaint.setStrokeWidth(30); //设置画笔宽度

        mPaint.setStyle(Paint.Style.STROKE); //设置填充样式 Paint.Style.STROKE 仅描边
        canvas.drawCircle(190, 200, 150, mPaint);

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE); //设置填充样式 Paint.Style.STROKE 填充内部和描边
        canvas.drawCircle(510, 200, 150, mPaint);

        mPaint.setStyle(Paint.Style.FILL); //设置填充样式 Paint.Style.STROKE 仅填充内部，就是没有了圆边
        canvas.drawCircle(830, 200, 150, mPaint);

        /*
          设置画布颜色
          */
        canvas.drawColor(Color.WHITE); //传入存在的颜色
//        canvas.drawARGB(255,0,255,255); //传入ARGB颜色分量
//        canvas.drawRGB(255,0,255); //传入RGB分量

        /*
          @startX 起始点X的坐标
          @startY 起始点Y的坐标
          @stopX 终点X的坐标
          @stopY 终点Y的坐标
          起点：（840,300） 终点：（840,600）
         */
        canvas.drawLine(840, 300, 840, 600, mPaint);

        /*
          绘制多条直线
          数组中两两成为一个坐标
          起点：（10，10） 终点：（100,100）
          起点：（200,200）终点：（400,400）
          */
        float[] pts = {10, 10, 100, 100, 200, 200, 400, 400};
        canvas.drawLines(pts, mPaint);

        float[] past1 = {10, 100, 100, 200, 200, 300, 400, 500};
        canvas.drawLines(past1, mPaint);

        float[] past2 = {10, 200, 100, 300, 200, 400, 500, 600};
        /*
          @offset 集合中跳过的坐标个数，这里的坐标是指一个点（66,88）
          @count 表示有多少个数值参与绘制，比如（66,88）（77,99）那就是四个数值
          下面的offset=2，所以（10,200）（100，300）被跳过
          下面的count=4，即（200,400）（500,600）这四个数值参与绘制
          所以起点坐标是：（200,400） 终点坐标是：（500,600）
          */
        canvas.drawLines(past2, 2, 4, mPaint);

        /*
          线和点的大小与mPaint.setStrokeWidth(30);有关
          与mPaint.setStyle无关
          */
        canvas.drawPoint(300, 700, mPaint);

        /*
          与上面一致，多个点和跳过的点
          */
        float[] points = {300, 750, 250, 725, 260, 780, 210, 900};
        canvas.drawPoints(points, mPaint);

        float[] points2 = {300, 850, 250, 825, 260, 880, 210, 990};
        canvas.drawPoints(points2, 2, 4, mPaint);

        /*
           (cx,cy)圆心坐标
           radius 圆的半径
         */
        canvas.drawCircle(450, 1100, 150, mPaint);

        /*
          以对角线的方式来绘制矩形
         （left,top) （right,bottom）形成一个对角线
          根据对角线来画出矩形
          1.直接设置参数
         */
        canvas.drawRect(100, 1300, 300, 1400, mPaint);

        //2.使用构造函数来设定矩形参数，直接在初始化时赋值
        canvas.drawRect(mRect, mPaint);

        //3.得到对象后赋值来完成矩形参数
        mRectF.left = 100f;
        mRectF.top = 1566f;
        mRectF.right = 200f;
        mRectF.bottom = 1599f;
        canvas.drawRect(mRectF, mPaint);

        mRectF.left = 100f;
        mRectF.top = 1666f;
        mRectF.right = 200f;
        mRectF.bottom = 1899f;
        // (rx,ry)是生成椭圆X半径和Y轴半径
        canvas.drawRoundRect(mRectF, 20, 30, mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(300, 1666, 400, 1899, 50, 60, mPaint);
        }

        mRectF.left = 100f;
        mRectF.top = 1920f;
        mRectF.right = 400f;
        mRectF.bottom = 2400f;
        //画椭圆
        canvas.drawOval(mRectF, mPaint);

        /*
         * 画弧线
         * startAngle 是指用X水平轴在右边顺时针转过的角度，也就是弧线开始的角度
         * sweepAngle 是指转过多少角度，也就是这段弧线的角度
         *
         * userCenter true/false true的时候
         * 指的是加上弧的边，也就是形成了扇形，
         * false的时候，不要两边，只有弧线
         *
         * 弧线是根据矩形生成的，（left,top）(right,bottom)确定一条直线
         * 弧的圆心就是在这条直线的中点，也就是垂直平分线。
         * */
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mRectF.left = 100f;
        mRectF.top = 2500f;
        mRectF.right = 400;
        mRectF.bottom = 2700;
        //加上弧边
        canvas.drawArc(mRectF, 0, 80, true, mPaint);
        canvas.drawArc(mRectF, 90, 80, true, mPaint);
        canvas.drawArc(mRectF, 180, 80, true, mPaint);
        canvas.drawArc(mRectF, 270, 80, true, mPaint);
        canvas.drawArc(mRectF, 360, 80, true, mPaint);

        //填充内部
        mPaint.setStyle(Paint.Style.FILL);
        mRectF.top = 2900f;
        mRectF.bottom = 3100f;
        canvas.drawArc(mRectF, 0, 90, true, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mRectF.left = 600f;
        mRectF.right = 900f;
        //不要弧边
        canvas.drawArc(mRectF, 0, 80, false, mPaint);
        canvas.drawArc(mRectF, 90, 80, false, mPaint);
        canvas.drawArc(mRectF, 180, 80, false, mPaint);
        canvas.drawArc(mRectF, 270, 80, false, mPaint);
        canvas.drawArc(mRectF, 360, 80, false, mPaint);

        /*
         * 验证弧心是不是（left,top）(right,bottom)的中点
         * 两坐标中点求法：
         * x=(x1+x2)/2
         * y=(y1+y2)/2
         * 验证手法：
         * (200,3200) (400,3400)的中点就是（300,3300）
         * 我们在中点（300,3300）画一个点，看看是否在弧的弧心
         * */
        //画弧线
        mRectF.left = 200f;
        mRectF.top = 3200;
        mRectF.right = 400f;
        mRectF.bottom = 3400;
        canvas.drawArc(mRectF, 0, 90, true, mPaint);

        //画点，确实是在这条直线的中点上
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(300, 3300, mPaint);

        mRectF.left = 200f;
        mRectF.top = 3500;
        mRectF.right = 400f;
        mRectF.bottom = 3600;
        if (mRectF.contains(mX, mY)) {
            mPaint.setColor(Color.RED);
        } else {
            mPaint.setColor(Color.GREEN);
        }
        canvas.drawRect(mRectF, mPaint);

    }

    /*
     * invalidate方法和postInvalidate方法都是用于进行View的刷新，
     * invalidate方法应用在UI线程中，
     * 而postInvalidate方法应用在非UI线程中，用于将线程切换到UI线程，
     * postInvalidate方法最后调用的也是invalidate方法。
     * */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = (int) event.getX();
        mY = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate(); // 必须在主线程中执行。重绘view
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mX = -1;
        }
        postInvalidate(); // 可以在任意线程执行。重绘view
        return super.onTouchEvent(event);
    }
}
