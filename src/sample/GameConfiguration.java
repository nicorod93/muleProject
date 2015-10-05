package sample;

import java.util.Timer;

/**
 * Created by nico on 9/30/15.
 */
public class GameConfiguration {
    public Timer timer = new Timer();
    private int[] foodRequirement = {3,3,3,4,4,4,4,5,5,5,5,0};

    public void initializePlayers() {
        for (int i = 0; i < Main.players; i++) {
            Main.playerArray.add(new Player(Main.playerName.get(i), Main
                    .playerColor.get(i), Main.playerRace.get(i)));
            if (Main.playerRace.get(i).equals("Flapper")) {
                Main.playerArray.get(i).setMoney(1600);
            } else if (Main.playerRace.get(i).equals("Human")) {
                Main.playerArray.get(i).setMoney(600);
            } else if (Main.playerRace.get(i).equals("Other")) {
                Main.playerArray.get(i).setMoney(1000);
            }
            if (Main.difficulty.equals("Beginner")) {
                Main.playerArray.get(i).addItem(new Item("Food", 8, 30));
                Main.playerArray.get(i).addItem(new Item
                        ("Energy", 4, 25));

            }
        }
    }

    public void takeTurn() {

    }

}
