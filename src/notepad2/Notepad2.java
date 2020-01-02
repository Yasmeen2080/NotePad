/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import static notepad2.note.checkforsave;
import sun.plugin2.ipc.windows.WindowsEvent;

/**
 *
 * @author Yasmeen
 */
public class Notepad2 extends Application {

    public static Stage stage;
    note notepad = new note() {
    };

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        Scene scene = new Scene(notepad, 300, 300);
        primaryStage.setTitle("Notepad..");
        primaryStage.setScene(scene);
        primaryStage.show();

        // primaryStage.fullScreenExitKeyProperty()
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
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
                        notepad.saveFile();
                    } else if (alert.getResult() == ButtonType.NO) {
                        System.exit(0);
                    }
                    else if(alert.getResult() == ButtonType.CANCEL){
                    System.out.println("cancel");
                    event.consume();
                }   
                }
                else
                {
                    
                }
            }
        });
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
