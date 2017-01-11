package com.github.palmeidaprog.nomad.main;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Control;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Created by paulo on 12/31/16.
 */
public class UIEffects {
    private FadeTransition ft;

    //--Singleton Constructor---------------------------------------------------
    private static volatile UIEffects instance = null;
    private UIEffects() {}
    public static synchronized UIEffects getInstance() {
        if(instance == null) {
            instance = new UIEffects();
        }
        return instance;
    }


    //--Effects methods---------------------------------------------------

    /*
    * @param img applies to ImageView the effect.
    * @param i 0 for exiting and 1 for entering a new obj
    * */
    public void fadeAnim(ImageView img, int i) {
        if(i == 0) {
            ft.getNode().setOpacity(1.0);
            ft.stop();
            ft = null;
        }
        else if(i == 1) {
            if(ft != null) {
                ft.getNode().setOpacity(1.0);
                ft.stop();
            }

            ft = new FadeTransition(Duration.millis(750), img);
            ft.setFromValue(1.0);
            ft.setToValue(0.3);
            ft.setCycleCount(Timeline.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();
        }
    }

    /*Add dropshadow effect to control*/
    public void addShadow(Control c) {
        DropShadow drop = new DropShadow();
        drop.setOffsetY(2);
        c.setEffect(drop);
    }
}
