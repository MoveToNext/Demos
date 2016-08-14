package liu.myapplication.recyclerView;

/**
 * @PackageName: liu.myapplication.recyclerView
 * @Description:
 * @author:
 * @date: 2016/8/12 14:50
 */
public class Category {
    public static final int SECOND_TYPE = 0;
    public final static int THIRD_TYPE  = 1;
    private String categoryName;
    private int type;

    public String getCategoryName() {
        return categoryName;
    }

    public Category(String categoryName, int type) {
        this.categoryName = categoryName;
        this.type = type;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
