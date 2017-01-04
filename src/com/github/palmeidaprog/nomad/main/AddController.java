/*
* add.fxml's Controller
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.main;

import com.sun.tools.javac.comp.Check;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    private Stage addStage;

    // TableView objects
    @FXML private TableView<Folders> foldersTable;
    @FXML private TableColumn<Folders, String> foldersCol;
    @FXML private TableColumn<Folders, CheckBox> contentCol;
    private ObservableList<Folders> foldersList = FXCollections.observableArrayList(new
            Folders(new File("back.jpg")));

    // FXML Controls
    @FXML private Label destDirLabel;
    @FXML private CheckBox portableCheck;
    @FXML private ComboBox portableCombo;


    //--Singleton design--------------------------------------------------
    private volatile static AddController instance = null;
    private AddController() { }
    public synchronized static AddController getInstance() {
        if(instance == null) {
            instance = new AddController();
        }
        return instance;
    }

    //--Implementing Initializable interface-------------------------------

    @Override
    public void initialize(URL u, ResourceBundle rb) {
        foldersCol.setCellValueFactory(new PropertyValueFactory<>("folder"));
        contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        foldersTable.setItems(foldersList);
    }

    //---------------------------------------------------------------------

    // Choose Destination Folder Button Click Event
    public void chooseClicked() {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Escolher Pasta Destino");
        dirChooser.setInitialDirectory(new File("/"));
        destDirLabel.setText(dirChooser.showDialog(addStage).toString());
    }

    // select/unselect portable mode checkbox event
    public void portableCheckClicked() {
        if(portableCheck.isSelected()) {
            portableCombo.setDisable(false);
        }
        else {
            portableCombo.setDisable(true);
        }
    }

    // add folder button click event
    public void addFolderClicked() {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Escolher pasta a ser adiionada:");
        dirChooser.setInitialDirectory(new File("/"));
        foldersList.add(new Folders(dirChooser.showDialog(addStage)));
    }

    public void clickCreateProfile() {
        validateCreateProfile();
    }

    //todo: implement validation to profile creation
    public void validateCreateProfile() { // todo: getfocus back to addStage
        DialogController.getInstance().getStage("Erro", "Erro Tit", "Corpo",
                "Butão").show(); // todo: fox this stage calling
    }

    //--Support methods-------------------------------------------------------------

    public void setStage(Stage s) {
        addStage = s;
    }

    // verify is file or directory exists and p
    private File validateDir(File f) {
        if(f.exists() && f.isDirectory()) {
            return f;
        }
        else if(!f.exists()) {
            // todo: dialog create directory question
            return f;
        }
        else {
            // todo: is a file not a directory error
            return null;
        }
    }
}
