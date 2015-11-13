package sample;

/**
 * Created by Beiwen on 10/2/15.
 */
public abstract class Mule {
    private Item item;
    protected int cost;
    protected String name;

    public final Item getItem() {return item;}

    public final int getCost() {
        return cost;
    }

    public final String getName() {
        return name;
    }
}
