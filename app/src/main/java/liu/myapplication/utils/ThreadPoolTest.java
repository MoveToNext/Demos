package liu.myapplication.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @PackageName: liu.myapplication.utils
 * @Description:
 * @author:
 * @date: 2016/9/30 16:28
 */
public class ThreadPoolTest {
    private ThreadPoolTest(){};
    private ExecutorService mCachedThreadPool;
    private ExecutorService mFixedThreadPool;
    private ExecutorService mSingleThreadExecutor;
    private static ThreadPoolTest threadPoolTest = new ThreadPoolTest();
    public static ThreadPoolTest getInstance() {
        return threadPoolTest;
    }

    public ExecutorService getnewCachedThreadPool() {
        if (mCachedThreadPool == null) {
            mCachedThreadPool = Executors.newCachedThreadPool();
        }
        return mCachedThreadPool;
    }

    public ExecutorService getnewFixedThreadPool(int i) {
        if (mFixedThreadPool == null) {
            mFixedThreadPool = Executors.newFixedThreadPool(i);
        }
        return mFixedThreadPool;
    }

    public ExecutorService getSingleThreadExecutor() {
        if (mSingleThreadExecutor == null) {
            mSingleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        return mSingleThreadExecutor;
    }

    public ThreadPoolExecutor getCustomPool() {
        return new ThreadPoolExecutor(2,4,300,TimeUnit.MILLISECONDS,new PriorityBlockingQueue<Runnable>());
    }
}
