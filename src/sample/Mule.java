package sample;

/**
 * Created by Beiwen on 10/2/15.
 */
public abstract class Mule {
    private Item item;
    private int cost;
    private String name;

    public final Item getItem() {return item;}

    public final int getCost() {
        return cost;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name1) {
        name = name1;
    }

    public final void setCost(int cost1) {
        cost = cost1;
    }
}
