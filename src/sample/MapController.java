package sample;
import javafx.scene.control.Button;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController{

    @FXML
    private Button passBut;

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

    @FXML
    private void startTurn() {
        Main.bought = false;
        Main.started = true;
        passBut.setDisable(false);
        System.out.println(Main.playerArray.get(Main.playerStart));
        if(Main.playerStart < Main.players - 1) {
            Main.playerStart++;
        } else {
            Main.playerStart = 0;
        }
    }

    @FXML
    private void buyLand(MouseEvent event) {
        if(!Main.bought && Main.started) {

            Button butt = (Button) event.getSource();
            butt.setStyle("-fx-background-color:" + Main.gameConfiguration
                    .getCurrentPlayer().getColor());
            butt.setOpacity(.5);
            String name = butt.getId();
            System.out.println(name);
            int xPos = GridPane.getColumnIndex(butt);
            int yPos = GridPane.getRowIndex(butt) - 1;
            Tile tile = new Tile(name, xPos, yPos);
            System.out.println(tile);
            Main.gameConfiguration.getCurrentPlayer().addProperty(tile);

            if (Main.playerTurn < Main.players - 1) {
                Main.playerTurn++;
            } else {
                Main.playerTurn = 0;
            }
            butt.setDisable(true);
            passBut.setDisable(true);
            Main.bought = true;
        } else {
            return;
        }
    }

    @FXML
    private void pass() {
        passBut.setDisable(true);
        Main.bought = true;
        if(Main.playerTurn < Main.players - 1) {
            Main.playerTurn++;
        } else {
            Main.playerTurn = 0;
        }
    }
}