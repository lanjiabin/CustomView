package com.lanjiabin.customview;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DivText extends View {
    private Paint mPaint;
    private Path mPath;

    public DivText(Context context) {
        super(context);
    }

    public DivText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DivText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(5); //画笔宽度
        mPaint.setAntiAlias(true); //是否使用抗锯齿，如果使用，绘图速度会变慢
        mPaint.setTextSize(50); //设置文字大小

        mPaint.setTextAlign(Paint.Align.LEFT); //文字对齐方式
        mPaint.setStyle(Paint.Style.STROKE); //绘图样式，对几何图形和文字都有效
        canvas.drawText("开发入门与实践", 400, 100, mPaint); //绘制文字

        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("开发入门与实践", 400, 250, mPaint);

        mPaint.setTextAlign(Paint.Align.RIGHT);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("开发入门与实践", 400, 400, mPaint);

        mPaint.setFakeBoldText(true); //是否为黑体
        mPaint.setUnderlineText(true); //是否带下划线
        mPaint.setStrikeThruText(true); //是否带有删除线
        mPaint.setTextAlign(Paint.Align.RIGHT);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("开发入门与实践", 400, 600, mPaint);

        //1.正常模式
        mPaint.setFakeBoldText(false); //是否为黑体
        mPaint.setUnderlineText(false); //是否带下划线
        mPaint.setStrikeThruText(false); //是否带有删除点
        mPaint.setTextAlign(Paint.Align.LEFT); //文字对齐方式
        mPaint.setStyle(Paint.Style.FILL); //绘图样式，对几何图形和文字都有效
        canvas.drawText("1.开发入门与实践", 400, 700, mPaint); //绘制文字

        //2.向右倾斜
        mPaint.setTextSkewX(-0.25f);
        canvas.drawText("2.开发入门与实践", 400, 800, mPaint); //绘制文字

        //3.向左倾斜
        mPaint.setTextSkewX(0.25f);
        canvas.drawText("3.开发入门与实践", 400, 900, mPaint); //绘制文字

        //4.水平拉伸
        mPaint.setTextScaleX(2); //只会向水平方向拉伸，高度不变
        canvas.drawText("4.开发入门与实践", 400, 1000, mPaint); //绘制文字

        //1.先创建两条相同的圆形路径，并画出两条路径原形
        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(50);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addCircle(300, 1300, 150, Path.Direction.CCW); //逆向绘制
        canvas.drawPath(mPath, mPaint);

        Path mPath2 = new Path();
        mPath2.addCircle(700, 1300, 150, Path.Direction.CCW); //逆向绘制
        canvas.drawPath(mPath2, mPaint);

        //2.绘制原始文字和偏移量
        String string = "Android开发与入门";
        mPaint.setColor(Color.RED);

        //3.将hOffset、vOffset全部设置为0，查看原始状态
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath(string, mPath, 0, 0, mPaint);

        //4.改变hOffset、vOffset的值
        canvas.drawTextOnPath(string, mPath2, 80, 30, mPaint);

        //系统自带字体
        mPaint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(string, 300, 1600, mPaint);

        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(string, 300, 1700, mPaint);

        mPaint.setTypeface(Typeface.SANS_SERIF);
        canvas.drawText(string, 300, 1800, mPaint);

        mPaint.setTypeface(Typeface.SERIF);
        canvas.drawText(string, 300, 1900, mPaint);

        mPaint.setTypeface(Typeface.MONOSPACE);
        canvas.drawText(string, 300, 2000, mPaint);

        string = "Android手机字体和自定义字体";
        //1.设置为系统字体的粗体，如当前手机系统为宋体，那最后为 宋体的粗体
        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawText(string, 300, 2100, mPaint);

        //2.设置为存在系统里的指定字体，如果不存在，那就会使用手机的默认字体
        mPaint.setTypeface(Typeface.create("宋体", Typeface.NORMAL));
        canvas.drawText(string, 300, 2200, mPaint);

        //3.设置自定义字体，在main先创建assets,然后创建fonts，把网上下载的ttf格式的字体放在里面
        AssetManager mgr = getResources().getAssets();
        Typeface typeface = Typeface.createFromAsset(mgr, "fonts/f002.ttf");
        mPaint.setTypeface(typeface);
        canvas.drawText(string, 300, 2300, mPaint);


    }
}
