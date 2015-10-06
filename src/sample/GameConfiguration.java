package sample;


/**
 * Created by nico on 9/30/15.
 */
public class GameConfiguration {
    private int[] foodRequirement = {3,3,3,4,4,4,4,5,5,5,5,0};
    private int timeLeft;

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


    public Player getCurrentPlayer() {
        return Main.playerArray.get(Main.playerTurn);
    }
    public void newPlayerTurn() {
        long currentTime = System.currentTimeMillis();
        long endTime = 50*1000L;
        while (currentTime < endTime) {
            this.timeLeft = (int) (endTime - currentTime);
            Main.currentPlayer = getCurrentPlayer();
            currentTime = System.currentTimeMillis();
        }
        if (Main.playerTurn < Main.players - 1) {
            Main.playerTurn++;
        } else {
            newRound();
            Main.playerTurn = 0;
        }
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void newRound() {
        Main.round++;
        for (int i = 0; i < Main.players; i++) {
            int money = Main.playerArray.get(i).getMoney();
            int numLand = Main.playerArray.get(i).numTiles() * 500;
            int valueOfGoods = Main.playerArray.get(i).valueOfGoods();
            Main.playerArray.get(i).setScore(money + numLand + valueOfGoods);
        }
    }
    public void selectionRound() {
//        for (Player p : Main.playerArray) {
//            Main.currentPlayer = p;
//            MapController.buyLand();
//        }
    }

}
