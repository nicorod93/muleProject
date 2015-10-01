package sample;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by MacbookRetina on 9/30/15.
 */
public class Player {
    private String name;
    private Color color;
    private String race;
    private ArrayList<Item> items;
    private int money;
    private ArrayList<Tile> tiles;

    public Player(String name, Color color, String race) {
        this.name = name;
        this.color = color;
        this.race = race;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addProperty(Tile tile) {
        tiles.add(tile);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public String getRace() {
        return race;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
