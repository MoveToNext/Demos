package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liu.myapplication.R;
import liu.myapplication.recyclerView.Category;
import liu.myapplication.recyclerView.CategoryAdapter;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: RecyclerViewActivity
 * @author: mifm
 * @date: 2016/8/12 14:23
 */
public class RecyclerViewActivity extends AppCompatActivity {
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;
    private List<Category> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {

        final CategoryAdapter adapter = new CategoryAdapter(this, mDatas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItemViewType(position) == 0){
                    return 1;
                }
                return 1;
            }
        });
        RecyclerView.setLayoutManager(gridLayoutManager);
        RecyclerView.setAdapter(adapter);
    }

    private void initData() {
        mDatas.add(new Category("水果", 0));
        mDatas.add(new Category("梨", 1));
        mDatas.add(new Category("水蜜桃", 1));
        mDatas.add(new Category("苹果", 1));
        mDatas.add(new Category("荔枝", 1));
        mDatas.add(new Category("葡萄", 1));
        mDatas.add(new Category("提子", 1));
        mDatas.add(new Category("蔬菜", 0));
        mDatas.add(new Category("土豆", 1));
        mDatas.add(new Category("洋葱", 1));
        mDatas.add(new Category("西红柿", 1));
        mDatas.add(new Category("辣椒", 1));
    }
}
