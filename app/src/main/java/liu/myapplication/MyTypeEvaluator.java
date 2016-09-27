package liu.myapplication;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * @PackageName: liu.myapplication
 * @Description: 估值器
 * @author: MIFM
 * @date: 2016/7/22 14:31
 */
public class MyTypeEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        Log.e("fraction", fraction + "");
        Point paint = new Point();
        paint.x = 100*fraction * 3f;
        paint.y = 100 * (fraction * 3f) * (fraction * 3f);
        return paint;
    }
}
