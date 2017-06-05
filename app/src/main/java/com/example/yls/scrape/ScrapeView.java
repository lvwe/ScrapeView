package com.example.yls.scrape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yls on 2017/6/5.
 */

public class ScrapeView extends View {
    private Bitmap mbpBackground;
    private Bitmap mbpForeground;
    private Canvas mCanvas;
    //    手指刮开路径的画笔
    private Paint mPathPaint;
    private Path mPath;
    private Paint mContentPaint;
    private String content = "刮刮乐";

    public ScrapeView(Context context) {
        super(context);
        init();

    }

    public ScrapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {


        mPathPaint = new Paint();
        mPathPaint.setAlpha(0);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(50);

        mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPathPaint.setStrokeJoin(Paint.Join.ROUND);
        mPathPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();

        mbpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.zz);
        mbpForeground = Bitmap.createBitmap(mbpBackground.getWidth(), mbpBackground.getHeight(), Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mbpForeground);

        mContentPaint = new Paint();
        mContentPaint.setColor(Color.WHITE);
        mContentPaint.setTextSize(100);
        mContentPaint.setStrokeWidth(20);

        mCanvas.drawColor(Color.GRAY);
        mCanvas.drawText(content, mCanvas.getWidth() / 4, mCanvas.getHeight() / 2, mContentPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());

                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;

        }
        mCanvas.drawPath(mPath, mPathPaint);
        invalidate();


        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mbpBackground, 0, 0, null);
        canvas.drawBitmap(mbpForeground, 0, 0, null);
    }
}
