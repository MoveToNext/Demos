package liu.myapplication.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: liu.myapplication.utils
 * @Description:
 * @author:
 * @date: 2016/8/18 13:45
 */
public class DataUtil {
    private static String[][] expandDatas = {{"1","2","3"},{"1","d","d"},{"f","r","r","2"}};
    public static String[][] getMutuData(){
        return expandDatas;
    }
    public static List<String> list = new ArrayList<>();

    public static List<String> getChildData(){
        list.add("苹果");
        list.add("梨");
        list.add("香蕉");
//        list.add("橘子");
//        list.add("橙子");
//        list.add("番茄");
//        list.add("橘子");
//        list.add("橘子");
//        list.add("橘子");
        return list;
    }
}
