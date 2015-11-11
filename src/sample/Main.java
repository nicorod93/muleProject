package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;



public class Main extends Application {
    public static String difficulty;
    public static String mapType;
    public static String strTime = "" + Main.timeRemain;

    public static int players = 0;
    public static long counter = 1;
    public static int playerTurn = 0;
    public static int playerStart = 0;
    public static int round = 0;
    public static long numSelectionRounds = 0;
    public static long numPasses = 0;
    public static long food = 16;
    public static long energy = 16;
    public static long timeRemain;

    public static List<String> playerRace = new ArrayList<>();
    public static List<String> playerColor = new ArrayList<>();
    public static List<String> playerName = new ArrayList<>();
    public static List<Player> playerArray = new ArrayList<>();
    public static List<Item> items;

    public static long[] foodRequirement = {3,3,3,3,4,4,4,4,5,5,5,5};

    public static Tile[][] tileMap = new Tile[9][5];

    public static Player currentPlayer;

    public static boolean bought = false;
    public static boolean started = false;
    public static boolean finishBuyingRound = false;
    public static boolean finishGame = false;
    public static boolean finishTurn;
    public static boolean ownedTile = false;

    public static Timer timer;

    public static Scene map;

    public static boolean placeFood = false;
    public static boolean placeEnergy = false;
    public static boolean placeOre = false;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLHomeScreen.fxml")); //main screen is home screen
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static void initializePlayers() {
        for (int i = 0; i < players; i++) {
            playerArray.add(new Player(playerName.get(i), playerColor.get(i),
                    playerRace.get(i)));
        }
    }

    public static void setPlayersMoney() {
        for (Player p : playerArray) {
            if (p.getRace().equals("Flapper")) {
                p.setMoney(1600);
            } else if (p.getRace().equals("Human")) {
                p.setMoney(600);
            } else if (p.getRace().equals("Other")) {
                p.setMoney(1000);
            }
        }
    }

    public static void setPlayersItems() {
        if (difficulty.equals("Beginner")) {
            playerArray.stream().forEach(player -> player.addItem(new Item
                    ("Food", 8, 30)));
            playerArray.stream().forEach(player -> player.addItem(new Item("Energy",
                    4, 25)));
            playerArray.stream().forEach(player -> player.addItem(new Item("Smithore",
                    0, 50)));
            playerArray.stream().forEach(player -> player.addItem(new Item("Crystite",
                    0, 100)));
        }
    }

    public static Player getCurrentPlayer() {
        return playerArray.get(playerTurn);
    }

    public static void newPlayerSetupTurn() {
        if (playerTurn < players - 1) {
            playerTurn++;
        } else {
            playerTurn = 0;
        }
    }

    public static void newPlayerTurn() {
        if (playerTurn < players - 1) {
            playerTurn++;
        } else {
            playerTurn = 0;
            newRound();
        }
        timeRemain = calculateTurnTime();
        //doProduction();
    }

//    public static void doProduction() {
//        for (int i = 0; i < tileMap.length; i++) {
//            for (Tile t : tileMap[i]) {
//                if (t != null) {
//                    System.out.println(getCurrentPlayer().getNumMules());
//                    System.out.println(getCurrentPlayer().getSpecificItem(1).getAmount());
//                    if (getCurrentPlayer().getNumMules() <= getCurrentPlayer().getSpecificItem(1).getAmount()) {
//                        System.out.println("Attempt Production");
//                        Item calculatedItem = calcProduction(t);
//                        System.out.println(calculatedItem);
//                        if (calculatedItem != null) {
//                            System.out.println("Do Production");
//                            System.out.println("Tile produced" + calculatedItem);
//                            getCurrentPlayer().addItem(calculatedItem);
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static void newRound() {
        round++;
        calculateScore();
        playerArray.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getScore() < o2.getScore()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        if (round == 12) {
            finishGame = true;
        }
    }

    public static void selectionRound() {
        if (playerArray.get(0).numTiles() == 2) {
            System.out.println("Selection round over");
            numSelectionRounds++;
        } else {
            numSelectionRounds++;
        }
    }

    public static void buyingRound() {
        if (numPasses == players) {
            finishBuyingRound = true;
        }
    }

    public static void calculateScore() {
        for (int i = 0; i < players; i++) {
            long money = playerArray.get(i).getMoney();
            long numLand = playerArray.get(i).numTiles() * 500;
            long valueOfGoods = playerArray.get(i).valueOfGoods();
            long total = money + numLand + valueOfGoods;
            playerArray.get(i).setScore(total);
        }
    }


    public static long calculateTurnTime() {
        long numFood = 0;
        for (Item i : Main.getCurrentPlayer().getItems()) {
            if (i.getName().equals("Food")) {
                numFood += i.getAmount();
            }
        }
        if (numFood > 0 && numFood < foodRequirement[round]) {
            timeRemain = 30;
        } else if (numFood == 0) {
            timeRemain = 5;
        } else {
            timeRemain = 50;
        }
        return timeRemain;
    }

    public static boolean checkPlayerTiles(Tile t) {
        for (Player p : playerArray) {
            for (Tile tile : p.getTiles()) {
                if (tile.equals(t)) {
                    ownedTile = true;
                }
            }
        }
        return ownedTile;
    }

    public static Item calcProduction(Tile tile) {
        if (tile.getName().equals("River")) {
            if (tile.getMule() != null) {
                System.out.println("River Mule");
                if (tile.getMule() instanceof EnergyMule) {
                    return new Item("Energy", 2, 25);
                } else if (tile.getMule() instanceof FoodMule) {
                    return new Item("Food", 4, 25);
                }
            }
        } else if (tile.getName().equals("Plain")) {
            if (tile.getMule() != null) {
                System.out.println("Plain Mule");
                if (tile.getMule() instanceof EnergyMule) {
                    return new Item("Energy", 3, 25);
                } else if (tile.getMule() instanceof FoodMule) {
                    return new Item("Food", 2, 25);
                }
            }
        } else if (tile.getName().equals("Mountain1")) {
            if (tile.getMule() != null) {
                System.out.println("M1 Mule");
                if (tile.getMule() instanceof EnergyMule) {
                    return new Item("Energy", 1, 25);
                } else if (tile.getMule() instanceof FoodMule) {
                    return new Item("Food", 1, 25);
                }
            }
        } else if (tile.getName().equals("Mountain2")) {
            if (tile.getMule() != null) {
                System.out.println("M2 Mule");
                if (tile.getMule() instanceof EnergyMule) {
                    return new Item("Energy", 1, 25);
                } else if (tile.getMule() instanceof FoodMule) {
                    return new Item("Food", 1, 25);
                }
            }
        } else if (tile.getName().equals("Mountain3")) {
            if (tile.getMule() != null) {
                System.out.println("M3 Mule");
                if (tile.getMule() instanceof EnergyMule) {
                    return new Item("Energy", 1, 25);
                } else if (tile.getMule() instanceof FoodMule) {
                    return new Item("Food", 1, 25);
                }
            }
        }
        return null;
    }





    public static void main (String[] args) throws Exception {
        Main mainGame = new Main();
        launch(args);
    }
}
