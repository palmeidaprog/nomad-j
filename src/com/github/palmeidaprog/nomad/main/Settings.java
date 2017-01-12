package com.github.palmeidaprog.nomad.main;

import java.io.File;
import java.io.Serializable;

/**
 * Created by paulo on 1/12/17.
 */
public class Settings implements Serializable {
    // Don't serialize these
    private File macFolder = new File(System.getProperty("user.home") +
            "/Library/Application Support/Nomad");
    private File windowsFolder = new File(System.getProperty("user.home") +
            "/AppData/Roaming/Nomad");

    // Constructor for serialization
    public Settings(Settings s) {
        // todo: create from serial.
    }

    //--Singleton design----------------------------------------------------

    private static volatile Settings instance = null;
    private Settings() { }
    public synchronized static Settings getInstance() {
        if(instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    //------------------------------------------------------------------------

    // get folder to place configuration file
    public File getConfigDir() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("windows")) {
            return windowsFolder;
        }
        else if(os.contains("mac")) {
            return macFolder;
        }
        else { // linux and others
            return new File("data"); // return program root folder
        }
    }

    //--Serializable implementation-------------------------------------------

    // todo: create save and read objfile methods

    // todo: implement serialization once the class has all the variables
    @Override
    public String toString() {
        return "empty";
    }

}
