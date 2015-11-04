package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import com.google.gson.Gson;

public class Controller implements Initializable {

    @FXML
    private ChoiceBox<String> difficulty;

    @FXML
    private ChoiceBox<String> mapType;

    @FXML
    private ChoiceBox<Integer> numPlayers;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        difficulty.setItems(FXCollections.observableArrayList());
        difficulty.getItems().addAll("Beginner", "Standard", "Tournament");
        mapType.setItems(FXCollections.observableArrayList());
        mapType.getItems().addAll("Standard");
        numPlayers.setItems(FXCollections.observableArrayList());
        numPlayers.getItems().addAll(2, 3, 4);
    }


    @FXML
    private void confirm(MouseEvent event) {
        try {
            Main.players = numPlayers.getValue();
            Main.difficulty = difficulty.getValue();
            Main.mapType = mapType.getValue();
            System.out.println("Difficulty: " + Main.difficulty);
            System.out.println("Map type: " + Main.mapType);
            System.out.println("Number of Players: " + Main.players);

            Scene town = new Scene(FXMLLoader.load(getClass().getResource("playerConfig.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Player Configuration");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @FXML
    private void fileLoad(MouseEvent event) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("fileSave.json");
            Object obj = parser.parse(fileReader);

            JSONObject jsonObject = (JSONObject) obj;
            //JSONValue jsonValue = (JSONValue) obj;

            BufferedReader br = new BufferedReader(new FileReader("fileSave" +
                    ".json"));

            Main.difficulty = (String) jsonObject.get("difficulty");
            Main.mapType = (String) jsonObject.get("mapType");
            Main.strTime = (String) jsonObject.get("strTime");
            Long player = (long) jsonObject.get("players");
            Main.players = player.intValue();
            Main.counter = (long) jsonObject.get("counter");
            Long playerT = (long) jsonObject.get("playerTurn");
            Main.playerTurn = playerT.intValue();
            Long playerS = (long) jsonObject.get("playerStart");
            Main.playerStart = playerS.intValue();
            Long r = (long) jsonObject.get("round");
            Main.players = r.intValue();
            Main.numSelectionRounds = (long) jsonObject.get("numSelectionRounds");
            Main.numPasses = (long) jsonObject.get("numPasses");
            Main.food = (long) jsonObject.get("food");
            Main.energy = (long) jsonObject.get("energy");
            Main.timeRemain = (long) jsonObject.get("timeRemain");
            Main.playerRace = (List<String>) jsonObject.get("playerRace");
            Main.playerColor = (List<String>) jsonObject.get("playerColor");
            Main.playerName = (List<String>) jsonObject.get("playerName");
            for (int i = 0; i <= Main.players; i++) {
                String playerStr = (String) jsonObject.get("p" + i);
                System.out.println(playerStr);
                int i1 = playerStr.indexOf(":", 7);
                System.out.println(i1);
                int i2 = playerStr.indexOf(",", 9);
                System.out.println(i2);
                String name = playerStr.substring(i1+2, i2-1);
                System.out.println(name);
                int i3 = playerStr.indexOf(":", 22);
                System.out.println(i3);
                int i4 = playerStr.indexOf(",", 24);
                System.out.println(i4);
                String color = playerStr.substring(i3 + 2, i4 - 1);
                System.out.println(color);
                int i5 = playerStr.indexOf(":", 34);
                System.out.println(i5);
                int i6 = playerStr.indexOf(",",36);
                System.out.println(i6);
                String race = playerStr.substring(i5+2, i6-1);
                System.out.println(race);
                Player p = new Player(name, color, race);
                Main.playerArray.add(p);
            }
            //Main.playerArray = (List<Player>) jsonObject.get("playersArray");
            System.out.println(Main.playerArray);
            Main.setPlayersMoney();
            Main.setPlayersItems();
            Main.items = (List<Item>) jsonObject.get("items");
            Main.currentPlayer = (Player) jsonObject.get("currentPlayer");
            Main.bought = (boolean) jsonObject.get("bought");
            Main.started = (boolean) jsonObject.get("started");
            Main.finishBuyingRound = (boolean) jsonObject.get("finishBuyingRound");
            Main.finishGame = (boolean) jsonObject.get("finishGame");
            Main.finishTurn = (boolean) jsonObject.get("finishTurn");
            Main.ownedTile = (boolean) jsonObject.get("ownedTile");
            //Main.timer = gson.fromJson(br, Timer.class);
            //Main.map = gson.fromJson(br, Scene.class);
            Main.placeFood = (boolean) jsonObject.get("placeFood");
            Main.placeEnergy = (boolean) jsonObject.get("placeEnergy");
            Main.placeOre = (boolean) jsonObject.get("placeOre");

            Scene town = new Scene(FXMLLoader.load(getClass().getResource("map.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Player Configuration");
            t.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
