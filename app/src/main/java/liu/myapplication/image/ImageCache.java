package liu.myapplication.image;

import android.graphics.Bitmap;

/**
 * @PackageName: liu.myapplication.image
 * @Description: 缓存接口
 * @author:
 * @date: 2016/10/1 14:06
 */
public interface ImageCache {
    public Bitmap get(String url);

    public void put(String url, Bitmap bitmap);
}
