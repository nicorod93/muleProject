package sample;
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

    public Player(String name, String color, String race, int money) {
        this.name = name;
        this.color = color;
        this.race = race;
        this.money = money;
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

    public String getColor() {
        return color;
    }

    public String getRace() {
        return race;
    }
}
