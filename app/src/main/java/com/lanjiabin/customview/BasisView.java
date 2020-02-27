package com.lanjiabin.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BasisView extends View {
    //画笔
    private Paint mPaint;
    //矩形类
    private RectF mRectF;

    //路径类
    private Path mPath;

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
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的基本属性
        mPaint.setAntiAlias(false); //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.orchid)); //设置画笔颜色
        mPaint.setStrokeWidth(5); //设置画笔宽度
        mPaint.setStyle(Paint.Style.STROKE); //设置填充样式 Paint.Style.STROKE 仅描边
        canvas.drawColor(Color.WHITE); //设置画布颜色

        //添加圆角矩形
       //(rx,ry)为椭圆的横轴半径和纵轴半径
       //定义四个角都为一个圆角
       mRectF=new RectF(50,50,240,200);
       mPath.addRoundRect(mRectF,10,15, Path.Direction.CCW);

       //每个圆角都不同
       mRectF=new RectF(290,50,480,200);
       float[] radii ={10,15,20,25,30,35,40,45};
       mPath.addRoundRect(mRectF,radii, Path.Direction.CCW);
       canvas.drawPath(mPath,mPaint);

       //添加圆形
       mPath.addCircle(100,100,50, Path.Direction.CCW);

       //添加椭圆路径
       mPath.addOval(mRectF, Path.Direction.CCW);

       //添加弧线路径
       mPath.addArc(mRectF,0,90);
    }

}
