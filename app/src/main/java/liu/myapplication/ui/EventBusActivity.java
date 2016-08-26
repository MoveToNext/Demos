package liu.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liu.myapplication.R;
import liu.myapplication.evnentMessage.TestMessage;
import liu.myapplication.view.BaseActivity;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: EventBusActivity
 * @author: mifm
 * @date: 2016/8/26 11:25
 */
public class EventBusActivity extends BaseActivity {
    @BindView(R.id.sendMessageToMain)
    Button sendMessageToMain;
    @BindView(R.id.et_changeToolBar)
    EditText etChangeToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sendMessageToMain)
    public void onClick() {
        String newName = etChangeToolBar.getText().toString().trim();
        if (TextUtils.isEmpty(newName)){
            showSnackbar("请必须输入！！！");
            return;

        }
        showSnackbar("Toolbar已经成功修改");
        EventBus.getDefault().post(new TestMessage(newName));
    }
}
