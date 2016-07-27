package liu.myapplication.Interface.Impl;

import java.util.ArrayList;
import java.util.List;

import liu.myapplication.Interface.RoleObserver;
import liu.myapplication.Interface.Subject;

/**
 * @PackageName: liu.myapplication.Interface.Impl
 * @Description: 具体主题
 * @author: MIFM
 * @date: 2016/7/26 17:05
 */
public class ImplSubject implements Subject {
    private int msg;

    public ImplSubject(int msg) {
        this.msg = msg;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    List<RoleObserver> roleObservers = new ArrayList<>();
    public void addRoleObserver(RoleObserver roleObserver){
        roleObservers.add(roleObserver);
    }

    public void remoreRoleObserver(RoleObserver roleObserver){
        roleObservers.remove(roleObserver);
    }
    @Override
    public void notifiFighter() {
        for (RoleObserver observer :roleObservers){
            observer.updata(msg);
        }
    }
}
