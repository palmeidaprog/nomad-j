/*
* Profile class, holds information about sync profiles
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.sync;

import com.github.palmeidaprog.nomad.main.Folders;
import com.github.palmeidaprog.nomad.main.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

import java.io.*;
import java.util.ArrayList;

public class Profile implements Serializable {
    private String profileName; // profile name
    private transient CheckBox active; // profileTable control
    private boolean activeSelection;
    private transient ObservableList<Folders> foldersList;
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
        checkBoxSelectEvent();
        active.setSelected(true);
    }

    // ReadObj serialized constructor
    public Profile(Profile p) {
        profileName = p.getProfileName();
        active = new CheckBox();
        active.setSelected(p.isActiveSelection());
        foldersList = p.getFoldersList();
        mobileMode = p.isMobileMode();
        containerFolder = p.getContainerFolder();
        mobile = p.getMobile();
        checkBoxSelectEvent();
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
    public boolean isActive() {
        return active.isSelected();
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

    public boolean isActiveSelection() {
        return activeSelection;
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

    private void checkBoxSelectEvent() {
        active.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(active.isSelected()) {
                    activeSelection = true;
                    System.out.println("Selected"); // @debug
                }
                else {
                    activeSelection = false;
                    System.out.println("deSelected"); // @debug
                }
            }
        });
    }

    //--Seriablize read and write-----------------------------------------------------------

    /*read from obj file and create a list
    * @return List with the objects read from the file*/
    public static ObservableList<Profile> readObjList() {
        ObservableList<Profile> list = FXCollections.observableArrayList();
        File profilesFile = new File(Settings.getInstance().getConfigDir() +
                "/profiles.ser");

        if(!profilesFile.exists()) {
            createObjFile();
        }
        else {
            // readFile
            try (ObjectInputStream objIS = new ObjectInputStream(new FileInputStream(profilesFile))) {


                while (true) { // infinite loop
                    Profile obj = new Profile((Profile) objIS.readObject());
                    list.add(obj);
                }
            } catch(EOFException e) {
                // do nothing (standard loop exit)
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /*@param list List of objs to be written*/
    public static void updateObjFile(ObservableList<Profile> list) {
        File profilesFile = new File(Settings.getInstance().getConfigDir() +
                "/profiles.ser");

        // create file if it doesn't exist
        if(!profilesFile.exists()) {
            createObjFile();
        }

        try(ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(profilesFile))) {
            for(Profile p : list) {
                objOS.writeObject(p);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // creates profiles.ser
    private static void createObjFile() {
        try { // todo: dialog error "couldn't create file/dir"
            if(!Settings.getInstance().getConfigDir().mkdirs()) {
                throw new IOException("Couldn't create the directory");
            }
            if(!new File(Settings.getInstance().getConfigDir() + "/profiles.ser").createNewFile()) {
                throw new IOException("Couldn't create the file");
            }
        } catch(IOException e) {
            //e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "profileName=" + profileName + "; activeSelection=" + activeSelection +
                "; mobileMode=" + mobileMode +
                "; containerFolder=" + containerFolder +
                "; mobile=" + mobile;
    }
}
//"; foldersList=" + foldersList +