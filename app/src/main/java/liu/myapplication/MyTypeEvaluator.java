package liu.myapplication;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * @PackageName: liu.myapplication
 * @Description:
 * @author: LanYing
 * @date: 2016/7/22 14:31
 */
public class MyTypeEvaluator implements TypeEvaluator<Paint> {
    @Override
    public Paint evaluate(float fraction, Paint startValue, Paint endValue) {
        Log.e("fraction", fraction + "");
        Paint paint = new Paint();
        paint.x = 100*fraction * 3f;
        paint.y = 100 * (fraction * 3f) * (fraction * 3f);
        return paint;
    }
}
