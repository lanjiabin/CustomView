package com.lanjiabin.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SpiderView extends View {

    private Paint mRadarPaint, mValuePaint; //网格画笔，数据画笔
    private float mRadius; //网格最大半径
    private int mCenterX; //中心X
    private int mCenterY; //中心Y
    private int mCount; //有多少个数据量
    private double mAngle; //角度
    private Path mNetworkPath; //网的路径对象
    private Path mLinePath; //网中的线的路径对象
    private Path mValuePath; //网中的线的路径对象
    private double[] mData; //数据
    private double mMaxVale; //最大值

    public SpiderView(Context context) {
        super(context);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 2.得到中心点位置
    //view的大小改变时调用这个函数，根据View的长和宽，得到整个布局的中心坐标
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        /*
         * 得到h,w的最小的那个值;
         * >> 1 移位 跟 /2 相同;
         * 乘以0.9f,表示占布局的90%
         * */
        mRadius = (Math.min(h, w) >> 1) * 0.9f;

        // 中心坐标
        mCenterX = w / 2;
        mCenterY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    // 1.初始化画笔
    public void init() {
        // 绘制网格
        mRadarPaint = new Paint();
        mRadarPaint.setStrokeWidth(10);
        mRadarPaint.setStyle(Paint.Style.STROKE);
        mRadarPaint.setColor(Color.GREEN);

        // 绘制结果
        mValuePaint = new Paint();
        mValuePaint.setStyle(Paint.Style.FILL);
        mValuePaint.setColor(Color.BLUE);

        mNetworkPath = new Path(); //网的路径对象
        mLinePath = new Path(); //网中的线的路径对象
        mValuePath = new Path(); //网格中数据的路径对象

        mCount = 6; // 设置多少个数据量
        double PI = Math.PI; // 数学角度中的π
        mAngle = 2 * PI / mCount; // 一个圆，分为多少份，每份的角度
        mData = new double[]{55, 28.7, 30.6, 70, 88,99};
        mMaxVale = 100;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
    }

    //3.画出网格 一圈一圈的
    public void drawPolygon(Canvas canvas) {

        //每一圈的间隔
        float r = mRadius / mCount;

        for (int i = 1; i <= mCount; i++) { // i = 0 的时候是中心点，不需要绘制
            float curR = r * i; //当前所在圈的半径，越往外越大
            mNetworkPath.reset();
            for (int j = 0; j < mCount; j++) {
                if (j == 0) mNetworkPath.moveTo(mCenterX + curR, mCenterY); // 每个圈的起点
                else {
                    // 一个直角三角形，知道斜边，知道斜角，可以得到直角的两个边
                    // 直角的两个边就是下一个点的坐标
                    float x = (float) (mCenterX + curR * Math.cos(mAngle * j));
                    float y = (float) (mCenterY + curR * Math.sin(mAngle * j));

                    //一个个坐标连接起来
                    mNetworkPath.lineTo(x, y);
                }
            }

            //形成闭环
            mNetworkPath.close();
            canvas.drawPath(mNetworkPath, mRadarPaint);
        }
    }

    // 4.画出网格中心线
    public void drawLines(Canvas canvas) {
        for (int i = 0; i < mCount; i++) {
            mLinePath.reset();
            mLinePath.moveTo(mCenterX, mCenterY);
            float x = (float) (mCenterX + mRadius * Math.cos(mAngle * i));
            float y = (float) (mCenterY + mRadius * Math.sin(mAngle * i));
            mLinePath.lineTo(x, y);
            canvas.drawPath(mLinePath, mRadarPaint);
        }
    }

    // 5.绘制数据
    public void drawRegion(Canvas canvas) {
        //透明颜色
        mValuePaint.setAlpha(127);
        for (int i=0;i<mCount;i++){
            double percent=mData[i]/mMaxVale; //得到数据占最大值的百分比
            float x=(float)(mCenterX+mRadius*Math.cos(mAngle*i)*percent); //把百分比应用到坐标上
            float y=(float)(mCenterY+mRadius*Math.sin(mAngle*i)*percent);
            if (i==0){
                mValuePath.moveTo(x,mCenterY);
            }else {
                mValuePath.lineTo(x,y);
            }
            canvas.drawCircle(x,y,10,mValuePaint); //每个点画成一个小圆
        }
        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE); //设置填充效果
        canvas.drawPath(mValuePath,mValuePaint);
    }
}
