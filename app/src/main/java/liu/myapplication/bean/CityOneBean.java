package liu.myapplication.bean;

import java.util.List;

/**
 * @PackageName: liu.myapplication.bean
 * @Description:
 * @author:
 * @date: 2016/9/27 13:38
 */
public class CityOneBean {

    /**
     * cities : [{"cities":[{"cities":[],"code":"101010200","name":"海淀"},{"cities":[],"code":"101010300","name":"朝阳"},{"cities":[],"code":"101010400","name":"顺义"},{"cities":[],"code":"101010500","name":"怀柔"},{"cities":[],"code":"101010600","name":"通州"},{"cities":[],"code":"101010700","name":"昌平"},{"cities":[],"code":"101010800","name":"延庆"},{"cities":[],"code":"101010900","name":"丰台"},{"cities":[],"code":"101011000","name":"石景山"},{"cities":[],"code":"101011100","name":"大兴"},{"cities":[],"code":"101011200","name":"房山"},{"cities":[],"code":"101011300","name":"密云"},{"cities":[],"code":"101011400","name":"门头沟"},{"cities":[],"code":"101011500","name":"平谷"}],"code":"101010200","name":"北京"}]
     * code : 101010200
     * name : 北京
     */

    private String code;
    private String name;
    /**
     * cities : [{"cities":[],"code":"101010200","name":"海淀"},{"cities":[],"code":"101010300","name":"朝阳"},{"cities":[],"code":"101010400","name":"顺义"},{"cities":[],"code":"101010500","name":"怀柔"},{"cities":[],"code":"101010600","name":"通州"},{"cities":[],"code":"101010700","name":"昌平"},{"cities":[],"code":"101010800","name":"延庆"},{"cities":[],"code":"101010900","name":"丰台"},{"cities":[],"code":"101011000","name":"石景山"},{"cities":[],"code":"101011100","name":"大兴"},{"cities":[],"code":"101011200","name":"房山"},{"cities":[],"code":"101011300","name":"密云"},{"cities":[],"code":"101011400","name":"门头沟"},{"cities":[],"code":"101011500","name":"平谷"}]
     * code : 101010200
     * name : 北京
     */

    private List<CitiesBean> cities;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    public static class CitiesBean {
        private String code;
        private String name;
        /**
         * cities : []
         * code : 101010200
         * name : 海淀
         */

        private List<CitieBean> cities;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CitieBean> getCities() {
            return cities;
        }

        public void setCities(List<CitieBean> cities) {
            this.cities = cities;
        }

        public static class CitieBean {
            private String code;
            private String name;
            private List<Object> cities;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Object> getCities() {
                return cities;
            }

            public void setCities(List<Object> cities) {
                this.cities = cities;
            }
        }
    }
}
