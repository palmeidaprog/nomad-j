/*
* Data Model for folderTab in add.fxml
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.main;

import javafx.scene.control.CheckBox;
import java.io.File;


public class Folders { // todo: Data model for foldersTable (Incomplete)
    private File folder;
    private CheckBox content;

    public Folders(File f) {
        folder = f;
        content = new CheckBox("");
    }

    //--Column Show Content--------------------------------------------------------
    public CheckBox getContent() {
        return content;
    }

    public void setContent(CheckBox c) {
        content = c;
    }

    //--Column Folders--------------------------------------------------------------
    public String getFolder() {
        return folder.getAbsoluteFile().toString();
    }

    public void setFolder(File f) {
        folder = f;
    }

}
