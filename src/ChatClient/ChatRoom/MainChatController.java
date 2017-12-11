/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient.ChatRoom;

import ChatClient.Client;
import ChatClient.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;

/**
 *
 * @author Zishan
 */
public class MainChatController implements Initializable{
     
    ObservableList observableList = FXCollections.observableArrayList();
    @FXML
    Pane chatPane;
    @FXML
    Pane fPPane;
    @FXML
    Separator chatPSeprator;
    @FXML
    ListView<String> userList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatPane.setVisible(false);
        fPPane.setVisible(false);
        chatPSeprator.setVisible(false);
       // ReadMsg read = new ReadMsg(userList);
        //userList.
        new Thread (new ReadMsg(userList)).start();
    }

}
class ReadMsg extends Task<Integer>{
    
       ObservableList observableList = FXCollections.observableArrayList();
        Client client = Main.cl;     
        ListView<String> userList;

    public ReadMsg(ListView<String> userList) {
        this.userList= userList;
    }
        
    @Override
    protected Integer call() throws Exception {
        while(true){
             String msg = client.read();
             if(msg.startsWith("USERLIST: ")){
                 observableList.clear();
                 String users[] = msg.split(" ")[1].split(",");
                 for(String str : users){
                     System.out.println(client.getUserName());
                     if(!str.equals(client.getUserName()) && !str.equals(null)){
                      observableList.add(str);
                     }
                 }
                 Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                         userList.getItems().clear();
                         userList.getItems().addAll(observableList);//To change body of generated methods, choose Tools | Templates.
                     }
                 });
                
                 
             }
        }    
    }
   
    
}