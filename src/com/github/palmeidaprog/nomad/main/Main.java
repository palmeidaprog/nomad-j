package com.github.palmeidaprog.nomad.main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Parent root;
    public static Stage mainStage;
    public final int WIDTH = 750, HEIGHT = 600; // mainStage dimentsions

    @Override
    public void start(Stage primaryStage) throws Exception{
        // FXML load
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        mainLoader.setController(Controller.getInstance());
        try {
            root = mainLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        mainStage = primaryStage; // saves Stage as public
        mainStage.setTitle("Nomad");
        mainStage.setScene(new Scene(root, WIDTH , HEIGHT));
        mainStage.getIcons().add(new Image(Main.class
                .getResourceAsStream("nomad-icon-64.png")));
        mainStage.show();
        //root.setEffect(blur);
        Platform.setImplicitExit(false);
        TrayIcn.getInstance().createSystemTrayIcon();
        //mainStage.setOpacity(OPACITY);
        //mainStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
