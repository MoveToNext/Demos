package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import liu.myapplication.R;

/**
 * @PackageName: liu.myapplication.ui
 * @Description:
 * @author: LanYing
 * @date: 2016/7/26 11:44
 */
public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
    }
}
