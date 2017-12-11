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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        new Thread (new ReadMsg(userList)).start();
    }
    
}
class ReadMsg implements Runnable{
        Client client = Main.cl;
        ListView<String> userList;
        ObservableList observableList = FXCollections.observableArrayList();
       // ObservableList oobservableList = FXCollections.observableArrayList();
        public ReadMsg(ListView<String> userList){
           this.userList = userList;
        }
        @Override
        public void run() {
            try{
                while(true){
                    String msg = client.read();
                    if(msg.startsWith("USERLIST: ")){
                        //System.out.println("Hello");
                        String users[] = msg.split(" ")[1].split(",");
                         for(int i=0;i<users.length;i++)
                            System.out.println(users[i]);
                         
                        observableList.addAll((Object) users);
                        userList.setItems(null);
                        userList.setItems(observableList);
                       
                      /* */
                       
                    }
                    else if(msg.startsWith("ChatServer: "))
                            {
                        
                    }
                    else{
                      
                       
                     
                        /*String oldMsg = msgBox.getText();
                        msgBox.setText( oldMsg+"\n"+msg);*/
                    }
                }
            }catch(Exception ex){
                System.out.println(ex);
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
}