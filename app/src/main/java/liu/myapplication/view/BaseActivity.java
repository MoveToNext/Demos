package liu.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @PackageName: liu.myapplication.view
 * @Description:
 * @author:
 * @date: 2016/8/26 11:45
 */
public class BaseActivity extends AppCompatActivity {

    public void openActivity(Class<?> mclass) {
        openActivity(mclass,null);
    }
    public void openActivity(Class<?> mclass, Bundle bundle) {

        Intent intent = new Intent(this,mclass);
        if (null != bundle){
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }
    public void showSnackbar(String msg){
        View decorView = getWindow().getDecorView();
        showSnackbar(decorView,msg,null);
        Snackbar.make(decorView,msg,Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackbar(View view,String msg,String action){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).setAction(action, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }
}
