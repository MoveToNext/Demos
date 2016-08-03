package liu.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

import liu.myapplication.R;

/**
 * Created by liutongyang on 2016/7/24
 *
 */
public class MyView extends View {

    private int mColor = Color.RED;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MyView(Context context) {
        this(context,null);
    }


    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        mColor = a.getColor(R.styleable.MyView_circle_color, Color.BLACK);
        a.recycle();
        Log.e("init", "3");
        init();
    }

    private void init() {
        paint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int hightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize= MeasureSpec.getSize(widthMeasureSpec);
        int hightSpecSize= MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && hightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,hightSpecSize);
        }else if (hightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,200);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Logger.e("MYview - dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.e("MYview - onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int width = getWidth()-paddingLeft-paddingRight;
        int height = getHeight()-paddingTop-paddingBottom;
        int min = Math.min(width, height)/2;
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,min,paint);
    }
}
