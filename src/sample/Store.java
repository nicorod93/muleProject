package sample;

import java.util.ArrayList;

/**
 * Created by nico on 10/2/15.
 */
public class Store {
    private ArrayList<Item> items;

    public Store() {
        items = new ArrayList<>();
        if (Main.difficulty.equals("Beginner")) {
            items.add(new Item("Food", 16, 30));
            items.add(new Item("Energy", 16, 25));
            //items.add(new Item("Mule", 25, ));
        }
        else {
            items.add(new Item("Food", 8, 30));
            items.add(new Item("Energy", 8, 25));
            items.add(new Item("Smithore", 8, 50));
            //Mule.add(new Item("Mule", 14));
        }
    }

    public void buyItem() {
    }

    public void sellItem() {
    }
}
