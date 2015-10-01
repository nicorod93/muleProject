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
import java.net.URL;
import java.util.ResourceBundle;

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
}
