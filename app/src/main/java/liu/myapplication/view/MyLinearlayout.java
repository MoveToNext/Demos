package liu.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

/**
 * @PackageName: liu.myapplication.view
 * @Description:
 * @author:
 * @date: 2016/8/3 17:00
 */
public class MyLinearlayout extends LinearLayout {
    public MyLinearlayout(Context context) {
        super(context);
    }

    public MyLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.d("MyLinearlayout-dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Logger.d("MyLinearlayout-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Logger.d("MyLinearlayout-ACTION_MOVE");
                return false;
        }
        Logger.d("MyLinearlayout-onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.d("MyLinearlayout-onTouchEvent");
        return super.onTouchEvent(event);
    }
}
