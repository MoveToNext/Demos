package liu.myapplication.Interface.Impl;

import com.orhanobut.logger.Logger;

import liu.myapplication.Interface.RoleObserver;

/**
 * @PackageName: liu.myapplication.Interface.Impl
 * @Description:
 * @author: LanYing
 * @date: 2016/7/26 17:10
 */
public class BObserver implements RoleObserver {
    @Override
    public void updata(int msg) {
        if (msg == 0){
            Logger.e("我是B，我接到了正确的指令0");
        }else if (msg == 1){
            Logger.e("我是B，我接到了正确的指令1");
        }else {
            Logger.e("我是B，指令错误");
        }
    }
}
