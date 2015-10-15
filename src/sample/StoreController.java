package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StoreController {

    @FXML
    private void goBuy(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("buyScreen.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Buy Screen");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @FXML
    private void goSell(MouseEvent event) {

    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Player Configuration");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }
}
