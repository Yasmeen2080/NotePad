package notepad2;

import static java.awt.SystemColor.menu;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCodeCombination;

import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class note extends BorderPane {

    FileInputStream fis;
    static String cutter = "";
    static Boolean forCutAndPaste = false;
    public static Boolean checkforsave = false;
    protected final MenuBar menuBar;
    protected final Menu fileMenu;
    protected final MenuItem newItem;
    protected final MenuItem openItem;
    protected final MenuItem saveItem;
    protected final MenuItem exitItem;
    protected final Menu editMenu;
    protected final MenuItem undoItem;
    protected final MenuItem cutItem;
    protected final MenuItem copyItem;
    protected final MenuItem pasteItem;
    protected final MenuItem deleteItem;
    protected final MenuItem selectAllItem;
    protected final Menu helpMenu;
    protected final MenuItem aboutItem;
    protected final TextArea textArea;

    public note() {

        menuBar = new MenuBar();
        fileMenu = new Menu();
        newItem = new MenuItem();
        openItem = new MenuItem();
        saveItem = new MenuItem();
        exitItem = new MenuItem();
        editMenu = new Menu();
        undoItem = new MenuItem();
        cutItem = new MenuItem();
        copyItem = new MenuItem();
        pasteItem = new MenuItem();
        deleteItem = new MenuItem();
        selectAllItem = new MenuItem();
        helpMenu = new Menu();
        aboutItem = new MenuItem();
        textArea = new TextArea();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(484.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        fileMenu.setMnemonicParsing(false);
        fileMenu.setText("File");

        newItem.setMnemonicParsing(false);
        newItem.setText("new");

        openItem.setMnemonicParsing(false);
        openItem.setText("open");

        saveItem.setMnemonicParsing(false);
        saveItem.setText("save");

        exitItem.setMnemonicParsing(false);
        exitItem.setText("Exit");

        editMenu.setMnemonicParsing(false);
        editMenu.setText("Edit");

        undoItem.setMnemonicParsing(false);
        undoItem.setText("undo");

        cutItem.setMnemonicParsing(false);
        cutItem.setText("cut");

        copyItem.setMnemonicParsing(false);
        copyItem.setText("copy");

        pasteItem.setMnemonicParsing(false);
        pasteItem.setText("paste");

        deleteItem.setMnemonicParsing(false);
        deleteItem.setText("delete");

        selectAllItem.setMnemonicParsing(false);
        selectAllItem.setText("select all");

        helpMenu.setMnemonicParsing(false);
        helpMenu.setText("Help");

        aboutItem.setMnemonicParsing(false);
        aboutItem.setText("About");
        setTop(menuBar);

        //Disbale All Here 
        //pasteItem.setDisable(true);
        cutItem.setDisable(true);
        copyItem.setDisable(true);
        deleteItem.setDisable(true);
        //Accelators Here !!
        saveItem.setAccelerator(KeyCodeCombination.keyCombination("Ctrl + S"));
        openItem.setAccelerator(KeyCodeCombination.keyCombination("Ctrl + O"));
        newItem.setAccelerator(KeyCodeCombination.keyCombination("Ctrl + N"));
        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(437.0);
        textArea.setPrefWidth(600.0);
        setCenter(textArea);

        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);
        editMenu.getItems().add(undoItem);
        editMenu.getItems().add(cutItem);
        editMenu.getItems().add(copyItem);
        editMenu.getItems().add(pasteItem);
        editMenu.getItems().add(deleteItem);
        editMenu.getItems().add(selectAllItem);
        menuBar.getMenus().add(editMenu);
        helpMenu.getItems().add(aboutItem);
        menuBar.getMenus().add(helpMenu);

        exitItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.getButtonTypes().setAll(ButtonType.OK,
                        ButtonType.CLOSE,
                        ButtonType.CANCEL);

                alert.setTitle("Notepad");
                alert.setHeaderText(null);
                if (checkforsave == false) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You want to Save your change " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        saveFile();
                    } else if (alert.getResult() == ButtonType.NO) {
                        System.exit(0);
                    } else if (alert.getResult() == ButtonType.CANCEL) {
                        System.out.println("cancel");
                        event.consume();
                    }
                } else {

                }

            }

        });

        newItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.getButtonTypes().setAll(ButtonType.OK,
                        ButtonType.CLOSE,
                        ButtonType.CANCEL);

                alert.setTitle("Notepad");
                alert.setHeaderText(null);
                if (checkforsave == false) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You want to Save your change " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        saveFile();
                    } else if (alert.getResult() == ButtonType.NO) {
                        textArea.clear();
                    } else if (alert.getResult() == ButtonType.CANCEL) {
                        alert.close();
                    }

                } else {
                    textArea.clear();
                }

            }

        });

        aboutItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                ImageView icon = new ImageView("Images/windows.png");

                alert.setHeaderText("Windows 10");
                DialogPane dialogPane = alert.getDialogPane();

                dialogPane.getStylesheets().add(
                        getClass().getResource("myCustomStyle.css").toExternalForm());
                alert.getDialogPane().setGraphic(icon);

                alert.setTitle("About");
                alert.setContentText("Microsoft Windows\nversion 1809 (os Build 17763.914) 2018 Microsoft corporation.All right reserved.\n\nThe Windows pro operating system and its user interface is protected by trademark and pending or existing intellectual property rights in United States and Other Countries regions. \n\n\n\n\n This Product is Licensed under the Microsoft Software License terms to user : Toshiba   ");
                alert.show();
            }
        });

        saveItem.addEventHandler(ActionEvent.ACTION, EventHandler
                -> {
            if (checkforsave == false) {
                saveFile();
            }

        }
        );
        openItem.addEventHandler(ActionEvent.ACTION, EventHandler -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            FileInputStream fis = null;
            alert.getButtonTypes().setAll(ButtonType.OK,
                    ButtonType.CLOSE,
                    ButtonType.CANCEL);

            alert.setTitle("Notepad");
            alert.setHeaderText(null);

            if (checkforsave == false) {
                System.out.println("");

                alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You want to Save your change " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    saveFile();
                } else if (alert.getResult() == ButtonType.NO) {
                    textArea.clear();
                } else if (alert.getResult() == ButtonType.CANCEL) {
                    alert.close();
                }
            } else {
                try {
                    FileChooser fc = new FileChooser();
                    File openFile = fc.showOpenDialog(null);
                    // FileReader fileReader;
                    checkforsave = true;
                    textArea.setText(openFile.getAbsolutePath());
                } //
                //                try {
                //                    fis = new FileInputStream(openFile);
                //                    byte[] data = new byte[(int) openFile.length()];
                //                    fis.read(data);
                //                    fis.close();
                //                    String s = new String(data, "UTF-8");
                //                    textArea.setText(s);
                catch (NullPointerException exp) {

                } //finally {
//                    try {
//                        fis.close();
//                    } catch (IOException ex) {
//                        //  Logger.getLogger(note.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                //}

            }

        });

        cutItem.addEventHandler(ActionEvent.ACTION, eventHandler
                -> {
            int startPos = textArea.getAnchor();
            int endPos = textArea.getCaretPosition();
            pasteItem.setDisable(false);
            System.out.println("Text Area Length " + textArea.getText().length());
            if (startPos > textArea.getText().length()) {
                startPos = textArea.getText().length();
            }
            if (startPos < endPos) {
                cutter = textArea.getText(startPos, endPos);
                System.out.println("cutter is = " + cutter);
                textArea.replaceText(startPos, endPos, "");

            } else {
                cutter = textArea.getText(endPos, startPos);
                System.out.println("Start Pos" + startPos);
                System.out.println("end Pos" + endPos);
                System.out.println("cutter is = " + cutter);
                if (startPos > endPos) {
                    textArea.replaceText(endPos, startPos, "");
                } else {
                    textArea.replaceText(textArea.getSelection(), "");
                }
            }

        });
        copyItem.addEventHandler(ActionEvent.ACTION, eventHandler
                -> {

            String txtAreaString = textArea.getText().toString();
            String selection = textArea.getSelectedText().toString();
            cutter = selection;
            pasteItem.setDisable(false);

        });
        deleteItem.addEventHandler(ActionEvent.ACTION, eventHandler
                -> {
            int startPos = textArea.getAnchor();
            int endPos = textArea.getCaretPosition();
            String txtAreaString = textArea.getText().toString();
            String selection = textArea.getSelectedText().toString();
            textArea.deleteText(startPos, endPos);

        });
        undoItem.addEventHandler(ActionEvent.ACTION, eventHandler
                -> {

            textArea.undo();

        });

        selectAllItem.addEventHandler(ActionEvent.ACTION, eventHandler
                -> {
            textArea.selectRange(0, textArea.getLength());

        });

        pasteItem.addEventHandler(ActionEvent.ACTION, eventHandler
                -> {

            int startPos = textArea.getAnchor();
            int endPos = startPos + cutter.length();
            //textArea.positionCaret(startPos);
            textArea.replaceText(textArea.getSelection(), cutter);
            System.out.println("Start Pos" + startPos);
            System.out.println("end Pos" + endPos);

        });

        //proprety Listner
        editMenu.setOnShowing((event) -> {
            boolean check = textArea.getSelectedText().isEmpty();
            if (check) {
                System.out.println("No");
                cutItem.setDisable(true);
                copyItem.setDisable(true);
                deleteItem.setDisable(true);
            } else {
                System.out.println("Yes");

                cutItem.setDisable(false);
                copyItem.setDisable(false);
                deleteItem.setDisable(false);
            }

        });

        ///
//        textArea.selectedTextProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
//
//                checkforsave = false;
//            }
//
//        });
        textArea.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkforsave = false;
                cutItem.setDisable(true);
                copyItem.setDisable(true);
                deleteItem.setDisable(true);

            }
        });

    }

    void errorAlert(String expection) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Error");
        alert.setContentText(expection);
        alert.show();

    }

    public void saveFile() {

        try {
            FileChooser fc;
            FileWriter fileWriter = null;
            fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));

            File saveFile = fc.showSaveDialog(null);
            String text = textArea.getText().toString();
            textArea.setText(text + saveFile.getAbsolutePath());
            checkforsave = true;
        } catch (NullPointerException exp) {

            // errorAlert(exp.getMessage());
            checkforsave = false;

        }

//        try {
//            fileWriter = new FileWriter(saveFile);
//            fileWriter.write(textArea.getText().toString());
//            fileWriter.close();
//        } catch (IOException exp) {
//            //errorAlert(exp.getMessage());
//        } catch (NullPointerException ex) {
//
//            System.out.println(ex.getMessage());
//        }
    }

    void checkFortextArea() {
        System.out.println(":::" + textArea.getText().toString().trim().length() + " ");

        if (textArea.getText().toString().trim().length() > 1) {
            cutItem.setDisable(false);
            copyItem.setDisable(false);
        } else {
            System.out.println("cut copy");
        }
    }

}
