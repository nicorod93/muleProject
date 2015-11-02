package sample;

import java.util.ArrayList;

/**
 * Created by nico on 10/2/15.
 */
public class Store {

    public Store() {
        Main.items = new ArrayList<>();
        if (Main.difficulty.equals("Beginner")) {
            Main.items.add(new Item("Food", 16, 30));
            Main.items.add(new Item("Energy", 16, 25));
            //items.add(new Item("Mule", 25, ));
        }
        else {
            Main.items.add(new Item("Food", 8, 30));
            Main.items.add(new Item("Energy", 8, 25));
            Main.items.add(new Item("Smithore", 8, 50));
            //Mule.add(new Item("Mule", 14));
        }
    }
}
