package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import org.apache.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerConfigController implements Initializable {
    @FXML
    private ChoiceBox<String> races;
    private static org.apache.log4j.Logger log = LogManager.getRootLogger();
    @FXML
    private ChoiceBox<String> color;

    @FXML
    private TextField plname;

    @Override
    public final void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        races.setItems(FXCollections.observableArrayList());
        races.getItems().addAll("Human", "Flapper", "Other");
        color.setItems(FXCollections.observableArrayList());
        color.getItems().addAll("Red", "Blue", "Orange", "Purple");
    }

    @FXML
    private void playerConfirm(MouseEvent event) {
        Main.playerRace.add(races.getValue());
        Main.playerColor.add(color.getValue());
        Main.playerName.add(plname.getText());
        try {
            if (Main.counter < Main.players) {
                Scene town = new Scene(FXMLLoader.load(getClass().getResource("playerConfig.fxml")));
                Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
                t.setScene(town);
                t.setTitle("Player Configuration");
                t.show();
                Main.counter++;
            } else {
                Main.initializePlayers();
                Main.setPlayersItems();
                Main.setPlayersMoney();
                Main.map = new Scene(FXMLLoader.load(getClass().getResource("map.fxml")));
                Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
                t.setScene(Main.map);
                t.setTitle("Map");
                t.show();
            }
        } catch (Exception e) {
            log.error("File Not Found", e);        }
    }
}
