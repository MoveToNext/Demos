package liu.myapplication.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liu.myapplication.R;
import liu.myapplication.pulltorefreshlistview.PullToRefreshBase;
import liu.myapplication.pulltorefreshlistview.PullToRefreshListView;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: PullToRefreshListActivity
 * @author: mifm
 * @date: 2016/8/5 16:36
 */
public class PullToRefreshListActivity extends AppCompatActivity {
    @BindView(R.id.ListView)
    PullToRefreshListView pullToRefreshListView;
    private android.widget.ListView listView;
    //    String[] array={"aaa","bbb","ccc","ddd","eee","eee","eee","eee","eee","eee","eee"};
    List<String> strings = new ArrayList<>();
    String[] array={"aaa","bbb","ccc","ddd","eee"};
    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);
        ButterKnife.bind(this);
        initData();
        initListView();
        View childAt = ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
    }

    private void initData() {
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        strings.add("ddd");
        strings.add("fff");
        strings.add("ggg");
    }

    private void initListView() {
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
//        pullToRefreshListView.setHasMoreData(false);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Logger.d("下拉刷新");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshListView.onPullDownRefreshComplete();
                                Logger.d("下拉刷新完成");
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Logger.d("上拉加载;");
                strings.add("ooo");
//                                adapter.notifyDataSetChanged();
                pullToRefreshListView.onPullUpRefreshComplete();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        SystemClock.sleep(3000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                strings.add("ooo");
////                                adapter.notifyDataSetChanged();
//                                pullToRefreshListView.onPullUpRefreshComplete();
//                                Logger.d("上拉加载完成完成");
//                            }
//                        });
//                    }
//                }).start();
            }
        });
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t = new TextView(PullToRefreshListActivity.this);
            t.setText(strings.get(position));
            return t;
        }
    }
}
