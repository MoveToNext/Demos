package liu.myapplication.image;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @PackageName: liu.myapplication.image
 * @Description:
 * @author:
 * @date: 2016/10/1 12:48
 */
public class MemoryCache implements ImageCache{
    //图片缓存
    public LruCache<String, Bitmap> mImageCache;
    public MemoryCache() {
        initImageCache();
    }
    private void initImageCache() {
        //计算可使用最大内存
        int maxMemoy = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemoy / 4;
        mImageCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() /1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap){
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url){
        return mImageCache.get(url);
    }
}
