package com.github.palmeidaprog.nomad.sync;

import java.io.File;

/**
 * Created by paulo on 1/1/17.
 */
public class Profile {
    private String profile;
    private boolean mobileMode;
    private File containderFolder;

    private enum Mobile {
        USB, EXTERNALHD
    }

}
