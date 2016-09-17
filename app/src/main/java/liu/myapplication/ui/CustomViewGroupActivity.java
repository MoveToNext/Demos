package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import liu.myapplication.R;
import liu.myapplication.view.BaseActivity;

/**
 * Created by liutongyang on 2016/9/17.
 */
public class CustomViewGroupActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewgroup);
    }
}
