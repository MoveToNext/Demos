package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import liu.myapplication.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: Okhttp笔记
 * @author: LanYing
 * @date: 2016/7/26 13:26
 */
public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "OkhttpActivity";
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        findViewById(R.id.http_get).setOnClickListener(this);
        findViewById(R.id.http_post).setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.http_get:
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder().url("https://www.baidu.com/s").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.d("失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Logger.d("response.body().string()----" +response.body().string());
                    }
                });
                break;
            case R.id.http_post:
                OkHttpClient okHttpClient1 = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder().add("uid","万万想不到").add("from","ios").build();
                Request request1 = new Request.Builder().url("http://mapi.159cai.com/getanalyst.phpa").post(requestBody).build();
                okHttpClient1.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.d("失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        TAG = response.body().string();
                        Logger.d("response.body().string()----" +response.body().string());
                    }
                });
                break;
            case R.id.ok_http:
                break;
        }
    }
}
