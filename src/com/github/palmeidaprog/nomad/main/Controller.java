package com.github.palmeidaprog.nomad.main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

import com.github.palmeidaprog.nomad.sync.Profile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TableView;
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
import java.net.URL;
import java.util.ResourceBundle;

import static com.github.palmeidaprog.nomad.main.Main.mainStage;

public class Controller implements Initializable {

    @FXML public ImageView addImage, editImage, deleteImage;
    @FXML private TableView<Profile> profileTable;
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

    //--Implementing iniatilizable interface---------------------

    @Override
    public void initialize(URL u,ResourceBundle rb) {
        profileTable.requestFocus(); // todo: Find a way to stop TextField requesting focus
    }

    //--Enter Events--------------------------------------------

    public void mouseEnterAdd() {
        /*addImage.setOpacity(0);*/
        UIEffects.getInstance().fadeAnim(addImage, 1);
    }

    public void mouseEnterEdit() {
        /*addImage.setOpacity(0);*/
        UIEffects.getInstance().fadeAnim(editImage, 1);
    }
    public void mouseEnterDelete() {
        /*addImage.setOpacity(0);*/
        UIEffects.getInstance().fadeAnim(deleteImage, 1);
    }

    //--Exit Events--------------------------------------------

    public void mouseExitAdd() {
        UIEffects.getInstance().fadeAnim(addImage, 0);
    }

    public void mouseExitEdit() {
        UIEffects.getInstance().fadeAnim(editImage, 0);
    }

    public void mouseExitDelete() {
        UIEffects.getInstance().fadeAnim(deleteImage, 0);
    }

    //--Click Events--------------------------------------------

    public void addStage() {
        FXMLLoader addLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root = null;
        Stage addStage = new Stage();
        addLoader.setController(AddController.getInstance());
        try {
            root = addLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        addStage.setTitle("Adicionar novo perfil");
        addStage.setScene(new Scene(root, 700, 542));
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
