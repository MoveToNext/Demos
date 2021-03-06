package liu.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by liutongyang on 2016/9/15.
 */
public class CircleView extends View {
    private WindowManager windowManager;
    private int width;
    private int mCircleXY;
    private float mRadius;
    private RectF mArcRectF;
    private Paint mPaint;
    private Paint mArcPaint;
    private Paint textPaint;

    private String showText = "50";
    private float part = 180;
    public CircleView(Context context) {
        super(context);
        initOption();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOption();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initOption();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initOption();
    }

    /**
     * 初始化参数
     */
    private void initOption() {
        WindowManager windowManager =  (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        mCircleXY = width/2;
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(
                android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (width * 0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(120);
        textPaint.setTextAlign(Paint.Align.CENTER);
        mRadius = (float)(width*0.5/2);
        mArcRectF = new RectF((float)(width*0.1),(float)(width*0.1),
                (float)(width*0.9),(float)(width*0.9));
    }

    public float getPart() {
        return part;
    }

    public void setPart(float part) {
        this.part = (part / 100f) * 360f;
        this.showText = part + "";
        invalidate();
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mPaint);
        canvas.drawArc(mArcRectF,270,part,false,mArcPaint);
        canvas.drawText(showText,mCircleXY,mCircleXY+30,textPaint);
    }
}
