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
    private GridPane pane;

//    @Override
//    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
//            Image image = new Image("http://www.planwallpaper.com/static/images/colorful-triangles-background_yB0qTG6.jpg");
//            pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
//    }

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
        butt.setStyle("-fx-background-color:" + Main.playerColor.get(Main.playerTurn));
        butt.setOpacity(.3);
        System.out.println("Clicked");
        if(Main.playerTurn < Main.players - 1) {
            Main.playerTurn++;
        } else {
            Main.playerTurn = 0;
        }
        butt.setDisable(true);
    }
}