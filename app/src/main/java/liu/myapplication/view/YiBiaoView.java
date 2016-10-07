package liu.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @PackageName: liu.myapplication.view
 * @Description: 仪表盘view
 * @author: mifm
 * @date: 2016/10/7 9:15
 */
public class YiBiaoView extends View {

    private Paint mCirclePaint;
    private int width;
    private int height;
    private Paint mDegreePaint;
    private Paint mHourPaint;
    private Paint mMinutePaint;

    public YiBiaoView(Context context) {
        super(context);
        initOption();
    }

    public YiBiaoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOption();
    }

    public YiBiaoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initOption();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public YiBiaoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initOption();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    /**
     * 初始化画笔等选项
     */
    private void initOption() {
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);//空心
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(5);//当style为stroke或者是fill_or_stroke笔刷粗细度

        //仪表文字 线段
        mDegreePaint = new Paint();
        mDegreePaint.setStrokeWidth(3);

        mHourPaint = new Paint();
        mHourPaint.setStrokeWidth(20);
        mMinutePaint = new Paint();
        mMinutePaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(width / 2, height/2,width/2,mCirclePaint);
        for (int i = 0; i < 24; i++) {
            String degree = String.valueOf(i);
            //区分整点与非整点
            if(i==0 || i == 6 || i == 12 || i == 18){
                mDegreePaint.setStrokeWidth(5);
                mDegreePaint.setTextSize(30);
                canvas.drawLine(width/2,height/2 -width / 2,
                        width/2,height/2 -width / 2+60,mDegreePaint);
                canvas.drawText(degree,width/2-mDegreePaint.measureText(degree)/2,
                        height/2 -width / 2+90,mDegreePaint);
            }else {
                mDegreePaint.setStrokeWidth(3);
                mDegreePaint.setTextSize(15);
                canvas.drawLine(width/2,height/2 -width / 2,
                        width/2,height/2 -width / 2+30,mDegreePaint);
                canvas.drawText(degree,width/2-mDegreePaint.measureText(degree)/2,
                        height/2 -width / 2+60,mDegreePaint);
            }
            //旋转画布
            canvas.rotate(15,width/2,height/2);
        }
        canvas.save();
        canvas.translate(width/2,height/2);
        canvas.drawLine(0,0,100,200,mHourPaint);
        canvas.drawLine(0,0,100,300,mMinutePaint);
        canvas.restore();
    }
}
