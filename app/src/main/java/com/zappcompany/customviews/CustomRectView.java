package com.zappcompany.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomRectView extends View {

    private Rect mRect;
    private Paint mPaint;
    public static final int RECT_SIZE = 100;
    public static final int RECT_TOP_LEFT = 20;
    private int mSquareColor;
    private int mSquareSize;
    private Paint mPaintCircle;

    public CustomRectView(Context context) {
        super(context);
        init(null);
    }

    public CustomRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    private void init(@Nullable AttributeSet set){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.GREEN);

        if(set ==null)
            return;
        //Refer to attrs.xml
        TypedArray ta = getContext().obtainStyledAttributes(set,R.styleable.CustomRectViewView);
        mSquareColor = ta.getColor(R.styleable.CustomRectViewView_square_color,Color.BLUE);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.CustomRectViewView_square_size,RECT_SIZE);
        mPaint.setColor(mSquareColor);
        ta.recycle();

    }
                                                        //else        //else
    public void changeColor(){
        mPaint.setColor(mPaint.getColor() == mSquareColor ? Color.GREEN : mSquareColor);
        //Recalls onDraw method else onDraw is called only when the app starts
        postInvalidate();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Draw rectangle
        mRect.left = RECT_TOP_LEFT;
        mRect.top = RECT_TOP_LEFT;
        mRect.bottom = mRect.top + mSquareSize;
        mRect.right = mRect.left + mSquareSize;
        canvas.drawRect(mRect,mPaint);

        //Draw circle
        float cx,cy;
        float radius = 100f;
        cx = getWidth() - radius -50f;//50f padding;
        cy = mRect.top + (mRect.height()/2);
        canvas.drawCircle(cx,cy,radius,mPaintCircle);
    }
}
