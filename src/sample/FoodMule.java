package sample;

/**
 * Created by user on 10/12/2015.
 */
public class FoodMule extends Mule {
    public static final int FOOD_COST = 125;

    public FoodMule() {
        super.cost = FOOD_COST;
        super.name = "Food Mule";
    }
}
