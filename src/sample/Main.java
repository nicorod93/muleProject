package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.Timer;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Main extends Application {
    public static String difficulty;
    public static String mapType;
    public static int timeRemain = 10;
    public static String strTime = "" + Main.timeRemain;


    public static int players = 0;
    public static int counter = 1;
    public static int playerTurn = 0;
    public static int playerStart = 0;
    public static int round = 0;
    public static int numSelectionRounds = 0;
    public static int numPasses = 0;
    public static int food = 16;
    public static int energy = 16;

    public static List<String> playerRace = new ArrayList<>();
    public static List<String> playerColor = new ArrayList<>();
    public static List<String> playerName = new ArrayList<>();
    public static List<Player> playerArray = new ArrayList<>();
    public static List<Item> items;

    public static int[] foodRequirement = {3,3,3,3,4,4,4,4,5,5,5,5};

    public static Player currentPlayer;

    public static boolean bought = false;
    public static boolean started = false;
    public static boolean finishBuyingRound = false;
    public static boolean finishGame = false;
    public static boolean finishTurn;

    public static Timer timer;

    public static Scene map;



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLHomeScreen.fxml")); //main screen is home screen
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
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
            timeRemain = calculateTurnTime();
        } else {
            playerTurn = 0;
            newRound();
        }
    }



    public static void newRound() {
        round++;
        for (int i = 0; i < Main.players; i++) {
            int money = Main.playerArray.get(i).getMoney();
            int numLand = Main.playerArray.get(i).numTiles() * 500;
            int valueOfGoods = Main.playerArray.get(i).valueOfGoods();
            Main.playerArray.get(i).setScore(money + numLand + valueOfGoods);
        }
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

    public static int calculateTurnTime() {
        int numFood = 0;
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
            timeRemain = 10;
        }
        return timeRemain;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
