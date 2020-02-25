package com.lanjiabin.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BasisView extends View {
    /**
     * 声明画笔
     */
    private Paint mPaint;

    public BasisView(Context context) {
        super(context);
    }

    /**
     * 默认的构造方法
     * */
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
     * */
    public void init(){
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔的基本属性
        mPaint.setAntiAlias(false); //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.orchid)); //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置填充样式
        mPaint.setStrokeWidth(10); //设置画笔宽度

        //画圆 cx 圆心x坐标 cy 圆心y坐标 radius 圆半径
        canvas.drawCircle(190,200,150,mPaint);

        mPaint.setAntiAlias(true); //抗锯齿
        canvas.drawCircle(510,200,150,mPaint);
    }
}
