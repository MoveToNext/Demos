package liu.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int width;
    private int height;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        image = (ImageView) findViewById(R.id.image);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        Log.e("height", height + "");
        Log.e("width", width + "");

    }

    public void rotate(final View view){
//        ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f,1.0f).setDuration(3000).start();
        //objectAnimator 多个动画集合
        /*
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "liu", 1.0f, 0.5f, 1.0f).setDuration(3000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.e("animatedValue", animatedValue + "");
                view.setAlpha(animatedValue);
                view.setScaleX(animatedValue);
                view.setScaleY(animatedValue);
            }
        });
        */

        //ValueAnimator
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, height - image.getHeight());
//        Log.e("image.getHeight()", image.getHeight() + "");
//        valueAnimator.setTarget(image);
//        valueAnimator.setDuration(3000);
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                image.setTranslationY((float)animation.getAnimatedValue());
//            }
//        });
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                ViewGroup parent = (ViewGroup) image.getParent();
//                parent.removeView(image);
//            }
//        });

//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.5f);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.5f);
//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(animator1,animator2);
//        set.setInterpolator(new DecelerateInterpolator());
//        set.setDuration(3000);
//        set.start();

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyTypeEvaluator(), new Paint(0, 0));
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Paint paint = (Paint) animation.getAnimatedValue();
                image.setX(paint.x);
                image.setY(paint.y);
            }
        });
        valueAnimator.start();
    }

}
