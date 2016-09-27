package liu.myapplication.bean;

import java.util.List;

/**
 * @PackageName: liu.myapplication.bean
 * @Description:
 * @author:
 * @date: 2016/9/27 13:29
 */
public class CityBean {
    private List<Cities> cities ;

    private String code;

    private String name;

    public void setCities(List<Cities> cities){
        this.cities = cities;
    }
    public List<Cities> getCities(){
        return this.cities;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
