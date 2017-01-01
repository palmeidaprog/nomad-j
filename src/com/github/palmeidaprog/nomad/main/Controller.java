package com.github.palmeidaprog.nomad.main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.io.IOException;

import static com.github.palmeidaprog.nomad.main.Main.mainStage;

public class Controller {
    private int i = 1;
    private static final double W = 330;
    private static final double H = 590;

    private static final double BLUR_AMOUNT = 60;
    private static final Duration SLIDE_DURATION = Duration.seconds(0.4);

    private static final double UPPER_SLIDE_POSITION = 100;

    private static final Effect frostEffect =
            new BoxBlur(BLUR_AMOUNT, BLUR_AMOUNT, 3);


    //--Singleton design----------------------------------
    private volatile static Controller instance = null;
    private Controller() { }
    public synchronized static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    //--Events--------------------------------------------

    public void addStage() {
        FXMLLoader addLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root = null;
        Stage addStage = new Stage();
        //mainLoader.setController(Controller.getInstance());
        try {
            root = addLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        addStage.setTitle("Adicionar novo perfil");
        addStage.setScene(new Scene(root, 630 , 450));
        addStage.show();
    }

    public void buttonClicked() {
        /*if(i == 0) i = 1;
        else i = 0;
        System.out.println("Clicked"); // @debug
        TrayIcn.getInstance().changeIcon(i);*/
        DoubleProperty sD = new SimpleDoubleProperty(H);
        freeze(Main.root, sD);

    }

    // create a frosty pane from a background node.
    private StackPane freeze(Node background, DoubleProperty y) {
        Image frostImage = background.snapshot(
                new SnapshotParameters(),
                null
        );
        ImageView frost = new ImageView(frostImage);

        Rectangle filler = new Rectangle(0, 0, W, H);
        filler.setFill(Color.AZURE);

        Pane frostPane = new Pane(frost);
        frostPane.setEffect(frostEffect);

        StackPane frostView = new StackPane(
                filler,
                frostPane
        );

        Rectangle clipShape = new Rectangle(0, y.get(), W, H);
        frostView.setClip(clipShape);

        clipShape.yProperty().bind(y);

        return frostView;
    }

}
