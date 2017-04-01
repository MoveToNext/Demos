package liu.myapplication.Interface;

import java.util.Map;

import io.reactivex.Observable;
import liu.myapplication.bean.WeatherBean;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @PackageName: liu.myapplication.Interface
 * @Description: WeatherApi
 * @author: mifm
 * @date: 2016/9/26 18:13
 */
public interface WeatherApi {
//    @GET("/v1/weather/query")
//    Call<WeatherBean> mycall(@Query("key") String key,
//                             @Query("city") String city,
//                             @Query("province") String pro);
//    @GET("/v1/weather/query")
//    Call<WeatherBean> mycall(@QueryMap Map<String,String> map);
    @GET("/v1/weather/query")
    Observable<WeatherBean> weatherQuery(@QueryMap Map<String,String> map);
}
