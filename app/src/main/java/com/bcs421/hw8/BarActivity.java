package com.bcs421.hw8;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BarActivity extends View {

    //hash map
    Map<String, Float> data = new HashMap<>();
    private int mNumCircles = 5;
    private Paint mPaint;
    private Random mRandom;
    private float barWidth =60f;
    private float barSpace =20f;
    Intent i = Intent.getIntent("Grade");
    String[] myGrades = i.getStringArrayExtra("Grade");
    Intent intent = Intent.getIntent("Values");
    int[] myValues = intent.getIntArrayExtra("Values");
    public BarActivity(Context context, AttributeSet attrs) throws URISyntaxException {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRandom = new Random();

     //   data.put("A", 0.20f);
     //   data.put("A-", 0.12f);
    //    data.put("F", 0.1f);

         for (int i = 0; i <=myGrades.length; i++)
        {
             data.put(myGrades[i],myValues[i] + 0.0f);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /// makes the x and y lines
        float sw = canvas.getWidth();
        float sh = canvas.getHeight();
        canvas.drawARGB(255, 255, 255, 0);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(20, sh * 7 / 8, sw - 20, sh * 7 / 8, mPaint);
        canvas.drawLine(sw * 7 / 8, 0, sw * 7 / 8, sh - 20, mPaint);
        for (int i = 0; i <=myGrades.length; i++) {
            mPaint.setColor(Color.RED);

            canvas.drawRect(sw / 8, sh * (7 / 8f - (float) data.get(myGrades[i])),sw / 8 + barWidth,
                    //display how hight the bar will go
                    7 * sh / 8, mPaint);;

        }

        canvas.drawRect(sw / 8, sh * (7 / 8f - (float) data.get("A")), sw / 8 + barWidth,
                //display how hight the bar will go
                7 * sh / 8, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(sw / 8 + 1 * (barSpace + barWidth), sh * (7 / 8f - (float) data.get("A")),
                sw / 8 + barWidth + (barSpace + barWidth), 7 * sh / 8, mPaint);
        mPaint.setTextSize(50);
//        canvas.drawRect("A", sw / 8 + 15, 7 * sh / 8 + 80, mPaint);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(sw / 8 + 2 * (barSpace + barWidth), sh * (7 / 8f - (float) data.get("F")),
                sw / 8 + barWidth + 2 * (barSpace + barWidth),
                7 * sh / 8, mPaint);
/*
        for (int c = 0; c < mNumCircles; c++) {
            int randomColor = Color.argb(150, mRandom.nextInt(256),
                    mRandom.nextInt(256), mRandom.nextInt(256));
            mPaint.setColor(randomColor);
            float centerX = mRandom.nextInt(getWidth());
            float centerY = mRandom.nextInt(getHeight());
            float radius = mRandom.nextInt(200) + 100;
            canvas.drawCircle(centerX, centerY, radius, mPaint);
        }*/

        //    canvas.drawText("A",150,1750,mPaint);
        //    canvas.drawText("B",230,1750,mPaint);
        //    canvas.drawText("C",320,1750,mPaint);
        //    canvas.drawText("D",410,1750,mPaint);
        //    canvas.drawText("F",500,1750,mPaint);

    }

}