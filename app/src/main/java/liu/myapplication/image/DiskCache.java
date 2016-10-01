package liu.myapplication.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.orhanobut.logger.Logger;
import com.utils.library.BitmapUtil;

import java.io.File;

/**
 * @PackageName: liu.myapplication.image
 * @Description:
 * @author:
 * @date: 2016/10/1 14:08
 */
public class DiskCache implements ImageCache {
    public  String cacheDir;

    private Context context;
    private String substring;

    public DiskCache(Context context) {
        this.context = context;
        cacheDir = context.getCacheDir()+"/image/";
    }

    @Override
    public Bitmap get(String url) {
        substring = url.substring(url.length() - 5, url.length());
        Logger.d(substring);
        Bitmap bitmap = null;
        File file = new File(cacheDir);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            bitmap = BitmapFactory.decodeFile(cacheDir + substring);
        }catch (Exception e){

        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {

        BitmapUtil.bitmap2File(bitmap,new File(cacheDir+substring));
    }
}
