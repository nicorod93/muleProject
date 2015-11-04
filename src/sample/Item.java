package sample;
import java.util.Comparator;
import java.util.Timer;

/**
 * Created by nico on 9/30/15.
 */
public class Item {
    private String name;
    private long amount;
    private long price;

    public Item(String name) {
        this(name, 0, 0);
    }

    public Item(String name, long amount, long price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public long getAmount() {
        return amount;
    }

    public long getPrice() {
        return price;
    }

    public void setAmount(long food) {
        this.amount = food;
    }

    public void decreaseAmount(int numSubtract) {
        if (amount >= numSubtract) {
            amount -= numSubtract;
        } else {
            amount = 0;
        }
    }

    public void increaseAmount(long numAdd) {
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

    public String toString() {
        return this.name + " " + amount;
    }
}
