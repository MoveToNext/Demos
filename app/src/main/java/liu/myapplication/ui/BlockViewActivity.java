package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import liu.myapplication.R;
import liu.myapplication.view.BaseActivity;

/**
 * PackageName:  liu.myapplication.ui
 * Description: 自定义表
 * date:   2016/11/1 16:00
 */
public class BlockViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blockview);
    }
}
