package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;

public class StoreController {
    private static org.apache.log4j.Logger log = LogManager.getRootLogger();
    @FXML
    private void goBuy(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("buyScreen.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Buy Screen");
            t.show();
        } catch (Exception e) {
            log.error("File Not Found", e);        }
    }

    @FXML
    private void goSell(MouseEvent event) {
        try {
            Scene town = new Scene(FXMLLoader.load(getClass().getResource("sellScreen.fxml")));
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(town);
            t.setTitle("Sell Screen");
            t.show();
        } catch (Exception e) {
            log.error("File Not Found", e);        }
    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Player Configuration");
            t.show();
        } catch (Exception e) {
            log.error("File Not Found", e);        }
    }
}
