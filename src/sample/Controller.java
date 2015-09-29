package sample;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private ChoiceBox<String> difficulty;

    @FXML
    private ChoiceBox<String> mapType;
    
    @FXML
    private ChoiceBox<Integer> numPlayers;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        difficulty.setItems(FXCollections.observableArrayList());
        difficulty.getItems().addAll("Beginner","Standard","Tournament");
        mapType.setItems(FXCollections.observableArrayList());
        mapType.getItems().addAll("Standard");
        numPlayers.setItems(FXCollections.observableArrayList());
        numPlayers.getItems().addAll(2,3,4);
    }
}
