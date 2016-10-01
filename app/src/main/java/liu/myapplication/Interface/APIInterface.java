package liu.myapplication.Interface;

import liu.myapplication.bean.TestModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @PackageName: liu.myapplication.Interface
 * @Description: retrofit
 * @author:
 * @date: 2016/9/26 17:15
 */
public interface APIInterface {

    @GET("/users/{user}")
    Call<TestModel> repo(@Path("user") String user);
}
