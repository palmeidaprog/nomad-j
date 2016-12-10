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
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Parent root;
    public static Stage mainStage;
    private final double OPACITY = 0.85;
    private final double BLUR_AMOUNT = 60;
    private BoxBlur blur = new BoxBlur(BLUR_AMOUNT, BLUR_AMOUNT, 3);

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
        mainStage.setScene(new Scene(root, 500, 550));
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
