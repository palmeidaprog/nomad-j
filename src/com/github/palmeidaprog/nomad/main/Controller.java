/*
* Main window's controller
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.main;

import com.github.palmeidaprog.nomad.sync.Profile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // profile table view
    @FXML public ImageView addImage, editImage, deleteImage;
    @FXML private TableView<Profile> profileTable;
    @FXML private TableColumn<Profile, CheckBox> activeCol;
    @FXML private TableColumn<Profile, String> profileCol;
    @FXML private TableColumn<Profile, String> folderCol;
    private ObservableList<Profile> profileList = FXCollections.observableArrayList();

    private static final double BLUR_AMOUNT = 60;
    private static final Duration SLIDE_DURATION = Duration.seconds(0.4);

    // Window Stage Obj
    private Stage mainStage;
    private Stage addStage;

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
        profileList = Profile.readObjList();

        // profile's table
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        profileCol.setCellValueFactory(new PropertyValueFactory<>("profileName"));
        folderCol.setCellValueFactory(new PropertyValueFactory<>("containerFolderString"));
        profileTable.setItems(profileList);
        profileTable.setPlaceholder(new Label("Sem perfis. Clique em adicionar à esquerda"));

        profileTable.requestFocus(); // todo: Find a way to stop TextField requesting focus
        initializeDialogStage();
        initializeAddStage();

        //Close Main Window Event
        mainStage = Main.mainStage;

    }

    private void initializeDialogStage() {
        final double WIDTH = 500;
        final double HEIGHT = 250;

        FXMLLoader dialogLoad = new FXMLLoader(getClass().getResource("dialog_nomad.fxml"));
        Parent dialogRoot = null;
        Stage dialogStage = new Stage();
        dialogLoad.setController(DialogController.getInstance());
        try {
            dialogRoot = dialogLoad.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        dialogStage.setTitle("Dialog");
        dialogStage.setScene(new Scene(dialogRoot, WIDTH, HEIGHT));
        dialogStage.getIcons().add(new Image(Main.class
                .getResourceAsStream("comment_error_32.png")));
        DialogController.getInstance().setStage(dialogStage, WIDTH, HEIGHT);
    }

    private void initializeAddStage() {
        FXMLLoader addLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root = null;
        addStage = new Stage();
        addLoader.setController(AddController.getInstance());
        try {
            root = addLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
        addStage.setTitle(StringResources.getAddStageTitle());
        addStage.setScene(new Scene(root, 700, 542));
        addStage.getIcons().add(new Image(Main.class
                .getResourceAsStream("nomad-icon-64.png")));
        AddController.getInstance().setStage(addStage);
    }

    public void updpateObjOnClose() {
        Profile.updateObjFile(profileList);
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

    //--Mouse Exit Events--------------------------------------------

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
        AddController add = AddController.getInstance();
        if(add.getStage().isShowing()) {
            add.getStage().requestFocus();
        }
        else {
            add.getStage(Main.mainStage.getX()+30, Main.mainStage.getY()+30).showAndWait();
            add.cleanAddStage();
        }
    }

    public void buttonClicked() {
        /*if(i == 0) i = 1;
        else i = 0;
        System.out.println("Clicked"); // @debug
        TrayIcn.getInstance().changeIcon(i);*/
        //DoubleProperty sD = new SimpleDoubleProperty(H);

    }

    //--Extra events----------------------------------------------------------------


    //--Support methods-------------------------------------------------------------

    public boolean validateNewProfile(String name) {
        // todo: if profileList contains the name return false
        return true;
    }

    public void addProfile(Profile prof) {
        profileList.add(prof);
        Profile.updateObjFile(profileList);
    }
}
