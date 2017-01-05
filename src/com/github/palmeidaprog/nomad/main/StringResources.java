/*
* Holds Strings Resource to make translation easier
* Nomad-j
* @author Paulo R. Almeida Filho
* @email palmeidaprogramming@gmail.com
*/

package com.github.palmeidaprog.nomad.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StringResources {

    // --Add Window---------------------------------------------------------------
    private static String addStageTitle = "Adicionar novo Perfil";

    // Directory/File Chooser
    private static String dirChooserTitle = "Escolher pasta a ser adicionada";
    private static String fileChooserTitle = "Escolher arquivo a ser adicionado";
    private static String fileExtensionAll = "Todos Arquivos";

    // Combo Portable List
    private static ObservableList<String> portableComboList = FXCollections
            .observableArrayList("USB/Cartão SD", "HD Externo");

    private static String noFolderAdded = "Nenhuma pasta escolhida";

    //--Getters-------------------------------------------------------------------

    public static String getNoFolderAdded() {
        return noFolderAdded;
    }

    public static ObservableList<String> getPortableComboList() {
        return portableComboList;
    }

    public static String getAddStageTitle() {
        return addStageTitle;
    }

    public static String getDirChooserTitle() {
        return dirChooserTitle;
    }

    public static String getFileChooserTitle() {
        return fileChooserTitle;
    }

    public static String getFileExtensionAll() {
        return fileExtensionAll;
    }

}
