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

        //1.文字
        String text="砺器悟道,砺器悟道,砺器悟道,砺器悟道";
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(45);

        //把文字逆时针放在路劲上
        mRectF=new RectF(150,250,440,600);
        mPath.addRect(mRectF, Path.Direction.CCW);
        canvas.drawPath(mPath,mPaint);
        canvas.drawTextOnPath(text,mPath,0,16,mPaint);

        //顺时针
        mPath=new Path();
        mRectF=new RectF(650,250,900,600);
        mPath.addRect(mRectF, Path.Direction.CW);
        canvas.drawPath(mPath,mPaint);
        canvas.drawTextOnPath(text,mPath,0,16,mPaint);






    }

}
