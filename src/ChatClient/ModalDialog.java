/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;


/**
 *
 * @author Zishan
 */
import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ModalDialog {
    Button btn;
    Label label;
    public ModalDialog(final Stage stg) {
        btn = new Button();
        label = new Label("File Successfully Downloaded");
    final  Stage stage = new Stage();           
        //Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
        //Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("Download Complete");
        Group root = new Group();
         Scene scene = new Scene(root, 70, 100, Color.GRAY);
        
         btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
             stage.hide();
             
             
                
            }
        });
        label.setLayoutX(15);
        label.setLayoutY(20);
        btn.setLayoutX(15);
        btn.setLayoutY(50);
        btn.setText("OK");
       
        root.getChildren().addAll(label,btn);   
        stage.setScene(scene);        
        stage.show();

    
    }
    
}
//public class ModalDialog {
//    
//}
