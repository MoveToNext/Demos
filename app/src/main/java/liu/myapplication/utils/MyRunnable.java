package liu.myapplication.utils;

/**
 * @PackageName: liu.myapplication.utils
 * @Description:
 * @author:
 * @date: 2016/9/30 16:31
 */
public abstract class MyRunnable implements Runnable ,Comparable<MyRunnable>{
    private int priority;

    public MyRunnable(int priority) {
        if (priority < 0)
            throw new IllegalArgumentException();
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(MyRunnable o) {
        int my = this.getPriority();
        int other = o.getPriority();
        return my < other ? 1 : my > other ? -1 : 0;
    }

    @Override
    public void run() {
        doSth();
    }

    public abstract void doSth();

}
