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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        playerEnergy.textProperty().set(Main.playerArray.get(0)
                .getSpecificItem(1).getName() + ": " + Integer.toString(Main.playerArray.get(0).getSpecificItem(1).getAmount()));
        playerFood.textProperty().set(Main.playerArray.get(0).getSpecificItem(0).getName() + ": " + Integer.toString(Main.playerArray.get(0).getSpecificItem(0).getAmount()));
        roundNumber.textProperty().set("Round " + Integer.toString(Main.round));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        timerCount.textProperty().set(Main.strTime);
        playerMoney.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
    }

    @FXML
    public boolean startTimer() {
        //Main.playerTurn++;
        startBut.setDisable(true);
        Main.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String strTimeFinal = "" + Main.timeRemain;
                    timerCount.textProperty().set(strTimeFinal);
                    Main.timeRemain--;
                    if (Main.timeRemain == -1) {
                        Main.bought = true;
                        Main.finishTurn = true;
                        startBut.setDisable(false);
                        Main.timer.cancel();
                        //endTurn();
                    }
                });
            }
        }, 0, 1000);
        return Main.finishTurn;
    }

    @FXML
    public void startTurn() {
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoney.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
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
            //Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer()
                    //.getMoney() - 300);
            passBut.setDisable(false);
            Main.bought = false;
        } else if (!Main.finishGame) {
            roundNumber.textProperty().set("Round " + Integer.toString(Main
                    .round));
            Main.timeRemain = Main.calculateTurnTime();
            Main.timer = new Timer();
            Main.finishTurn = false;
            System.out.println(Main.round);
            while (startTimer()) {
            }
            Main.newPlayerTurn();
        }
    }

    @FXML
    public void endTurn() {
        Main.newPlayerTurn();
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoney.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
        //startBut.setDisable(true);
    }

    @FXML
    private void buyLand(MouseEvent event) {
        Button landButton = (Button) event.getSource();
        landButton.setDisable(false);
        if (Main.placeFood) {
            System.out.println("Food Mule");
            String a = landButton.getStyle();
            String b = "-fx-background-color:" + Main.getCurrentPlayer().getColor();
            if (a.equals(b)) {
                Main.getCurrentPlayer().increaseMules();
                String name = landButton.getId();
                int xPos = GridPane.getColumnIndex(landButton);
                int yPos = GridPane.getRowIndex(landButton);
                Tile t = Main.getCurrentPlayer().getTileAt(name, xPos, yPos);
                if (t != null) {
                    t.addMule(new FoodMule());
                }
                landButton.setDisable(false);
                landButton.setOpacity(.5);
                Image image = new Image(getClass().getResourceAsStream("ward.png"));
                landButton.setGraphic(new ImageView(image));
                landButton.setDisable(true);
                Main.placeFood = false;
            }
            else {
                System.out.println("Placed Mule Wrong! Mule is lost");
                Main.placeFood = false;
            }
        }
        if (Main.placeEnergy) {
            System.out.println("Energy Mule");
            String a = landButton.getStyle();
            String b = "-fx-background-color:" + Main.getCurrentPlayer().getColor();
            if (a.equals(b)) {
                Main.getCurrentPlayer().increaseMules();
                String name = landButton.getId();
                int xPos = GridPane.getColumnIndex(landButton);
                int yPos = GridPane.getRowIndex(landButton);
                Tile t = Main.getCurrentPlayer().getTileAt(name, xPos, yPos);
                if (t != null) {
                    t.addMule(new EnergyMule());
                }
                landButton.setDisable(false);
                landButton.setOpacity(.5);
                Image image = new Image(getClass().getResourceAsStream("energyward.png"));
                landButton.setGraphic(new ImageView(image));
                landButton.setDisable(true);
                Main.placeEnergy = false;
            }
            else {
                System.out.println("Placed Mule Wrong! Mule is lost");
                Main.placeEnergy = false;
            }
        }
        if (Main.placeOre) {
            System.out.println("Ore Mule");
            String a = landButton.getStyle();
            String b = "-fx-background-color:" + Main.getCurrentPlayer().getColor();
            if (a.equals(b)) {
                Main.getCurrentPlayer().increaseMules();
                String name = landButton.getId();
                int xPos = GridPane.getColumnIndex(landButton);
                int yPos = GridPane.getRowIndex(landButton);
                Tile t = Main.getCurrentPlayer().getTileAt(name, xPos, yPos);
                if (t != null) {
                    t.addMule(new SmithoreMule());
                }
                landButton.setDisable(false);
                landButton.setOpacity(.5);
                Image image = new Image(getClass().getResourceAsStream("oreward.png"));
                landButton.setGraphic(new ImageView(image));
                landButton.setDisable(true);
                Main.placeOre = false;
            }
            else {
                System.out.println("Placed Mule Wrong! Mule is lost");
                Main.placeOre = false;
            }
        }
        if (!Main.bought && Main.started) {
            playerMoney.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
            selectLand(landButton);
            passBut.setDisable(true);
            Main.bought = true;
            Main.newPlayerSetupTurn();
        }
    }

    @FXML
    private void pass() {
        passBut.setDisable(true);
        Main.bought = true;
        if (Main.getCurrentPlayer().equals(Main.playerArray.get(Main.players - 1))
                && Main.numPasses == Main.players - 1) {
            Main.finishBuyingRound = true;
        } else if (Main.getCurrentPlayer().equals(Main.playerArray.get(Main
                .players - 1)) && Main.numPasses < Main.players - 1) {
            Main.numPasses = 0;
        } else {
            Main.numPasses++;
        }
        startBut.setDisable(false);
        Main.newPlayerSetupTurn();
    }

    private void selectLand(Button button) {
        String name = button.getId();
        int xPos = GridPane.getColumnIndex(button);
        int yPos = GridPane.getRowIndex(button);
        Tile tile = new Tile(name, xPos, yPos);
        if (!Main.checkPlayerTiles(tile)) {
            System.out.println("New Tile");
            button.setStyle("-fx-background-color:" + Main
                    .getCurrentPlayer().getColor());
            button.setOpacity(.5);
            Main.getCurrentPlayer().addProperty(tile);
        } else {
            System.out.println("Oops that land is already owned!");
        }
        //button.setDisable(true);
    }
}