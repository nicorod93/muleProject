package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by Gordon on 10/1/2015.
 */
public class townController {
    @FXML
    private void goToStore(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("store.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Store");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @FXML
    private void goToLandOffice(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("landOffice.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Land Office");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @FXML
    private void goToPub(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("pub.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Pub");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }
}
