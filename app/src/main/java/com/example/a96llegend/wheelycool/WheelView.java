package com.example.a96llegend.wheelycool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import java.util.ArrayList;
import java.util.List;

public class WheelView extends View {

    private static final String tag = "====Debug====";

    //circle and text colors
    private int[] colour = {Color.parseColor("#BE4A47"), Color.parseColor("#6495ED"),
            Color.parseColor("#ED7F00"), Color.parseColor("#77D9D3"), Color.parseColor("#D3FFCE")};

    //label text
    private String defaultText = "no option";
    private List<String> options = new ArrayList<String>();

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //get the attributes specified in attrs.xml , which are the pre-define background colour and option name
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.WheelView, 0, 0);
        try {
            String temp = a.getString(R.styleable.WheelView_firstOption);
            if(temp != null){
                options.add(temp);
            }
            temp = a.getString(R.styleable.WheelView_secondOption);
            if(temp != null){
                options.add(temp);
            }
            temp = a.getString(R.styleable.WheelView_thirdOption);
            if(temp != null){
                options.add(temp);
            }
            temp = a.getString(R.styleable.WheelView_forthOption);
            if(temp != null){
                options.add(temp);
            }
            temp = a.getString(R.styleable.WheelView_fifthOption);
            if(temp != null){
                options.add(temp);
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

    //For setting options in run time
    public void setOptions(List<String> optionList){
        options.clear();
        for(int i = 0; i < optionList.size(); i++){
            options.add(optionList.get(i));
        }
        invalidate();
    }

    //Force the wheel view to be a square
    @Override
    protected void onMeasure(int widthMeasure, int heightMeasure){
        super.onMeasure(widthMeasure, heightMeasure);
        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        if(width < height) {
            setMeasuredDimension(width, width);
        }else {
            setMeasuredDimension(height, height);
        }
    }

    //Draw the wheel
    @Override
    protected void onDraw(Canvas canvas) {
        //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        // Calculate radius and text size
        // get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf) {
            radius = viewHeightHalf;
        }else {
            radius = viewWidthHalf;
        }

        //paint for drawing custom view
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Style.FILL);
        circlePaint.setAntiAlias(true);

        //Calculate text size
        float textSize = radius / 7;

        //Set the bounds of oval to the whole canvas
        RectF bounds = new RectF(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());

        int segments = options.size();
        if(segments > 1){
            float gap = 360 / segments;
            for(int i = 0; i < segments; i++){
                drawSegment(circlePaint, canvas, bounds, gap*i, gap, i);
                drawText(circlePaint, canvas, textSize, ((gap/2f) + (gap*(float)i)), viewWidthHalf, viewHeightHalf, options.get(i), radius);
            }
        } else {
            circlePaint.setColor(colour[0]);
            canvas.drawCircle(viewHeightHalf, viewHeightHalf, radius, circlePaint);
            circlePaint.setColor(Color.BLACK);
            circlePaint.setTextAlign(Paint.Align.CENTER);
            circlePaint.setTextSize(textSize);
            if(segments == 1) {
                canvas.drawText(options.get(0), viewWidthHalf, viewHeightHalf - ((circlePaint.descent() + circlePaint.ascent()) / 2), circlePaint);
            } else {
                canvas.drawText("No option", viewWidthHalf, viewHeightHalf - ((circlePaint.descent() + circlePaint.ascent()) / 2), circlePaint);
            }
        }
    }

    private void drawSegment(Paint paint, Canvas canvas, RectF bound, float start, float sweep, int colourIndex){
        //Draw arc
        paint.setColor(colour[colourIndex]);
        canvas.drawArc(bound, start, sweep, true, paint);
    }

    private void drawText(Paint paint, Canvas canvas, float textSize, float angle, float viewWidthHalf, float viewHeightHalf, String text, int radius){
        //set the text color using the color specified
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);

        //Calculate location
        float x = viewWidthHalf + (0.5f * (float)radius);
        float y = viewWidthHalf - ((paint.descent() + paint.ascent()) / 2);

        //Rotate canvas and draw text
        canvas.save();
        canvas.rotate(angle, viewWidthHalf, viewHeightHalf);
        canvas.drawText(text, x, y, paint); //Use unrotate x, and y, beacuse coordinate rotate as well.
        canvas.restore();
    }
}
