package main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Parent root;
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // FXML Load
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        mainLoader.setController(Controller.getInstance());
        try {
            root = mainLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        mainStage = primaryStage; // saves Stage as public
        primaryStage.setTitle("Nomad");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
