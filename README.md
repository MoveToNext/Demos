#一个平时保存零碎笔记的仓库
属性动画的展示
##属性动画
+ ###ObjectAnimator 动画的执行类
		
		animation.ofFloat(view, "alpha", 1.0F, 0.5F,1.0F)
		透明度从1变到0再变到1
		ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
		旋转X轴

+ ###ValueAnimator 实现动画

	ValueAnimator是ObjectAnimator的基类

		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setTranslationY((float)animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup parent = (ViewGroup) image.getParent();
                parent.removeView(image);
            }
        });
		
+ ###TimeInterpolator（时间插值器）：一般用自带的就可以了

	根据时间流逝的百分比计算出当前属性值改变的百分比。

+ ###TypeEvaluator（类型估值算法，即估值器）： 

	根据当前属性改变的百分比来计算改变后的属性值
		
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

