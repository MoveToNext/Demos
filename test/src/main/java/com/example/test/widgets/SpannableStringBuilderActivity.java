package com.example.test.widgets;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.example.test.R;

/**
 * <pre>
 * PackageName:  com.example.test.widgets
 * Description:
 * Created by :   Liu
 * date:         2017/11/6 上午9:45
 * </pre>
 */
public class SpannableStringBuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        final TextView textView = (TextView) findViewById(R.id.text);
        findViewById(R.id.btn_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("这是一段测试文字");
                BackgroundColorSpan span = new BackgroundColorSpan(Color.RED);
                spannableStringBuilder.setSpan(span,1,3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                textView.setText(spannableStringBuilder);
            }
        });
    }
}
