package liu.myapplication.utils;

import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.Map;

/**
 * @PackageName: liu.myapplication.utils
 * @Description:
 * @author:
 * @date: 2016/8/26 14:41
 */
public class HttpUtils {
    public static void get(String url, Map<String,String> map, Callback callback) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.append(entry.getKey() + "="+entry.getValue()+"&");
        }
        Logger.d(url+"?"+builder.toString().substring(0,builder.length()-1));
        OkHttpUtils.get()
                .url(url)
                .params(map)
                .build()
                .execute(callback);
    }
}
