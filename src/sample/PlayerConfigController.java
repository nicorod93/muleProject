package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerConfigController implements Initializable {

    @FXML
    private ChoiceBox<String> races;

    @FXML
    private ColorPicker color;

    @FXML
    private TextField plname;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        races.setItems(FXCollections.observableArrayList());
        races.getItems().addAll("Human", "Flapper", "Other");
    }

    @FXML
    private void playerConfirm(MouseEvent event) {
        Main.playerRace.add(races.getValue());
        Main.playerColor.add(color.getValue());
        Main.playerName.add(plname.getText());
        System.out.println("Player names: " + Main.playerName + " Player Races: " + Main.playerRace + " Player Colors: " + Main.playerColor);
        try {
            if (Main.counter < Main.players) {
                Scene town = new Scene(FXMLLoader.load(getClass().getResource("playerConfig.fxml")));
                Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
                t.setScene(town);
                t.setTitle("Player Configuration");
                t.show();
                Main.counter++;
            } else {
                Scene town = new Scene(FXMLLoader.load(getClass().getResource("map.fxml")));
                Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
                t.setScene(town);
                t.setTitle("Map");
                t.show();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }
}
