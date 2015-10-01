package sample;

/**
 * Created by nico on 9/30/15.
 */
public class GameConfiguration {

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
                for (int j = 0; j < 8; j++) {
                    Main.playerArray.get(i).addItem(new Item("Food", 30));
                }
                for (int m = 0; m < 4; m++) {
                    Main.playerArray.get(i).addItem(new Item
                            ("Energy", 25));
                }
            }
        }
    }

}
