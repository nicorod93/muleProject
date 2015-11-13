package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;

/**
 * Created by RichardWang on 10/15/15.
 */
public class LandOfficeController {

    private static org.apache.log4j.Logger log = LogManager.getRootLogger();
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
