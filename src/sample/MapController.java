package sample;

import javafx.application.Platform;
import javafx.scene.control.Button;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;

import java.awt.Color;

public class MapController implements Initializable {



    @FXML
    private Label roundNumber;

    @FXML
    private Label timerCount;

    @FXML
    private Label playerMoney;

    @FXML
    private Label playerEnergy;

    @FXML
    private Label playerFood;

    @FXML
    private Button passBut;

    @FXML
    private Button startBut;

    @FXML
    private Button endBut;

    @FXML
    private Label playerName;

    @FXML
    private void goToTown(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("town.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Player Configuration");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        playerEnergy.textProperty().set(Main.playerArray.get(0).getSpecificItem(1).getName() + ": " + Long.toString(Main.playerArray.get(0).getSpecificItem(0).getAmount()));
        playerFood.textProperty().set(Main.playerArray.get(0).getSpecificItem(0).getName() + ": " + Long.toString(Main.playerArray.get(0).getSpecificItem(0).getAmount()));
        roundNumber.textProperty().set("Round " + Long.toString(Main.round));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        timerCount.textProperty().set(Main.strTime);
        playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer
                ().getMoney()));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
    }

    @FXML
    public boolean startTimer() {
        Main.playerTurn++;
        startBut.setDisable(true);
        Main.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String strTimeFinal = "" + Main.timeRemain;
                    //System.out.println(strTimeFinal + " | " + Main.timeRemain);
                    timerCount.textProperty().set(strTimeFinal);
                    Main.timeRemain--;
                    if (Main.timeRemain == -1) {
                        Main.bought = true;
                        Main.finishTurn = true;
                        endTurn();
                    }
                });
            }
        }, 0, 1000);
        return Main.finishTurn;
    }

    @FXML
    public void startTurn() {
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer
                ().getMoney()));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        Main.getCurrentPlayer().calculateScore();
        System.out.println(Main.getCurrentPlayer().getScore());
        randomEvent a = new randomEvent(Main.getCurrentPlayer(), Main.round, Main.playerArray);
        if (Main.numSelectionRounds < Main.players * 2) {
            System.out.println(Main.numSelectionRounds);
            Main.bought = false;
            Main.started = true;
            System.out.println(Main.playerArray.get(Main.playerStart));
            if (Main.playerStart < Main.players - 1) {
                Main.playerStart++;
            } else {
                Main.playerStart = 0;
            }
            Main.selectionRound();
        } else if (!Main.finishBuyingRound) {
            System.out.println("Executed");
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 300);
            passBut.setDisable(false);
            startBut.setDisable(true);
            Main.bought = false;
        } else if (!Main.finishGame) {
            roundNumber.textProperty().set("Round " + Long.toString(Main
                    .round));
            Main.timeRemain = Main.calculateTurnTime();
            Main.timer = new Timer();
            Main.finishTurn = false;
            System.out.println(Main.round);
            //System.out.println("Before Timer");
            while (startTimer()) {
                //System.out.println("Inside timer");
            }

            //System.out.println("After Timer");
            Main.newPlayerTurn();
        }
    }

    @FXML
    public void endTurn() {
        Main.getCurrentPlayer().calculateScore();
        System.out.println("Your Score: " + Main.getCurrentPlayer().getScore());
        randomEvent a = new randomEvent(Main.getCurrentPlayer(), Main.round, Main.playerArray);
        playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer
                ().getMoney()));
        playerEnergy.textProperty().set("Energy: " + Long.toString(Main
                .getCurrentPlayer().getSpecificItem(1).getAmount()));
        playerFood.textProperty().set("Food: " + Long.toString(Main
                .getCurrentPlayer().getSpecificItem(0).getAmount()));
        if (Main.playerTurn < Main.players - 1) {
//            System.out.println("THIS IS EXECUTED");
//            System.out.println(Main.playerTurn + " " + Main.players);
            Main.playerTurn++;
        } else {
            Main.playerTurn = 0;
            roundNumber.textProperty().set("Round " + Long.toString(Main
                    .round));

        }
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer
                ().getMoney()));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        Main.getCurrentPlayer().calcProduction();
        playerEnergy.textProperty().set("Energy: " + Long.toString(Main
                .getCurrentPlayer().getSpecificItem(1).getAmount()));
        playerFood.textProperty().set("Food: " + Long.toString(Main
                .getCurrentPlayer().getSpecificItem(0).getAmount()));

        Main.timeRemain = 50;
        startBut.setDisable(true);

    }

    @FXML
    private void buyLand(MouseEvent event) {
        if (Main.placeFood) {
            Button bb = (Button) event.getSource();
            String a = bb.getStyle();
            String b = "-fx-background-color:" + Main.getCurrentPlayer().getColor();
            if (a.equals(b)) {
                bb.setDisable(false);
                bb.setOpacity(1);
                Image image = new Image(getClass().getResourceAsStream("ward.png"));
                bb.setGraphic(new ImageView(image));
                bb.setStyle("-fx-background-color:" + Main.getCurrentPlayer().getColor());
                bb.setDisable(true);
                Main.getCurrentPlayer().addFMule();
                Main.placeFood = false;
                playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
                playerEnergy.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
                playerFood.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
            }
            else {
                System.out.println("Placed Mule Wrong! Mule is lost");
                Main.placeFood = false;
                playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
                playerEnergy.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
                playerFood.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
            }
        }
        if (Main.placeEnergy) {
            Button bb = (Button) event.getSource();
            String a = bb.getStyle();
            String b = "-fx-background-color:" + Main.getCurrentPlayer().getColor();
            if (a.equals(b)) {
                bb.setDisable(false);
                bb.setOpacity(1);
                Image image = new Image(getClass().getResourceAsStream("energyward.png"));
                bb.setGraphic(new ImageView(image));
                bb.setStyle("-fx-background-color:" + Main.getCurrentPlayer().getColor());
                bb.setDisable(true);
                Main.getCurrentPlayer().addEMule();
                Main.placeEnergy = false;
                playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
                playerEnergy.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
                playerFood.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
            }
            else {
                System.out.println("Placed Mule Wrong! Mule is lost");
                Main.placeEnergy = false;
                playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
                playerEnergy.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
                playerFood.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
            }
        }
        if (Main.placeOre) {
            Button bb = (Button) event.getSource();
            String a = bb.getStyle();
            String b = "-fx-background-color:" + Main.getCurrentPlayer().getColor();
            if (a.equals(b)) {
                bb.setDisable(false);
                bb.setOpacity(1);
                Image image = new Image(getClass().getResourceAsStream("oreward.png"));
                bb.setGraphic(new ImageView(image));
                bb.setStyle("-fx-background-color:" + Main.getCurrentPlayer().getColor());
                bb.setDisable(true);
                Main.getCurrentPlayer().addOMule();
                Main.placeOre = false;
                playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
                playerEnergy.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
                playerFood.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
            }
            else {
                System.out.println("Placed Mule Wrong! Mule is lost");
                Main.placeOre = false;
                playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
                playerEnergy.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
                playerFood.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
            }
        }
        if (!Main.bought && Main.started) {
            playerMoney.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
            Button butt = (Button) event.getSource();
            butt.setStyle("-fx-background-color:" + Main
                    .getCurrentPlayer().getColor());
            butt.setOpacity(.5);

            String name = butt.getId();
            System.out.println(name);
            int xPos = GridPane.getColumnIndex(butt);
            int yPos = GridPane.getRowIndex(butt) - 1;
            Tile tile = new Tile(name, xPos, yPos);
            System.out.println(tile);
            Main.getCurrentPlayer().addProperty(tile);
           // butt.setDisable(true);
            passBut.setDisable(true);
            Main.bought = true;
            Main.newPlayerSetupTurn();


        } else {
            return;
        }
    }

    @FXML
    private void pass() {
        passBut.setDisable(true);
        Main.bought = true;
        System.out.println(Main.getCurrentPlayer());
        System.out.println(Main.playerTurn);
        System.out.println(Main.playerArray.get(Main.players - 1));
        if (Main.getCurrentPlayer().equals(Main.playerArray.get(Main.players - 1))
                && Main.numPasses == Main.players - 1) {
            //System.out.println("DNA");
            Main.finishBuyingRound = true;
        } else if (Main.getCurrentPlayer().equals(Main.playerArray.get(Main
                .players - 1)) && Main.numPasses < Main.players - 1) {
            Main.numPasses = 0;
        } else {
            //System.out.println("WEEE");
            Main.numPasses++;
        }
        startBut.setDisable(false);
        Main.newPlayerSetupTurn();
    }

    @FXML
    private void fileSave() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("difficulty", Main.difficulty);
        obj.put("mapType", Main.mapType);
        obj.put("strTime", Main.strTime);
        obj.put("players", Main.players);
        obj.put("counter", Main.counter);
        obj.put("playerTurn", Main.playerTurn);
        obj.put("playerStart", Main.playerStart);
        obj.put("round", Main.round);
        obj.put("numSelectionRounds", Main.numSelectionRounds);
        obj.put("numPasses", Main.numPasses);
        obj.put("food", Main.food);
        obj.put("energy", Main.energy);
        obj.put("timeRemain", Main.timeRemain);
        obj.put("playerRace", Main.playerRace);
        obj.put("playerColor", Main.playerColor);
        obj.put("playerName", Main.playerName);
        obj.put("playerArray", Main.playerArray);
        obj.put("items", Main.items);
        obj.put("currentPlayer", Main.currentPlayer);
        obj.put("bought", Main.bought);
        obj.put("started", Main.started);
        obj.put("finishBuyingRound", Main.finishBuyingRound);
        obj.put("finishGame", Main.finishGame);
        obj.put("finishTurn", Main.finishTurn);
        obj.put("ownedTile", Main.ownedTile);
        obj.put("timer", Main.timer);
        obj.put("map", Main.map);
        obj.put("placeFood", Main.placeFood);
        obj.put("placeEnergy", Main.placeEnergy);
        obj.put("placeOre", Main.placeOre);
        System.out.println("Saved");
        try {
            FileWriter file = new FileWriter("fileSave.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}