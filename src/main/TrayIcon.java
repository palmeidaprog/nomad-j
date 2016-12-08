package main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;

public class TrayIcon {
    private String os;
    //private Image ok = new Image(getClass().getResourceAsStream("images/ok.png"));
    private BufferedImage ok = new BufferedImage(getClass().getResourceAsStream("images/ok.png"));
    //private Image toSync = new Image(getClass().getResourceAsStream("images/tosync.png"));


    //--Singleton design----------------------------------
    private volatile static TrayIcon instance = null;
    private TrayIcon() {
        detectOS();
    }
    public synchronized static TrayIcon getInstance() {
        if(instance == null) {
            instance = new TrayIcon();
        }
        return instance;
    }

    /* detects OS */
    private void detectOS() {
        if(System.getProperty("os.name").toLowerCase().contains("windows")) {
            os = "windows";
        }
        else if(System.getProperty("os.name").toLowerCase().contains("linux")) {
            os = "linux";
        }
        else {
            os = "mac";
        }
    }

    // creates SystemTrayIcon
    public void createSystemTrayIcon() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        /*final TrayIcon trayIcon = new TrayIcon(
                createImage("images/ok.png", "tray icon")));*/
        final TrayIcon trayIcon = new TrayIcon(ok);
        final SystemTray tray = SystemTray.getSystemTray();
    }

}
