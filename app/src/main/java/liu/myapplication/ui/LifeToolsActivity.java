package liu.myapplication.ui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.utils.library.ResourceUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import liu.myapplication.BaseActivity;
import liu.myapplication.Interface.WeatherApi;
import liu.myapplication.R;
import liu.myapplication.bean.CityOneBean;
import liu.myapplication.bean.ProvinceBean;
import liu.myapplication.bean.WeatherBean;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: 生活辅助工具类
 * @author: mifm
 * @date: 2016/8/26 14:18
 */
public class LifeToolsActivity extends BaseActivity {
    public static final String TAG = "LifeToolsActivity";
    @BindView(R.id.btn_sendRequest)
    Button btnSendRequest;
    @BindView(R.id.vMasker)
    View vMasker;
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    @BindView(R.id.btn_choose)
    Button btnChoose;
    private Context context = this;
    private OptionsPickerView pvOptions;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<IPickerViewData>>> options3Items = new ArrayList<>();
    private String tx;
    private String pickViewText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_life;
    }

    @Override
    public void initView() {
        RecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //选项选择器
        pvOptions = new OptionsPickerView(this);
        initCityDatas();
    }

    /**
     * 加载城市数据
     */
    private void initCityDatas() {
        Observable<List<CityOneBean>> observable = Observable.create(new ObservableOnSubscribe<List<CityOneBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CityOneBean>> e) throws Exception {
                String ss = ResourceUtil.geFileFromAssets(getApplicationContext(), "city-code.json");
                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<CityOneBean>>(){}.getType();
                List<CityOneBean> list = (List<CityOneBean>) gson.fromJson(ss, collectionType);
                e.onNext(list);
                e.onComplete();
            }

        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Consumer<List<CityOneBean>>() {
            @Override
            public void accept(List<CityOneBean> cityOneBeen) throws Exception {
                addData(cityOneBeen);
            }
        });
//        new Thread(){
//            @Override
//            public void run() {
//                String ss = ResourceUtil.geFileFromAssets(getApplicationContext(), "city-code.json");
//                Gson gson = new Gson();
//                Type collectionType = new TypeToken<List<CityOneBean>>(){}.getType();
//                List<CityOneBean> list = (List<CityOneBean>) gson.fromJson(ss, collectionType);
//                addData(list);
//            }
//        }.start();
    }

    /**
     * 填充省市的数据
     * @param list 省市的数据
     */
    private void addData(List<CityOneBean> list) {
        List<CityOneBean.CitiesBean.CitieBean> citieBeen = list.get(1).getCities().get(0).getCities();

        //一级菜单
            options1Items.add(new ProvinceBean(list.get(1).getName()));
        //选项2
        ArrayList<String> options2Items_01=new ArrayList<>();
        options2Items_01.add("上海");
        options2Items.add(options2Items_01);
        //选项3
        ArrayList<ArrayList<IPickerViewData>> options3Items_01 = new ArrayList<>();
        ArrayList<IPickerViewData> options3Items_01_01=new ArrayList<>();
        for (int i = 0; i < citieBeen.size(); i++) {
            options3Items_01_01.add(new ProvinceBean(citieBeen.get(i).getName()));
        }
        options3Items_01.add(options3Items_01_01);
        options3Items.add(options3Items_01);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
                //三级联动效果
                pvOptions.setPicker(options1Items, options2Items, options3Items, true);
                pvOptions.setTitle("选择城市");
                pvOptions.setCyclic(false, false, false);
                //设置默认选中的三级项目
                //监听确定选择按钮
                pvOptions.setSelectOptions(0, 0, 0);
                pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3) {
                        //返回的分别是三个级别的选中位置
                        pickViewText = options3Items.get(options1).get(option2).get(options3).getPickerViewText();
                        btnChoose.setText(pickViewText);
                    }
                });
//            }
//        });

    }

    @OnClick({R.id.btn_choose,R.id.btn_sendRequest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sendRequest:
                sendRequest();
                break;

            case R.id.btn_choose:
                pvOptions.show();
                break;
        }
    }

    private void sendRequest() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "1680e4bfb1f00");
        map.put("city", pickViewText);
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Observable<WeatherBean> beanCall = weatherApi.mycall(map);

        beanCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(WeatherBean value) {
                        showDatas(value);
                        Log.d(TAG, "onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });

//        beanCall.enqueue(new Callback<WeatherBean>() {
//            @Override
//            public void onResponse(retrofit2.Call<WeatherBean> call, Response<WeatherBean> response) {
//                showDatas(response.body());
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<WeatherBean> call, Throwable t) {
//
//            }
//        });
    }

    private void showDatas(WeatherBean weatherBean) {
        final List<WeatherBean.ResultBean.FutureBean> result = weatherBean.getResult().get(0).getFuture();
        RecyclerView.setAdapter(new CommonAdapter<WeatherBean.ResultBean.FutureBean>(context, R.layout.item_weather, result) {
            @Override
            protected void convert(ViewHolder holder, final WeatherBean.ResultBean.FutureBean o, int position) {
                holder.setText(R.id.temp, o.getTemperature());
                holder.setText(R.id.date, o.getDate());
                holder.setText(R.id.week, o.getWeek());
                holder.setText(R.id.qing, o.getDayTime()+"转"+o.getNight());
                holder.setOnClickListener(R.id.temp, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LifeToolsActivity.this);
                        dialog.setTitle("天气");
                        dialog.setMessage(o.getWind());
                        dialog.setNegativeButton("确定" ,null);
                        dialog.setPositiveButton("取消",null);
                        dialog.show();
                    }
                });
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

        });
    }
}
