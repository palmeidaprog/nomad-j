/*
* Profile class, holds information about sync profiles
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.sync;

import com.github.palmeidaprog.nomad.main.Folders;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import java.io.File;
import java.util.ArrayList;

public class Profile {
    private String profileName; // profile name
    private CheckBox active; // profileTable control
    private ObservableList<Folders> foldersList;
    private boolean mobileMode;
    private File containerFolder;
    private Mobile mobile = Mobile.USB;


    /*@param name Profile name
    * @param fList List containing folders to sync
    * @param container Container's Folders */
    public Profile(String name, ObservableList<Folders> fList, File container) {
        profileName = name;
        foldersList = fList;
        containerFolder = container;
        active = new CheckBox();
        active.setSelected(true);
    }

    public enum Mobile {
        USB, EXTERNALHD;
    }

    //---Getters and Setters-------------------------------------------------

    public void setMobile(Mobile m) {
        mobile = m;
    }

    public Mobile getMobile() {
        return mobile;
    }

    // activate/deactive profile
    public void setActive(boolean b) {
        active.setSelected(b);
    }

    // is profile active
    public void isActive() {
        active.isSelected();
    }

    public ObservableList<Folders> getFoldersList() {
        return foldersList;
    }

    public void setFoldersList(ObservableList<Folders> foldersList) {
        this.foldersList = foldersList;
    }

    public boolean isMobileMode() {
        return mobileMode;
    }

    public void setMobileMode(boolean mobileMode) {
        this.mobileMode = mobileMode;
    }

    public File getContainerFolder() {
        return containerFolder;
    }

    public void setContainerFolder(File containerFolder) {
        this.containerFolder = containerFolder;
    }

    public void setPortable(boolean b) {
        mobileMode = b;
    }

    public boolean isPortable() {
        return mobileMode;
    }

    //--Data Model-----------------------------------------------------------

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String p) {
        profileName = p;
    }

    public CheckBox getActive() {
        return active;
    }

    public String getContainerFolderString() {
        return containerFolder.toString();
    }

}
