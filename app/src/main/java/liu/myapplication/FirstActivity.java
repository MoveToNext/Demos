package liu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.utils.library.SPUtil;

/**
 * @PackageName: liu.myapplication
 * @Description:
 * @author:
 * @date: 2016/8/24 15:26
 */
public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirst = (boolean) SPUtil.getSP(this, "isFirst", true);
        if (isFirst){
            startActivity(new Intent(this,LoadingActivity.class));
        }else {
            startActivity(new Intent(this,MainActivity.class));
        }
        finish();
    }
}
