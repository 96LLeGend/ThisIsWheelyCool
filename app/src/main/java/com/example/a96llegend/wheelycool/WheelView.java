package com.example.a96llegend.wheelycool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class WheelView extends View {

    //circle and text colors
    private int[] colour = {Color.parseColor("#BE4A47"), Color.parseColor("#6495ED"),
            Color.parseColor("#ED7F00"), Color.parseColor("#77D9D3"), Color.parseColor("#D3FFCE")};

    //label text
    private String defaultText = "no option";

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //get the attributes specified in attrs.xml , which are the pre-define background colour and option name
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.WheelView, 0, 0);
        try {
            String temp = a.getString(R.styleable.WheelView_defaultText);
            if(temp != null){
                defaultText = temp;
            }
            colour[0] = a.getInteger(R.styleable.WheelView_firstColor, colour[0]);
            colour[1] = a.getInteger(R.styleable.WheelView_secondColor, colour[1]);
            colour[2] = a.getInteger(R.styleable.WheelView_thirdColor, colour[2]);
            colour[3] = a.getInteger(R.styleable.WheelView_forthColor, colour[3]);
            colour[4] = a.getInteger(R.styleable.WheelView_fifthColor, colour[4]);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        //  Calculate radius
        // get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf) {
            radius = viewHeightHalf - 10;
        }else {
            radius = viewWidthHalf - 10;
        }

        //paint for drawing custom view
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Style.FILL);
        circlePaint.setAntiAlias(true);

        //set the text color using the color specified
        circlePaint.setColor(Color.BLACK);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);
        canvas.drawText(defaultText, viewWidthHalf, viewHeightHalf, circlePaint);

        //Set the bounds of oval to the whole canvas
        RectF bounds = new RectF(0, 0, viewWidthHalf * 2, viewHeightHalf * 2);

        //First section
        circlePaint.setColor(colour[0]);
        canvas.drawArc(bounds, 0f, 72f, true, circlePaint);

        //Second section
        circlePaint.setColor(colour[1]);
        canvas.drawArc(bounds, 72f, 72f, true, circlePaint);

        //Third section
        circlePaint.setColor(colour[2]);
        canvas.drawArc(bounds, 144f, 72f, true, circlePaint);

        //Forth section
        circlePaint.setColor(colour[3]);
        canvas.drawArc(bounds, 216f, 72f, true, circlePaint);

        //Fifth section
        circlePaint.setColor(colour[4]);
        canvas.drawArc(bounds, 288f, 72f, true, circlePaint);
    }

}
