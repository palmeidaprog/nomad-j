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

    // Missing adding/editing data
    private static String profileName = "Nome do perfil";
    private static String containerFolder = "Pasta Recipiente";
    private static String foldersToSync = "Pastas a serem sincronizadas";

    // Controls
    private static String destDirLabel = "Escolher pasta destino";

    // dialog
    private static String addDialogTitle = "Erro: Não foi possivel criar perfil";
    private static String addDialogHeader = "Informações incompletas";
    private static String addDialogBody = "As informações a seguir estão faltando para criar " +
            "o perfil: ";
    private static String addDialogBtn = "OK";

    //--Getters-------------------------------------------------------------------

    public static String getAddDialogTitle() {
        return addDialogTitle;
    }

    public static String getAddDialogHeader() {
        return addDialogHeader;
    }

    public static String getAddDialogBody() {
        return addDialogBody;
    }

    public static String getAddDialogBtn() {
        return addDialogBtn;
    }

    public static String getDestDirLabel() {
        return destDirLabel;
    }

    public static String getProfileName() {
        return profileName;
    }

    public static String getContainerFolder() {
        return containerFolder;
    }

    public static String getFoldersToSync() {
        return foldersToSync;
    }

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
