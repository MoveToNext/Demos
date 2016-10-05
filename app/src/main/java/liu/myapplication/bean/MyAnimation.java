package liu.myapplication.bean;

import android.graphics.Matrix;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @PackageName: liu.myapplication.bean
 * @Description: 自定义animation
 * @date: 2016/10/5 18:31
 */
public class MyAnimation extends Animation {
    private int mCenterWidth;
    private int mCenterHeight;

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        if (interpolatedTime < 0.8) {
            matrix.preScale(1 + 0.625f * interpolatedTime, 1 - interpolatedTime / 0.8f + 0.01f, mCenterWidth, mCenterHeight);
        } else {
            matrix.preScale(7.5f * (1 - interpolatedTime), 0.01f, mCenterWidth, mCenterHeight);
        }
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(500);
        setFillAfter(false);
        //保存View的中心点
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
        setInterpolator(new AccelerateDecelerateInterpolator());
    }
}
