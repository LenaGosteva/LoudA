package com.example.loudalarm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class ClockView extends View {

//    private final int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private int height, width = 0;
    private int padding = 0;
    private int handTruncation, hourHandTruncation = 0;
    private int radius = 0;
    private Paint paint;
    private boolean isInit;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initClock() {
        height = getHeight();
        width = getWidth();
        int numeralSpacing = 0;
        padding = numeralSpacing + 50;
        int min = Math.min(height, width);
        radius = min / 2 - padding;
        handTruncation = min / 20;
        hourHandTruncation = min / 7;
        paint = new Paint();
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }

        canvas.drawColor(getResources().getColor(R.color.null_color));
        drawCircle(canvas);
        drawCenter(canvas);
//        drawNumeral(canvas);
        drawHands(canvas);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawHandMinute(@NonNull Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / 2f, height / 2f,
                (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius),
                paint);
    }

    private void drawHandSeconds(@NonNull Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;

        canvas.drawCircle((float) (width / 2 + Math.cos(angle) * handRadius / 2),
                (float) (height / 2 + Math.sin(angle) * handRadius / 2),
                10f, paint);
    }

    private void drawHands(Canvas canvas) {
        Calendar c = Calendar.getInstance();
        float hour = c.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHandMinute(canvas, (hour + c.get(Calendar.MINUTE) / 60f) * 5f, true);
        drawHandMinute(canvas, c.get(Calendar.MINUTE), false);
        drawHandSeconds(canvas, c.get(Calendar.SECOND), false);
    }

//    private void drawNumeral(Canvas canvas) {
//        paint.setTextSize(fontSize);
//
//        for (int number : numbers) {
//            String tmp = String.valueOf(number);
//            paint.getTextBounds(tmp, 0, tmp.length(), rect);
//            double angle = Math.PI / 6 * (number - 3);
//            int x = (int) (width / 2 + Math.cos(angle) * radius - rect.width() / 2);
//            int y = (int) (height / 2 + Math.sin(angle) * radius + rect.height() / 2);
//            canvas.drawText(tmp, x, y, paint);
//        }
//    }

    private void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2f, height / 2f, 12, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2f, height / 2f, radius + padding - 10, paint);
    }

}