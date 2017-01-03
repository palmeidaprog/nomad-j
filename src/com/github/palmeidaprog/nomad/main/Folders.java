package com.github.palmeidaprog.nomad.main;

import javafx.scene.control.CheckBox;

import java.io.File;

/**
 * Created by paulo on 1/3/17.
 */
public class Folders { // todo: Data model for foldersTable (Incomplete)
    private File folder;
    private CheckBox content;

    public Folders(File f) {
        this.folder = f;
    }

    public CheckBox getContent() {
        return content;
    }

    public String getFolder() {
        return folder.toString();
    }


}
