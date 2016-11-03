package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import liu.myapplication.R;
import liu.myapplication.BaseActivity;

public class CustomViewGroupActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewgroup);
    }
}
