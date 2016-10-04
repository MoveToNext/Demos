package liu.myapplication.image;

import android.content.Context;

/**
 * @PackageName: liu.myapplication.image
 * @Description: 配置类
 * @author:
 * @date: 2016/10/4 13:49
 */
public class ImageLoaderConfig {
    public Context context;
    ImageCache mCache = new MemoryCache();
    int threadCount = Runtime.getRuntime().availableProcessors()+1;
    private ImageLoaderConfig(){}
    /**
     * 配置的builder
     */
    public static class Builder{
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }
        ImageCache imageCache = new MemoryCache();
        int threadCount = Runtime.getRuntime().availableProcessors()+1;
        public Builder setCache(ImageCache imageCache){
            imageCache = imageCache;
            return this;
        }
        public Builder setthreadCount(int count){
            threadCount = count;
            return this;
        }

        /**
         * 根据已经设置好的属性创建配置对象
         */
        public ImageLoaderConfig create(){
            ImageLoaderConfig config = new ImageLoaderConfig();
            appConfig(config);
            return config;
        }

        private void appConfig(ImageLoaderConfig config) {
            config.mCache = this.imageCache;
            config.threadCount = this.threadCount;
            config.context = this.context;
        }
    }

}
