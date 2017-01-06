/*
* dialog_nomad.fxml's Controller
* Loaded in Controller Class (Main window's controller)
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DialogController {
    private Stage dialogStage;

    // Dialog controls
    @FXML private Label titleLabel, bodyLabel;
    @FXML private Button okButton;

    //--Singleton design----------------------------------
    private volatile static DialogController instance = null;
    private DialogController() { }
    public synchronized static DialogController getInstance() {
        if(instance == null) {
            instance = new DialogController();
        }
        return instance;
    }

    public void setStage(Stage s) {
        dialogStage = s;
    }

    /*
    * Custom dialog call
    * @param title Window dialog title
    * @param title2 header label
    * @param bodyText Body Label's Text
    * @param buttonText Button's Text
    */
    public Stage getStage(String title, String title2, String bodyText, String buttonText) {
        dialogStage.setTitle(title);
        titleLabel.setText(title2);
        bodyLabel.setText(bodyText);
        okButton.setText(buttonText);
        return dialogStage;
    }

    public void closeStage() {
        dialogStage.close();
    }
}
