package sample;

/**
 * Created by Beiwen on 10/2/15.
 */
public abstract class Mule {
    private Item item;
    private int cost;
    private String name;

    public final int getItem() {return item};

    public final int getCost() {
        return cost;
    }

    public final String getName() {
        return name;
    }
}
