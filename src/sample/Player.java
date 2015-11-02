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
    private static int money;
    private List<Tile> tiles;
    private static int score;

    public Player(String name, String color, String race) {
        this.name = name;
        this.color = color;
        this.race = race;
        items = new ArrayList<>();
        tiles = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {
            this.items.add(item);
        } else {
            items.get(items.indexOf(item)).increaseAmount(item.getAmount());
        }
    }

    public void addProperty(Tile tile) {
        this.tiles.add(tile);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getRace() {
        return race;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String toString() {
        return name + " " + race + " " + color;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public int getScore() {
        return this.score;
    }

    public int numTiles() {
        return tiles.size();
    }

    public int valueOfGoods() {
        int sum = 0;
        for (Item i: items) {
            sum += (i.getAmount() * i.getPrice());
        }
        return sum;
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getSpecificItem(int i) {
        return items.get(i);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void calculateScore() {
        int i = this.getSpecificItem(0).getAmount() + this.getSpecificItem(1).getAmount() + this.getSpecificItem(2).getAmount();
        int total = getMoney() + tiles.size() * 500 + i;
        setScore(total);
    }
}
