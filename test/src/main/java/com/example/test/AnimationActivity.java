package com.example.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

/**
 * <pre>
 * PackageName:  com.example.test
 * Description:
 * Created by :   Liu
 * date:         2017/10/13 下午3:01
 * </pre>
 */
public class AnimationActivity extends AppCompatActivity {

    private View btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        btn = findViewById(R.id.btn_anim);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation();
            }
        });
    }

    private void animation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tran);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 100, Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
        translateAnimation.setDuration(300);
        translateAnimation.setFillAfter(true);
        btn.startAnimation(animation);
    }
}
