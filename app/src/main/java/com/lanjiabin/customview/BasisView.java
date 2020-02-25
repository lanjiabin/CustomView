package com.lanjiabin.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BasisView extends View {
    //画笔
    private Paint mPaint;
    //矩形类
    RectF mRectF;
    Rect mRect;

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
        canvas.drawColor(Color.BLUE); //传入存在的颜色
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


    }
}
