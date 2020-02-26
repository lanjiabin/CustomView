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

        // 绘制直线路径
        mPath.moveTo(150, 100); // 设置起始点
        mPath.lineTo(150, 199); // 第一条直线的终点，也是第二条直线的起点
        mPath.lineTo(440, 199); // 画第二条直线
        mPath.close(); //形成闭环
        canvas.drawPath(mPath, mPaint); //绘制路径

        // 绘制弧线路径
        mPath.moveTo(150, 230); // 起点
        mRectF = new RectF(100, 250, 200, 299); //生成椭圆的矩形
        mPath.arcTo(mRectF, 0, 90, true); // 0是起始角度，90是弧经过的角度
        canvas.drawPath(mPath, mPaint);

    }

}
