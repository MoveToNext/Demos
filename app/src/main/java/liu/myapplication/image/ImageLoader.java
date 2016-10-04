package liu.myapplication.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @PackageName: liu.myapplication.image
 * @Description: 图片加载类
 * @author: mifm
 * @date: 2016/10/1 12:13
 */
public class ImageLoader  {
    public static final String TAG = "imageloader";
    private Context context;
    public ImageCache mMemoryCache;
    ExecutorService mExecutorService  = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ImageLoader(){}
    public volatile static ImageLoader mImageLoader;
    public static ImageLoader getInstance(){
        if (mImageLoader == null){
            synchronized (ImageLoader.class){
                mImageLoader = new ImageLoader();
            }
        }
        return mImageLoader;
    }

    /**
     * 图片加载配置对象
     * @param config
     */
    public void init(ImageLoaderConfig config){
        mMemoryCache = config.mCache;
        this.context = config.context;
        setThreadCount(config.threadCount);
    }
    //设置新的线程数量
    private void setThreadCount(int count){
        mExecutorService.shutdown();
        mExecutorService = null;
        mExecutorService = Executors.newFixedThreadPool(count);
    }
    /**
     * 加载图片
     * @param url
     * @param imageView
     */
    public void displayImage(final String url, final ImageView imageView){
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null){
                    return;
                }
                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            Log.d(TAG, "downloadImage: 没有缓存");
            try {
                URL urll = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urll.openConnection();
                Bitmap bitmap1 = BitmapFactory.decodeStream(conn.getInputStream());
                mMemoryCache.put(url,bitmap1);
                conn.disconnect();
                return bitmap1;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "downloadImage: 有缓存");
        return bitmap;
    }
}
