package sample;
import java.util.Comparator;
import java.util.Timer;

/**
 * Created by nico on 9/30/15.
 */
public class Item {
    private String name;
    private int amount;
    private int price;

    public Item(String name, int amount, int price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void decreaseAmount(int numSubtract) {
        if (amount >= numSubtract) {
            amount -= numSubtract;
        } else {
            amount = 0;
        }
    }

    public void increaseAmount(int numAdd) {
        amount += numAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item that = (Item) o;
        return this.getName().equals(that.getName());
    }
}
