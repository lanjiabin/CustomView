package com.lanjiabin.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
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
        mPaint.setColor(getResources().getColor(R.color.white)); //设置画笔颜色
        mPaint.setStrokeWidth(5); //设置画笔宽度
        mPaint.setStyle(Paint.Style.STROKE); //设置填充样式 Paint.Style.STROKE 仅描边
        canvas.drawColor(Color.RED); //设置画布颜色
        
        //1.Path重置
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        mPath.addRect(100, 100, 300, 300, Path.Direction.CCW);
        mPath.addCircle(300, 300, 100, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
    }

}
