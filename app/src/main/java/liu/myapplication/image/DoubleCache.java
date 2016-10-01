package liu.myapplication.image;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * @PackageName: liu.myapplication.image
 * @Description: 双缓存
 * @author:
 * @date: 2016/10/1 15:13
 */
public class DoubleCache implements ImageCache {
    ImageCache mMemoryCache ;
    ImageCache mDiskCache;
    private Context context;

    public DoubleCache(Context context) {
        this.context = context;
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache(context);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            bitmap = mDiskCache.get(url.substring(url.length() - 5, url.length()));
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url.substring(url.length()-5,url.length()),bitmap);
    }
}
