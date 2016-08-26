package liu.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.bean.WeatherBean;
import liu.myapplication.utils.Constants;
import liu.myapplication.utils.HttpUtils;
import liu.myapplication.view.BaseActivity;
import okhttp3.Call;

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

    private void initCityDatas() {
        new Thread().start();
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
        HttpUtils.get(Constants.TIANQISERVER, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logger.e(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(response, WeatherBean.class);
                showDatas(weatherBean);
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
