package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import liu.myapplication.Interface.Impl.AObserver;
import liu.myapplication.Interface.Impl.BObserver;
import liu.myapplication.Interface.Impl.ImplSubject;
import liu.myapplication.R;

/**
 * @PackageName: liu.myapplication.ui
 * @Description:
 * @author: LanYing
 * @date: 2016/7/26 17:13
 */
public class ObserverTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ImplSubject subject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        subject = new ImplSubject(0);
        subject.addRoleObserver(new AObserver());
        subject.addRoleObserver(new BObserver());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_0:
                subject.setMsg(0);
                break;
            case R.id.btn_1:
                subject.setMsg(1);
                break;
        }
        subject.notifiFighter();
    }
}
