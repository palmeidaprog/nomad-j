package main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static java.awt.Toolkit.getDefaultToolkit;

public class TrayIcn {
    private String os;
    private TrayIcon trayIcon;

    //--Singleton design--------------------------------------------------
    private volatile static TrayIcn instance = null;
    private TrayIcn() {
        detectOS();
    }
    public synchronized static TrayIcn getInstance() {
        if(instance == null) {
            instance = new TrayIcn();
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
        getDefaultToolkit();
        System.out.println("passed"); // @debug


        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final SystemTray tray = SystemTray.getSystemTray();

        // creating popup menu
        final PopupMenu popup = new PopupMenu();


        /*final TrayIcon trayIcon = new TrayIcon(
                createImage("images/ok.png", "tray icon")));*/

        Image image = null;
        try {
            image = getIcon(1);
            //ImageIcon iIcon = new ImageIcon(getClass().getResourceAsStream("images/ok128.gif"));
            trayIcon = new TrayIcon(image, "Nomad", popup);
            tray.add(trayIcon);
            //trayIcon.setPopupMenu(popup);
        }
        catch(AWTException e) {
            e.printStackTrace();
        }

        // exit program system tray icon menu
        MenuItem exitItem = new MenuItem("Sair");
        exitItem.addActionListener(event -> {
            Platform.exit();
            tray.remove(trayIcon);
        });

        // open program system tray icon menu
        final Stage stage = Main.mainStage;
        MenuItem openItem = new MenuItem("Abrir Programa");
        openItem.addActionListener(event -> {
           stage.show();
        });

        // setup the popup menu for the application.
        popup.add(openItem);
        popup.addSeparator();
        popup.add(exitItem);
        trayIcon.setPopupMenu(popup);
        //final TrayIcon trayIcon = new TrayIcon(image);
    }
    /*
    * @param i 0 for unsynchronized icon and 1 for synchronized (standard)
    * */
    private Image getIcon(int i) {
        try {
            if (os.equals("mac")) {
                return i == 1 ? ImageIO.read(getClass().getResourceAsStream("mac_ok128.gif")) :
                        ImageIO.read(getClass().getResourceAsStream("mac_sync128.gif"));
            }
            else {
                return i == 1 ? ImageIO.read(getClass().getResourceAsStream("windows_ok128.png")) :
                        ImageIO.read(getClass().getResourceAsStream("mac_sync128.gif"));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
