package liu.myapplication.Interface.Impl;

import com.orhanobut.logger.Logger;

import liu.myapplication.Interface.RoleObserver;

/**
 * @PackageName: liu.myapplication.Interface.Impl
 * @Description:
 * @author: LanYing
 * @date: 2016/7/26 17:09
 */
public class AObserver implements RoleObserver {
    @Override
    public void updata(int msg) {
        if (msg == 0){
            Logger.e("我是A，我接到了正确的指令0");
        }else if (msg == 1){
            Logger.e("我是A，我接到了正确的指令1");
        }else {
            Logger.e("我是A，指令错误");
        }
    }
}
