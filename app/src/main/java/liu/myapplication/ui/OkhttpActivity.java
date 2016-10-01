package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import liu.myapplication.Interface.APIInterface;
import liu.myapplication.R;
import liu.myapplication.bean.TestModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static liu.myapplication.R.id.retrofit;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: Okhttp笔记
 * @author: MIFM
 * @date: 2016/7/26 13:26
 */
public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "OkhttpActivity";
    @BindView(retrofit)
    Button btn_retrofit;
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
        findViewById(R.id.http_get).setOnClickListener(this);
        findViewById(R.id.http_post).setOnClickListener(this);
        btn_retrofit.setOnClickListener(this);
        text = (TextView) findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(TAG);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.http_get:
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder().url("http://www.baidu.com/s").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.d("失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Logger.d("response.body().string()----" + response.body().string());
                    }
                });
                break;
            case R.id.http_post:
                OkHttpClient okHttpClient1 = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder().add("uid", "万万想不到").add("from", "ios").build();
                Request request1 = new Request.Builder().url("http://mapi.159cai.com/getanalyst.phpa").post(requestBody).build();
                okHttpClient1.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.d("失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        TAG = response.body().string();
                        Logger.d("response.body().string()----" + response.body().string());
                    }
                });
                break;
            case R.id.ok_http:
                break;
            case retrofit:
                Retrofit retrofit= new Retrofit.Builder()
                        .baseUrl("https://api.github.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIInterface service = retrofit.create(APIInterface.class);
                retrofit2.Call<TestModel> modelCall = service.repo("MoveToNext");
                modelCall.enqueue(new retrofit2.Callback<TestModel>() {
                    @Override
                    public void onResponse(retrofit2.Call<TestModel> call, retrofit2.Response<TestModel> response) {
                        text.setText(response.body().getLogin());
                        Log.d(TAG, response.body().getLogin());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<TestModel> call, Throwable t) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
