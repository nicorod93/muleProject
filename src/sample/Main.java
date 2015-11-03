package sample;

import com.sun.tools.javac.jvm.Items;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.*;

import java.io.File;
import java.io.PrintWriter;

import java.io.FileWriter;
import java.io.IOException;


import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Main extends Application {
    public static String difficulty;
    public static String mapType;
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
    public static int timeRemain;

    public static List<String> playerRace = new ArrayList<>();
    public static List<String> playerColor = new ArrayList<>();
    public static List<String> playerName = new ArrayList<>();
    public static List<Player> playerArray = new ArrayList<>();
    public static List<Item> items;

    public static int[] foodRequirement = {3,3,3,3,4,4,4,4,5,5,5,5};

    public static Tile[][] tileMap = new Tile[9][5];

    public static String[] variables = {"difficulty",
            "mapType",
            "strTime",
            "players",
            "counter",
            "playerTurn",
            "playerStart",
            "round",
            "numSelectionRounds",
            "numPasses",
            "food",
            "energy",
            "timeRemain",
            "playerRace",
            "playerColor",
            "playerName",
            "playerArray",
            "items",
            "currentPlayer",
            "bought",
            "started",
            "finishBuyingRound",
            "finishGame",
            "finishTurn",
            "ownedTile",
            "timer",
            "map",
            "placeFood",
            "placeEnergy",
            "placeOre",
            "placeFood",
            "placeEnergy",
            "placeOre"};

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
        for (int i = 0; i < Main.players; i++) {
            int money = Main.playerArray.get(i).getMoney();
            int numLand = Main.playerArray.get(i).numTiles() * 500;
            int valueOfGoods = Main.playerArray.get(i).valueOfGoods();
            Main.playerArray.get(i).setScore(money + numLand + valueOfGoods);
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

    public static void fileSave() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("difficulty", difficulty);
        obj.put("mapType ", mapType);
        obj.put("strTime ", strTime);
        obj.put("players ", players);
        obj.put("counter ", counter);
        obj.put("playerTurn ", playerTurn);
        obj.put("playerStart ", playerStart);
        obj.put("round ", round);
        obj.put("numSelectionRounds ", numSelectionRounds);
        obj.put("numPasses ", numPasses);
        obj.put("food ", food);
        obj.put("energy ", energy);
        obj.put("timeRemain ", timeRemain);
        obj.put("playerRace ", playerRace);
        obj.put("playerColor ", playerColor);
        obj.put("playerName) ", playerName);
        obj.put("playerArray ", playerArray);
        obj.put("items ", items);
        obj.put("currentPlayer ", currentPlayer);
        obj.put("bought ", bought);
        obj.put("started ", started);
        obj.put("finishBuyingRound ", finishBuyingRound);
        obj.put("finishGame ", finishGame);
        obj.put("finishTurn ", finishTurn);
        obj.put("ownedTile ", ownedTile);
        obj.put("timer ", timer);
        obj.put("map ", map);
        obj.put("placeFood ", placeFood);
        obj.put("placeEnergy ", placeEnergy);
        obj.put("placeOre ", placeOre);
        obj.put("placeFood ", placeFood);
        obj.put("placeEnergy ", placeEnergy);
        obj.put("placeOre ", placeOre);

        try {
            FileWriter file = new FileWriter("fileSave.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileLoad() throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("fileSave.json");
            Object obj = (JSONObject) parser.parse(fileReader);

            JSONObject jsonObject = (JSONObject) obj;

            difficulty = (String) jsonObject.get("difficulty");
            mapType = (String) jsonObject.get("mapType");
            strTime = (String) jsonObject.get("strTime");
            players = (int) jsonObject.get("players");
            counter = (int) jsonObject.get("counter");
            playerTurn = (int) jsonObject.get("playerTurn");
            playerStart = (int) jsonObject.get("playerStart");
            round = (int) jsonObject.get("round");
            numSelectionRounds = (int) jsonObject.get("numSelectionRounds");
            numPasses = (int) jsonObject.get("numPasses");
            food = (int) jsonObject.get("food");
            energy = (int) jsonObject.get("energy");
            timeRemain = (int) jsonObject.get("timeRemain");
            playerRace = (List<String>) jsonObject.get("playerRace");
            playerColor = (List<String>) jsonObject.get("playerColor");
            playerName = (List<String>) jsonObject.get("playerName");
            playerArray = (List<Player>) jsonObject.get("playerArray");
            items = (List<Item>) jsonObject.get("items");
            currentPlayer = (Player) jsonObject.get("currentPlayer");
            bought = (boolean) jsonObject.get("bought");
            started = (boolean) jsonObject.get("started");
            finishBuyingRound = (boolean) jsonObject.get("finishBuyingRound");
            finishGame = (boolean) jsonObject.get("finishGame");
            finishTurn = (boolean) jsonObject.get("finishTurn");
            ownedTile = (boolean) jsonObject.get("ownedTile");
            timer = (Timer) jsonObject.get("timer");
            map = (Scene) jsonObject.get("map");
            placeFood = (boolean) jsonObject.get("placeFood");
            placeEnergy = (boolean) jsonObject.get("placeEnergy");
            placeOre = (boolean) jsonObject.get("placeOre");
            placeFood = (boolean) jsonObject.get("placeFood");
            placeEnergy = (boolean) jsonObject.get("placeEnergy");
            placeOre = (boolean) jsonObject.get("placeOre");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void main (String[] args) {
        Main main = new Main();
        launch(args);
    }
}
