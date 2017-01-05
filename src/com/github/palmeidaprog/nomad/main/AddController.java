/*
* add.fxml's Controller
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    private Stage addStage;

    // TableView objects
    @FXML private TableView<Folders> foldersTable;
    @FXML private TableColumn<Folders, String> foldersCol;
    @FXML private TableColumn<Folders, CheckBox> contentCol;
    private ObservableList<Folders> foldersList = FXCollections.observableArrayList();
    /*private ObservableList<Folders> foldersList = FXCollections.observableArrayList(new
            Folders(new File("back.jpg")));*/

    // FXML Controls
    @FXML private Button createProfileBtn;
    @FXML private TextField profileTextF;
    @FXML private Label destDirLabel, titleLabel;
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
        foldersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    //---------------------------------------------------------------------

    // Choose Destination Folder Button Click Event
    public void chooseClicked() {
        destDirLabel.setText(chooser(StringResources.getDirChooserTitle(), 0).toString());
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
        foldersList.add(new Folders(chooser(StringResources.getDirChooserTitle(), 0)));
    }

    // add file button click event
    public void addFileClicked() {
        foldersList.add(new Folders(chooser(StringResources.getFileChooserTitle(), 1)));
    }

    // remove item from foldersTable
    public void removeClicked() {
        List<Folders> toRemove = new ArrayList<>();
        toRemove.addAll(foldersTable.getSelectionModel().getSelectedItems());
        foldersList.removeAll(toRemove);
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


    // clean the add/edit window from previous information
    private void cleanAddStage() {
        //todo: clear all the add stage controls before closing the window
    }

    /*
    * Directory/File chooser
    * @param title Window Title
    * @param i 0 to choose a directory and 1 for directory
    */
    private File chooser(String title, int i) {
        if(i == 0) {
            DirectoryChooser wChooser = new DirectoryChooser();
            wChooser.setTitle(title);
            wChooser.setInitialDirectory(new File("/"));
            return wChooser.showDialog(addStage);
        }
        else { // todo: implement later multiple file selection
            FileChooser wChooser = new FileChooser();
            wChooser.setTitle(title);
            wChooser.setInitialDirectory(new File("/"));
            wChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                    StringResources.getFileExtensionAll(), "*.*"));
            return wChooser.showOpenDialog(addStage);
        }
    }

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
