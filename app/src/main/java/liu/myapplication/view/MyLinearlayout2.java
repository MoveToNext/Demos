package liu.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @PackageName: liu.myapplication.view
 * @Description:
 * @author:
 * @date: 2016/8/3 17:00
 *
 * 如果全部没有返回true ，由activity处理，之后不分发了
 * 子view down事件不处理，move也不分发了，上级view处理，就分到上级
 * down处理，move不处理，以后都会给他，然后回传到activity，不传递上级，因为有处理的标志view（）没有处理的标志view会调用
 * dispatchTransformedTouchEvent，然后会逐层调用上次方法
 * 不是down事件，标志view也是空的话，就直接拦截了（viewgroup）
 * 一般情况，都有底层view处理down事件，所以默认流程是从上到下传递，然后move可能没人处理，统一返回
 */
public class MyLinearlayout2 extends LinearLayout {
    public MyLinearlayout2(Context context) {
        super(context);
    }

    public MyLinearlayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearlayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyLinearlayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MyLinearlayout", "MyLinearlayout2-dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean handle = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
//                handle = true;
                break;
            case MotionEvent.ACTION_MOVE:
//                handle = true;
                break;
        }
        Log.d("MyLinearlayout", "MyLinearlayout2-onInterceptTouchEvent");
        return handle||super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handle = false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("MyLinearlayout", "MyLinearlayout2 - onTouchEvent-ACTION_DOWN");
//                handle = true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MyLinearlayout", "MyLinearlayout2 - onTouchEvent-ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyLinearlayout", "MyLinearlayout2 - onTouchEvent-ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("MyLinearlayout", "MyLinearlayout2 - onTouchEvent-ACTION_CANCEL");
                break;
        }
        return handle || super.onTouchEvent(event);
    }
}
