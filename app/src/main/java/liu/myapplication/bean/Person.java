package liu.myapplication.bean;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * @PackageName: liu.myapplication.bean
 * @Description:
 * @author:
 * @date: 2016/10/4 20:56
 */
public class Person {
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String age, String name) {
        this.age = age;
        this.name = name;
    }

    private String name;
    private String age;
    public void speak(String mes){
        Log.d("teach", mes);
        Logger.d(mes);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
