package sample;

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
    private List<Tile> tiles;
    private static int score;
    private static int money;
    private int numMules;

    public Player(String name, String color, String race) {
        this.name = name;
        this.color = color;
        this.race = race;
        this.numMules = 0;
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

    public int setScore(int newScore) {
        return this.score = newScore;
    }

    public int getScore() {
        return this.score;
    }

    public int numTiles() {
        return tiles.size();
    }

    public int getNumMules() {
        return numMules;
    }

    public int increaseMules() {
        return numMules + 1;
    }

    public int decreaseMules() {
        return numMules - 1;
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

    public Tile getTileAt(String name, int x, int y) {
        Tile tile = new Tile(name, x, y);
        for (int i = 0; i < Main.tileMap.length; i++) {
            for (Tile t : Main.tileMap[i]) {
                if (tile.equals(t)) {
                    System.out.println("EQUAL!");
                    return t;
                }
            }
        }
        System.out.println("Not equal");
        return null;
    }

}
