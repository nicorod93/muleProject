package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.ArrayList;

public class Main extends Application {
    public static ArrayList<String> playerRace = new ArrayList<>();
    public static int players = 0;
    public static String difficulty;
    public static String mapType;
    public static int counter = 1;
    public static ArrayList<Color> playerColor = new ArrayList<>();
    public static ArrayList<String> playerName = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLHomeScreen.fxml")); //main screen is home screen
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
