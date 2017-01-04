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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    // TableView objects
    @FXML private TableView<Folders> foldersTable;
    @FXML private TableColumn<Folders, String> foldersCol;
    @FXML private TableColumn<Folders, CheckBox> contentCol;
    private ObservableList<Folders> foldersList = FXCollections.observableArrayList(new
            Folders(new File("back.jpg")));

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

    public void clickCreateProfile() {

    }
}
