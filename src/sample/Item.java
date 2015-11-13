package sample;

/**
 * Created by nico on 9/30/15.
 */
public class Item {
    private String name;
    private long amount;
    private long price;


    public Item(String name1, long amount1, long price1) {
        this.name = name1;
        this.amount = amount1;
        this.price = price1;
    }

    public final String getName() {
        return this.name;
    }

    public final long getAmount() {
        return amount;
    }

    public final long getPrice() {
        return price;
    }

    public final void setAmount(long food) {
        this.amount = food;
    }

    public final void decreaseAmount(int numSubtract) {
        if (amount >= numSubtract) {
            amount -= numSubtract;
        } else {
            amount = 0;
        }
    }

    public final void increaseAmount(long numAdd) {
        amount += numAdd;
    }

    @Override
    public final boolean equals(Object o) {
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

    @Override
    public abstract int hashCode();

    public final String toString() {
        return this.name + " " + amount;
    }
}
