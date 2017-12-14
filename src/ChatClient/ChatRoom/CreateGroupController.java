/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient.ChatRoom;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        groupName.setPromptText("Enter Group Name");
    }    
    public void initData(ObservableList<String> users ){
       userList.getItems().addAll(users);
    }
}
