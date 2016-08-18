package liu.myapplication.generic;

/**
 * @PackageName: liu.myapplication.generic
 * @Description:
 * @author:
 * @date: 2016/8/17 11:16
 */
public interface IFanxi<T> {
    void add(T t);

    T get();

    void remove(T t);
}
