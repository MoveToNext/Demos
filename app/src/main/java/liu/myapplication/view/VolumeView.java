package liu.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liutongyang on 2016/9/16.
 * 音频图
 */
public class VolumeView extends View {

    private int mRectCount = 10;
    private float offet = 5f;
    private int mWidth;
    private int mRectHeight;
    private int mRectWidth;
    private double mRandom;
    private Paint mPaint;
    private LinearGradient mLinearGradient;

    public VolumeView(Context context) {
        super(context);
        initOption();
    }


    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOption();
    }

    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initOption();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initOption();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth * 0.6) / mRectCount;
        mLinearGradient = new LinearGradient(
                0,
                0,
                mRectWidth,
                mRectHeight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.MIRROR
        );
        mPaint.setShader(mLinearGradient);
    }

    private void initOption() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++){
            mRandom = Math.random();
            float currentHeight = (float) (mRectHeight * mRandom);
            canvas.drawRect((float)(mWidth*0.4/2+mRectWidth*i+offet),
                    currentHeight,
                    (float)(mWidth*0.4/2+mRectWidth*(i+1)),
                    mRectHeight,
                    mPaint);
        }
        postInvalidateDelayed(500);
    }
}
