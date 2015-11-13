package sample;
//import com.sun.tools.javac.jvm.Items;
//import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacbookRetina on 9/30/15.
 */
public class Player {
    private String name;
    private String color;
    private String race;
    private List<Item> items;
    private long money;
    private List<Tile> tiles;
    private static long score;
    private static long fMule;
    private static long eMule;
    private static long oMule;

    public Player(String name, String color, String race) {
        this.name = name;
        this.color = color;
        this.race = race;
        items = new ArrayList<>();
        tiles = new ArrayList<>();
        this.fMule = 0;
        this.eMule = 0;
        this.oMule = 0;
        this.money = 0;
    }

    final public void addItem(Item item) {
        if (!items.contains(item)) {
            this.items.add(item);
        } else {
            items.get(items.indexOf(item)).increaseAmount(item.getAmount());
        }
    }

    final public void addProperty(Tile tile) {
        this.tiles.add(tile);
    }

    final public String getName() {
        return name;
    }

    final public String getColor() {
        return color;
    }

    final public String getRace() {
        return race;
    }

    final public void setMoney(long money) {
        this.money = money;
    }

    final public long getMoney() {
        return money;
    }

    final public String toString() {
        return name + " " + race + " " + color;
    }

    final public void setScore(long newScore) {
        this.score = newScore;
    }

    final public long getScore() {
        return this.score;
    }

    final public int numTiles() {
        return tiles.size();
    }

    final public int valueOfGoods() {
        int sum = 0;
        for (Item i: items) {
            sum += (i.getAmount() * i.getPrice());
        }
        return sum;
    }

    final public List<Item> getItems() {
        return items;
    }

    final public Item getSpecificItem(int i) {
        return items.get(i);
    }

    final public List<Tile> getTiles() {
        return tiles;
    }

    final public void calculateScore() {
        long i = this.getSpecificItem(0).getAmount() + this.getSpecificItem
                (1).getAmount() + this.getSpecificItem(2).getAmount();
        long total = getMoney() + tiles.size() * 500 + i;
        setScore(total);
    }

    final public void addFMule() {
        fMule++;
    }

    final public void addEMule() {
        eMule++;
    }

    final public void addOMule() {
        oMule++;
    }

    final public void calcProduction() {
        System.out.println(name + " owns " + fMule + " Food Mules");
        System.out.println(name + " owns " + eMule + " Energy Mules");
        System.out.println(name + " owns " + oMule + " Ore Mules");
        if ((this.getSpecificItem(1).getAmount()) > (fMule + eMule + oMule) ) {
            if (fMule > 0) {
                long a = fMule * 2;
                System.out.println(a + " units of Food were produced");
                this.getSpecificItem(0).setAmount(this.getSpecificItem(0).getAmount() +a);
            }
            if (eMule > 0) {
                long b = eMule * 3;
                System.out.println(b + " units of Energy were produced");
                this.getSpecificItem(1).setAmount(this.getSpecificItem(1).getAmount() + b);
            }
            if (oMule > 0) {
                long c = oMule * 1;
                System.out.println(c + " units of Ore were produced");
                this.getSpecificItem(2).setAmount(this.getSpecificItem(2).getAmount() + c);
            }
        }

    }
}
