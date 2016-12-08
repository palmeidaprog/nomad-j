package main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

public class Controller {

    //--Singleton design----------------------------------
    private volatile static Controller instance = null;
    private Controller() { }
    public synchronized static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    //--Events--------------------------------------------

    public void buttonClicked() {
        TrayIcon.getInstance().createSystemTrayIcon();
    }

}
