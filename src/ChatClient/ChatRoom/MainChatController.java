/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient.ChatRoom;

import ChatClient.Client;
import ChatClient.Main;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author Zishan
 */
public class MainChatController implements Initializable{
     
    ObservableList observableList = FXCollections.observableArrayList();
    @FXML
    Pane chatPane;
    @FXML
    Label userName;
    @FXML
    Pane fPPane;
    @FXML
    Separator chatPSeprator;
    @FXML
    ListView<String> userList;
    @FXML
    ListView<Label> msgList;
    @FXML
    Label chatUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatPane.setVisible(false);
        fPPane.setVisible(false);
        chatPSeprator.setVisible(false);
       // Platform.runLater(() -> {
            userName.setText(Main.cl.getUserName());
        //});
        
       // ReadMsg read = new ReadMsg(userList);
        //userList.
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                userList.setOnMouseClicked(e ->{
//            listClicked();
//        });//To change body of generated methods, choose Tools | Templates.
//            }
//        });
        
        new Thread (new ReadMsg(userList,msgList)).start();
    }
    @FXML
    TextField message;
    @FXML
    private void sendAction(ActionEvent event) throws IOException{
        if(!message.equals(null)){
           Main.cl.write(message.getText());
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                   msgList.getItems().add(new Label(message.getText()));
               }
           });
        }     
    }
    @FXML
    private void listClicked(MouseEvent e){
        chatPane.setVisible(true);
        fPPane.setVisible(true);
        String user =  userList.getSelectionModel().getSelectedItem();
        //System.out.println(user);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatUser.setText(user);//To change body of generated methods, choose Tools | Templates.
            }
        });    
    }
    @FXML
    ImageView myPic;
    @FXML
    private void profilePicSet(MouseEvent e) throws MalformedURLException{
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilejpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg" );
        FileChooser.ExtensionFilter extFileJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG" );
        FileChooser.ExtensionFilter extFilepng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png" );
        FileChooser.ExtensionFilter extFilePNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG" );
        chooser.getExtensionFilters().addAll(extFilejpg,extFileJPG,extFilepng,extFilePNG);
        File file =chooser.showOpenDialog(null);
        //Image img = new Image(file.getAbsolutePath());
        URL url = file.toURI().toURL();
        myPic.setImage(new Image(url.toExternalForm()));
//        Platform.runLater(() -> {
//            myPic.setImage(img);
//        });
    }
    
}
class ReadMsg extends Task<Integer>{
    
       ObservableList observableList = FXCollections.observableArrayList();
       ObservableList<Label> observableMsgList = FXCollections.observableArrayList();
        Client client = Main.cl;     
        ListView<String> userList;
        ListView<Label> msgList;
    public ReadMsg(ListView<String> userList, ListView<Label> msgList) {
        this.userList= userList;
        this.msgList = msgList;
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
             else if(msg.startsWith("ChatServer: ")){
                 
             }
             else{
                 Label msgLabel = new Label(msg);
                 Platform.runLater(() -> {
                      msgList.getItems().add(msgLabel);
                 });
             }
        }    
    }
   
    
}