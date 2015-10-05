package sample;

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
    private void buyLand(MouseEvent event) {
        Button butt = (Button) event.getSource();
        butt.setStyle("-fx-background-color:" + Main.gameConfiguration
                .getCurrentPlayer().getColor());
        butt.setOpacity(.5);
        String name = butt.getId();
        System.out.println(name);
        int x = (int) butt.getLayoutX();
        int y = (int) butt.getLayoutY();
        if (x >= 0 && x < 74) {
            x = 0;
        } else if (x >= 74 && x < 148) {
            x = 1;
        } else if (x >= 148 && x < 222) {
            x = 2;
        } else if (x >= 222 && x < 296) {
            x = 3;
        } else if (x >= 296 && x < 370) {
            x = 4;
        } else if (x >= 370 && x < 444) {
            x = 5;
        } else if (x >= 444 && x < 518) {
            x = 6;
        } else if (x >= 518 && x < 592) {
            x = 7;
        } else if (x >= 592 && x < 666) {
            x = 8;
        }
        if (y >= 0 && y < 80) {
            y = 0;
        } else if (y >= 80 && y < 160) {
            y = 1;
        } else if (y >= 160 && y < 240) {
            y = 2;
        } else if (y >= 240 && y < 300) {
            y = 3;
        } else if (y >= 300 && y < 360) {
            y = 4;
        }
        Tile tile = new Tile(name, x, y);
        System.out.println(tile);
        Main.gameConfiguration.getCurrentPlayer().addProperty(tile);
        if(Main.playerTurn < Main.players - 1) {
            Main.playerTurn++;
        } else {
            Main.playerTurn = 0;
        }
        butt.setDisable(true);
    }
}