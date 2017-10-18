package com.example.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * <pre>
 * PackageName:  com.example.test
 * Description:
 * Created by :   Liu
 * date:         2017/9/26 下午1:15
 * </pre>
 */
public class CustomTextView extends TextView {

    public static final String TAG = "CustomTextView";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.CustomizeStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.CustomizeStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Customize, defStyleAttr, defStyleRes);

        String one = a.getString(R.styleable.Customize_attr_one);
        String two = a.getString(R.styleable.Customize_attr_two);
        String three = a.getString(R.styleable.Customize_attr_three);
        String four = a.getString(R.styleable.Customize_attr_four);
        Log.i(TAG, "one:" + one);
        Log.i(TAG, "two:" + two);
        Log.i(TAG, "three:" + three);
        Log.i(TAG, "four:" + four);
        a.recycle();
    }
}
