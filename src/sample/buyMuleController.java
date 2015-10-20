package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by RichardWang on 10/16/15.
 */
public class buyMuleController  implements Initializable {

    @FXML
    private Label playerFoodAmm;

    @FXML
    private Label playerEnergyAmm;

    @FXML
    private Label playerNameID;

    @FXML
    private Label playerMoneyLabel;
//
//    @FXML
//    private Button foodBut;
//
//    @FXML
//    private Button energyBut;
//
//    @FXML
//    private Button muleBut;
//
//    @FXML
//    private Button oreBut;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        playerNameID.textProperty().set(Main.getCurrentPlayer().getName());
        playerMoneyLabel.textProperty().set("$" + Integer.toString(Main.getCurrentPlayer().getMoney()));
        playerEnergyAmm.textProperty().set("Energy: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
        playerFoodAmm.textProperty().set("Food: " + Integer.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Map");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @FXML
    private void buyFood(MouseEvent ev) {
        try {
            Main.placeFood = true;
            Stage t = (Stage) ((Node) ev.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Map");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @FXML
    private void buyEnergy(MouseEvent eve) {
        try {
            Main.placeEnergy = true;
            Stage t = (Stage) ((Node) eve.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Map");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }

    @FXML
    private void buyOre(MouseEvent even) {
        try {
            Main.placeOre = true;
            Stage t = (Stage) ((Node) even.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle("Map");
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException("No file");
        }
    }
}
