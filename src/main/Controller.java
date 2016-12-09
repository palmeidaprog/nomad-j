package main;

/*
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

public class Controller {
    private int i = 1;

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
        if(i == 0) i = 1;
        else i = 0;
        System.out.println("Clicked"); // @debug
        TrayIcn.getInstance().changeIcon(i);
    }
}
