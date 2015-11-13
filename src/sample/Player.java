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
    private static final int TILE_SCORE = 500;
    public static final int MULE_PLACE = 3;


    public Player(String name1, String color1, String race1) {
        this.name = name1;
        this.color = color1;
        this.race = race1;
        items = new ArrayList<>();
        tiles = new ArrayList<>();
        this.fMule = 0;
        this.eMule = 0;
        this.oMule = 0;
        this.money = 0;
    }

    public final void addItem(Item item) {
        if (!items.contains(item)) {
            this.items.add(item);
        } else {
            items.get(items.indexOf(item)).increaseAmount(item.getAmount());
        }
    }


    public final void addProperty(Tile tile) {
        this.tiles.add(tile);
    }

    public final String getName() {
        return name;
    }

    public final String getColor() {
        return color;
    }

    public final String getRace() {
        return race;
    }

    public final void setMoney(long money1) {
        this.money = money1;
    }

    public final long getMoney() {
        return money;
    }

    public final String toString() {
        return name + " " + race + " " + color;
    }

    public final void setScore(long newScore) {
        this.score = newScore;
    }

    public final long getScore() {
        return this.score;
    }

    public final int numTiles() {
        return tiles.size();
    }

    public final int valueOfGoods() {
        int sum = 0;
        for (Item i: items) {
            sum += (i.getAmount() * i.getPrice());
        }
        return sum;
    }

    public final List<Item> getItems() {
        return items;
    }

    public final Item getSpecificItem(int i) {
        return items.get(i);
    }

    public final List<Tile> getTiles() {
        return tiles;
    }

    public final void calculateScore() {
        long i = this.getSpecificItem(0).getAmount() + this.getSpecificItem
                (1).getAmount() + this.getSpecificItem(2).getAmount();
        long total = getMoney() + tiles.size() * TILE_SCORE + i;
        setScore(total);
    }

    public final void addFMule() {
        fMule++;
    }

    public final void addEMule() {
        eMule++;
    }

    public final void addOMule() {
        oMule++;
    }

    public final void calcProduction() {
        if ((this.getSpecificItem(1).getAmount()) > (fMule + eMule + oMule) ) {
            if (fMule > 0) {
                long a = fMule * 2;
                this.getSpecificItem(0).setAmount(this.getSpecificItem(0).getAmount() +a);
            }
            if (eMule > 0) {
                long b = eMule * MULE_PLACE;
                this.getSpecificItem(1).setAmount(this.getSpecificItem(1).getAmount() + b);
            }
            if (oMule > 0) {
                long c = oMule * 1;
                this.getSpecificItem(2).setAmount(this.getSpecificItem(2).getAmount() + c);
            }
        }

    }
}
