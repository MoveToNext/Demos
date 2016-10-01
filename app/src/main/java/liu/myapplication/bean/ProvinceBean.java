package liu.myapplication.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * @PackageName: liu.myapplication.bean
 * @Description:
 * @author:
 * @date: 2016/9/27 17:34
 */
public class ProvinceBean implements IPickerViewData {
    private String content;
    public ProvinceBean(String content) {
        this.content = content;
    }

    @Override
    public String getPickerViewText() {
        return content;
    }
}
