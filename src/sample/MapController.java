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
        playerEnergy.textProperty().set(Main.playerArray.get(0).getSpecificItem(1).getName() + ": " + Integer.toString(Main.playerArray.get(0).getSpecificItem(0).getAmount()));
        playerFood.textProperty().set(Main.playerArray.get(0).getSpecificItem(0).getName() + ": " + Integer.toString(Main.playerArray.get(0).getSpecificItem(0).getAmount()));
        roundNumber.textProperty().set("Round " + Integer.toString(Main.roundNumber));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        timerCount.textProperty().set(Main.strTime);
        playerMoney.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
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
                    System.out.println(strTimeFinal + " | " + Main.timeRemain);
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
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 300);
            passBut.setDisable(false);
            startBut.setDisable(true);
            Main.bought = false;
        } else if (!Main.finishGame) {
            Main.roundNumber++;
            roundNumber.textProperty().set("Round " + Integer.toString(Main.roundNumber));
            Main.timeRemain = Main.calculateTurnTime();
            Main.timer = new Timer();
            Main.finishTurn = false;
            System.out.println(Main.round);
            System.out.println("Before Timer");
            while (startTimer()) {
                System.out.println("Inside timer");
            }

            System.out.println("After Timer");
            Main.newPlayerTurn();
        }
    }

    @FXML
    public void endTurn() {
        if (Main.playerTurn < Main.players - 1) {
            System.out.println("THIS IS EXECUTED");
            System.out.println(Main.playerTurn + " " + Main.players);
            Main.playerTurn++;
        } else {
            Main.playerTurn = 0;
            Main.roundNumber++;
            roundNumber.textProperty().set("Round " + Integer.toString(Main.roundNumber));

        }
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoney.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
        playerName.textProperty().set(Main.getCurrentPlayer().getName());
        Main.timeRemain = 10;
        startBut.setDisable(true);

    }

    @FXML
    private void buyLand(MouseEvent event) {
        if (Main.placeFood) {
            Button bb = (Button) event.getSource();
            bb.setDisable(false);
            bb.setOpacity(1);
            Image image = new Image(getClass().getResourceAsStream("town.png"));
            bb.setGraphic(new ImageView(image));
            System.out.println("I'm here");
            bb.setDisable(true);
        }
        if (!Main.bought && Main.started) {
            playerMoney.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
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
            butt.setDisable(true);
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
            System.out.println("DNA");
            Main.finishBuyingRound = true;
        } else if (Main.getCurrentPlayer().equals(Main.playerArray.get(Main
                .players - 1)) && Main.numPasses < Main.players - 1) {
            System.out.println("FUCK");
            Main.numPasses = 0;
        } else {
            System.out.println("WEEE");
            Main.numPasses++;
        }
        startBut.setDisable(false);
        Main.newPlayerSetupTurn();
    }
}