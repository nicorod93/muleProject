package sample;
import com.sun.tools.javac.jvm.Items;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by MacbookRetina on 9/30/15.
 */
public class Player {
    private String name;
    private String color;
    private String race;
    private ArrayList<Item> items;
    private int money;
    private ArrayList<Tile> tiles;
    private int score;

    public Player(String name, String color, String race) {
        this.name = name;
        this.color = color;
        this.race = race;
        items = new ArrayList<>();
        tiles = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
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

    public int setScore(int newScore) {
        return this.score = newScore;
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
}
