/*
* add.fxml's Controller
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.main;

import com.github.palmeidaprog.nomad.sync.Profile;
import com.github.palmeidaprog.nomad.sync.Profile.Mobile;
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
    private File destFolder;

    // TableView objects
    @FXML private TableView<Folders> foldersTable;
    @FXML private TableColumn<Folders, String> foldersCol;
    @FXML private TableColumn<Folders, CheckBox> contentCol;
    private ObservableList<Folders> foldersList = FXCollections.observableArrayList();


    // FXML Controls
    @FXML private Button createProfileBtn;
    @FXML private TextField profileTextF;
    @FXML private Label destDirLabel, titleLabel;
    @FXML private CheckBox portableCheck;
    @FXML private ComboBox<String> portableCombo;
    private ObservableList<String> portableComboList; // get from StringResource class


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
        // table view
        foldersCol.setCellValueFactory(new PropertyValueFactory<>("folder"));
        contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        foldersTable.setItems(foldersList);
        foldersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // combo box
        portableComboList = StringResources.getPortableComboList();
        portableCombo.setItems(portableComboList);

        // changing folderTable empty placeholder message
        foldersTable.setPlaceholder(new Label(StringResources.getNoFolderAdded()));
    }

    //---------------------------------------------------------------------

    // Choose Destination Folder Button Click Event
    public void chooseClicked() {//todo: clear all the add stage controls before closing the window
        destFolder = chooser(StringResources.getDirChooserTitle(), 0);
        destDirLabel.setText(destFolder.toString());
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

    // create profile button click event
    public void clickCreateProfile() {
        if(validateCreateProfile()) {
            Profile profile = new Profile(profileTextF.getText(), foldersList, destFolder);
            profile.setPortable(true);
            if(portableCombo.getSelectionModel().getSelectedItem().equals(StringResources
                    .getUsb())) {
               profile.setMobile(Mobile.USB);
            }
            else if(portableCombo.getSelectionModel().getSelectedItem()
                    .equals(StringResources.getHdExt())) {
                profile.setMobile(Mobile.EXTERNALHD);
            }
            Controller.getInstance().addProfile(profile);
            cleanAddStage(); // clears the windows controls
            addStage.close();
        }
    }

    //--Support methods-------------------------------------------------------------

    //todo: implement validation to profile creation
    private boolean validateCreateProfile() { // todo: getfocus back to addStage
        List<String> toFix = new ArrayList<>();
        boolean showDialog = false;

        // if profile name is missing
        if(profileTextF.getText().equals("") || profileTextF.getText() == null) {
            toFix.add(StringResources.getProfileName());
            showDialog = true;
        }

        // didn't choose the Container Folder
        if(destDirLabel.getText().equals(StringResources.getDestDirLabel())) {
            toFix.add(StringResources.getContainerFolder());
            showDialog = true;
        }

        // empty folders table
        if(foldersList.isEmpty()) {
            toFix.add(StringResources.getFoldersToSync());
            showDialog = true;
        }

        // Error dialog
        if(showDialog) {
            String bodyText = StringResources.getAddDialogBody();
            for(String s : toFix) {
                bodyText = bodyText + s + ", ";
            }
            // replace comma at the end for a dot
            String aux = bodyText.substring(bodyText.length() -3);
            aux = aux.replace(",", ".");
            bodyText = bodyText.replace(bodyText.substring(bodyText.length()-3), aux);

            // show the dialog
            DialogController.getInstance().getStage(StringResources.getAddDialogTitle(),
                    StringResources.getAddDialogHeader(), bodyText,
                    StringResources.getAddDialogBtn()).show();
            return false;
        }
        else {
            return true;
        }
    }

    // todo: change this to Controller Class later
    public Boolean validateProfileName(String name) {
        return true;
    }

    // clean the add/edit window from previous information
    private void cleanAddStage() {
        profileTextF.setEffect(null);
        portableCheck.setSelected(false);
        portableCombo.setDisable(true);
        destDirLabel.setText(StringResources.getDestDirLabel());
        foldersList.clear();
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

    public Stage getStage() {
        return addStage;
    }

    /*Return Stage at a specific location
     * @param x X coord
     * @param y Y coord*/
    public Stage getStage(double x, double y) {
        addStage.setX(x);
        addStage.setY(y);
        return addStage;
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
