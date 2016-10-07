package liu.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import liu.myapplication.R;


/**
 * @PackageName: liu.myapplication.view
 * @Description: 刮刮卡
 * @author: mifm
 * @date: 2016/10/7 10:52
 */
public class XferkmodeView extends View{

    private Paint mPaint;
    private Path mPath;
    private Bitmap mBGBitmap;
    private Bitmap mFGBitmap;
    private Canvas mCanvas;

    public XferkmodeView(Context context) {
        super(context);
        initOption();
    }


    public XferkmodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOption();
    }

    public XferkmodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initOption();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public XferkmodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initOption();
    }
    private void initOption() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
        mBGBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test2);
        mFGBitmap = Bitmap.createBitmap(mBGBitmap.getWidth(), mBGBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mFGBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBGBitmap,0,0,null);
        canvas.drawBitmap(mFGBitmap,0,0,null);
    }
}
