package com.example.test;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    private ListView listivew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        btn = findViewById(R.id.btn_anim);
        listivew = (ListView) findViewById(R.id.list);
        listivew.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.activity_list_item,android.R.id.text1,new String[]{"1","3","4","1","3","4","1","3","4"
                ,"1","3","4"
                ,"1","3","4"
                ,"1","3","4"
                ,"1","3","4"
                ,"1","3","4"}));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation();
            }
        });
    }

    private void animation() {
        XmlResourceParser animation = getResources().getAnimation(R.anim.slide_in_bottom);
        AttributeSet attributeSet = Xml.asAttributeSet(animation);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tran);
////        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE, 0,
////                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
//        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0f,1.0f);
//        AnimationSet animationSet = new AnimationSet(true);
//
//        scaleAnimation.setDuration(300);
//        scaleAnimation.setRepeatCount(1);
//        scaleAnimation.setRepeatMode(Animation.REVERSE);
//        scaleAnimation.setFillAfter(true);
//        animationSet.addAnimation(scaleAnimation);
//        animationSet.addAnimation(alphaAnimation);
//        animationSet.setRepeatCount(5);
//        btn.startAnimation(animationSet);

//        ViewPropertyAnimator viewPropertyAnimator = btn.animate().scaleX(0.5f);
//        ViewPropertyAnimator rotation = btn.animate().rotation(180f);

        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("x", 50f);
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("y", 50f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(btn, propertyValuesHolder, propertyValuesHolder1);
        objectAnimator.setDuration(1000).start();
    }
}
