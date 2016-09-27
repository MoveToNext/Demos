package liu.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utils.library.ResourceUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.Interface.WeatherApi;
import liu.myapplication.R;
import liu.myapplication.bean.CityOneBean;
import liu.myapplication.bean.WeatherBean;
import liu.myapplication.view.BaseActivity;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: 生活辅助工具类
 * @author: mifm
 * @date: 2016/8/26 14:18
 */
public class LifeToolsActivity extends BaseActivity {

    @BindView(R.id.btn_sendRequest)
    Button btnSendRequest;
    @BindView(R.id.vMasker)
    View vMasker;
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    @BindView(R.id.btn_choose)
    Button btnChoose;
    private Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ButterKnife.bind(this);
        RecyclerView.setLayoutManager(new LinearLayoutManager(context));
        initCityDatas();
    }

    /**
     * 加载城市数据
     */
    private void initCityDatas() {
        new Thread(){
            @Override
            public void run() {
                String ss = ResourceUtil.geFileFromAssets(getApplicationContext(), "city-code.json");
                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<CityOneBean>>(){}.getType();
                List<CityOneBean> list = (List<CityOneBean>) gson.fromJson(ss, collectionType);
                Log.d("LifeToolsActivity", list.toString());
            }
        }.start();
    }

    @OnClick({R.id.btn_sendRequest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sendRequest:
                sendRequest();
                break;

            case R.id.btn_choose:


                break;
        }
    }

    private void sendRequest() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "1680e4bfb1f00");
        map.put("city", "闵行");
        map.put("province", "上海");
//        HttpUtils.get(Constants.TIANQISERVER, map, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Logger.e(e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Gson gson = new Gson();
//                WeatherBean weatherBean = gson.fromJson(response, WeatherBean.class);
//                showDatas(weatherBean);
//            }
//        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apicloud.mob.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        retrofit2.Call<WeatherBean> beanCall = weatherApi.mycall(map);
        beanCall.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(retrofit2.Call<WeatherBean> call, Response<WeatherBean> response) {
                showDatas(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<WeatherBean> call, Throwable t) {

            }
        });
    }

    private void showDatas(WeatherBean weatherBean) {
        final List<WeatherBean.ResultBean.FutureBean> result = weatherBean.getResult().get(0).getFuture();
        Log.d("dd", result.toString());
        RecyclerView.setAdapter(new CommonAdapter<WeatherBean.ResultBean.FutureBean>(context, R.layout.item_list_category_second, result) {
            @Override
            protected void convert(ViewHolder holder, WeatherBean.ResultBean.FutureBean o, int position) {
                holder.setText(R.id.text22, o.getTemperature());
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        });
    }
}
