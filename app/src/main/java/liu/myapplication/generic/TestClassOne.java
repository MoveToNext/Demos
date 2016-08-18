package liu.myapplication.generic;

import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: liu.myapplication.generic
 * @Description:
 * @author:
 * @date: 2016/8/17 11:19
 */
public class TestClassOne implements IFanxi {


    private List<Object> list = new LinkedList<>();
    @Override
    public void add(Object o) {
        list.add(o);
    }

    @Override
    public Object get() {
        return list.get(0);
    }

    @Override
    public void remove(Object o) {
        list.remove(o);
    }
}
