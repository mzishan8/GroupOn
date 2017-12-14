/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient.ChatRoom;

import ChatClient.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zishan
 */
public class CreateGroupController implements Initializable {

    @FXML
    TextField groupName;
    @FXML
    ListView userList;
    @FXML
    Button create;
    ObservableList<String> selectedUsers;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        create.setDisable(true);
        create.disableProperty().bind(
        Bindings.createBooleanBinding( () -> 
        groupName.getText().trim().isEmpty(), groupName.textProperty()
      )
   );
        selectedUsers = FXCollections.observableArrayList();
        groupName.setPromptText("Enter Group Name First");
        userList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    public void initData(ObservableList<String> users ){
       userList.getItems().addAll(users);
    }
    @FXML
    private void createButtonAction(ActionEvent event ) throws IOException
    {
        selectedUsers = userList.getSelectionModel().getSelectedItems();
        String msg = "GroupCreateRequest:"+groupName.getText()+" ";
        for(String users:selectedUsers){
            msg = msg+","+users;
        }
        Main.cl.write(msg);
        Stage stage = (Stage) create.getScene().getWindow();
        stage.close();
    }
   
}
