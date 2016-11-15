package liu.myapplication.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import liu.myapplication.BaseActivity;
import liu.myapplication.MyTypeEvaluator;
import liu.myapplication.Point;
import liu.myapplication.R;
import liu.myapplication.view.MyView;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: 属性动画
 * @author: MIFM
 * @date: 2016/7/26 11:39
 */
public class AnimationActivity extends BaseActivity {
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    private MyView image;
    private Context ctx = this;

    private boolean mFlag = false;
    private ImageView[] views;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    public void initView() {
        initMenu();
        image = (MyView) findViewById(R.id.image);
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    Logger.e("被触摸了");
                    return true;
                } else {
                    return false;
                }

            }
        });
    }

    /**
     * 灵动菜单
     */
    private void initMenu() {
        int[] res = new int[]{R.mipmap.seat,R.mipmap.night,R.mipmap.music,
        R.mipmap.camera,R.mipmap.menu};

        views = new ImageView[5];
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(80
                , 80);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        for (int i = 0; i < 5; i++) {
            views[i] = new ImageView(this);
            views[i].setBackgroundResource(res[i]);
            views[i].setLayoutParams(params);
            if (i < 4){
                views[i].setVisibility(View.GONE);
            }
            rlContent.addView(views[i]);
        }

        views[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar("定位");
            }
        });
        views[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlag){
                    closeAnim();
                }else {
                    showAnim();
                }
                mFlag = !mFlag;
            }
        });
    }

    /**
     * 关闭动画
     */
    private void closeAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(views[4], "alpha", 1f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(views[0], "translationY", 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(views[1], "translationX", 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(views[2], "translationX", 0);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(views[3], "translationY", 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0,animator1,animator2,animator3,animator4);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                for (int i = 0; i < 4; i++) {
                    views[i].setVisibility(View.GONE);
                }
            }
        });
        set.start();
    }

    /**
     * 开启动画
     */
    private void showAnim() {
        for (int i = 0; i < 4; i++) {
            views[i].setVisibility(View.VISIBLE);
        }
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(views[4], "alpha", 1f, 0.5f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(views[0], "translationY", -200);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(views[1], "translationX", -200);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(views[2], "translationX", 200);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(views[3], "translationY", 200);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0,animator1,animator2,animator3,animator4);
        set.start();
    }

    public void rotate(final View view) {
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

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyTypeEvaluator(), new Point(0, 0));
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point paint = (Point) animation.getAnimatedValue();
                image.setX(paint.x);
                image.setY(paint.y);
            }
        });
        valueAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void trans(View view) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX"
                , 0, 1.0f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1.0f);
        animatorSet.playTogether(objectAnimator, objectAnimator1);
//        animatorSet.setDuration(2000)
//                .start();
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1.0f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("alpha", 0, 1.0f);
//        ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2)
//                .setDuration(2000).start();
        view.animate()
                .alpha(0.5f)
                .setDuration(2000)
                .translationX(300)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Logger.d("view run");
                    }
                }).start();
    }
}
