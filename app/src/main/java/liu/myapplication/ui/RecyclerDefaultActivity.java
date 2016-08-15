package liu.myapplication.ui;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import liu.myapplication.R;
import liu.myapplication.recycle.RecycleAdapter;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: RecyclerDefaultActivity
 * @author: mifm
 * @date: 2016/8/15 10:40
 */
public class RecyclerDefaultActivity extends AppCompatActivity {
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView RecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_default);
        ButterKnife.bind(this);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });
        RecyclerView.setAdapter(new RecycleAdapter(this));
    }
}
