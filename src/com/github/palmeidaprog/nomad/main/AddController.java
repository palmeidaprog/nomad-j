package com.github.palmeidaprog.nomad.main;

/*
* add.fxml's Controller
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

import com.github.palmeidaprog.nomad.sync.Profile;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static javafx.application.ConditionalFeature.FXML;

public class AddController {
    @FXML private TableView<Profile> foldersTable;
    @FXML private TableColumn<Profile, String> foldersCol;
    @FXML private TableColumn<Profile, CheckBox> contentCol;

    //--Singleton design--------------------------------------------------
    private volatile static AddController instance = null;
    private AddController() { }
    public synchronized static AddController getInstance() {
        if(instance == null) {
            instance = new AddController();
        }
        return instance;
    }

    //---------------------------------------------------------------------
}
