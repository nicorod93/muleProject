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

    public static final String MAP_NAME = "Map";
    public static final String NO_FILE = "No file";

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
        playerMoneyLabel.textProperty().set("$" + Long.toString(Main.getCurrentPlayer().getMoney()));
        playerEnergyAmm.textProperty().set("Energy: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(1).getAmount()));
        playerFoodAmm.textProperty().set("Food: " + Long.toString(Main.getCurrentPlayer().getSpecificItem(0).getAmount()));
    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Stage t = (Stage) ((Node) event.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle(MAP_NAME);
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException(NO_FILE);
        }
    }

    @FXML
    private void buyFood(MouseEvent ev) {
        try {
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 25);
            Main.placeFood = true;
            Stage t = (Stage) ((Node) ev.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle(MAP_NAME);
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException(NO_FILE);
        }
    }

    @FXML
    private void buyEnergy(MouseEvent eve) {
        try {
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 50);
            Main.placeEnergy = true;
            Stage t = (Stage) ((Node) eve.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle(MAP_NAME);
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException(NO_FILE);
        }
    }

    @FXML
    private void buyOre(MouseEvent even) {
        try {
            Main.getCurrentPlayer().setMoney(Main.getCurrentPlayer().getMoney() - 100);
            Main.placeOre = true;
            Stage t = (Stage) ((Node) even.getSource()).getScene().getWindow();
            t.setScene(Main.map);
            t.setTitle(MAP_NAME);
            t.show();
        } catch (Exception e) {
            throw new IllegalArgumentException(NO_FILE);
        }
    }
}
