package liu.myapplication.bean;

/**
 * @PackageName: liu.myapplication.bean
 * @Description:
 * @author:
 * @date: 2016/9/30 13:22
 */
public class NutritionFacts {
    private final int servingSize;//必要
    private final int servings;//必要
    private final int calories;//可选
    private final int fat;//可选
    private final int sodium;//可选
    private final int carbohydrate;//可选

    public static class  Builder{
        private final int servingSize;//必要
        private final int servings;//必要
        private int calories=0;//可选
        private int fat=0;//可选
        private int sodium=0;//可选
        private int carbohydrate=0;//可选

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val){
            calories = val;
            return this;
        }
        public Builder fat(int val){
            fat=val;
            return this;
        }
        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    }
    private NutritionFacts(Builder builder) {
        this.servingSize=builder.servingSize;
        this.servings=builder.servings;
        this.calories=builder.calories;
        this.fat=builder.fat;
        this.sodium=builder.sodium;
        this.carbohydrate=builder.carbohydrate;
    }
}
