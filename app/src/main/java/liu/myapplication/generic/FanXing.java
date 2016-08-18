package liu.myapplication.generic;

import android.widget.TextView;

/**
 * @PackageName: liu.myapplication.generic
 * @Description: 泛型类
 * @author: mifm
 * @date: 2016/8/17 10:05
 */
public class FanXing<T extends TextView> {
    private T[] items;
    private T test;
    public FanXing(T t) {
        this.test = t;
    }

    public T print(){
        if (test instanceof TextView){
            test.setText("哈哈，我来了");
            return test;
        }
        return null;
    }
}
