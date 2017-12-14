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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author Zishan
 */
public class MainChatController implements Initializable{
     
    ObservableList observableList = FXCollections.observableArrayList();
    HashMap<String,ListView<GridPane>> userContainer;
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
    Label chatUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userContainer = new HashMap<>();
        chatPane.setVisible(false);
        fPPane.setVisible(false);
        chatPSeprator.setVisible(false);
       // Platform.runLater(() -> {
        userName.setText(Main.cl.getUserName());
        //});
        new Thread (new ReadMsg(userList,observableList,userName.getText(),userContainer,chatPane)).start();
    }
    @FXML
    TextArea message;
    @FXML
    private void sendAction(ActionEvent event) throws IOException{
        if(!message.equals(null)){
           Main.cl.write(message.getText()+";"+chatUser.getText());
           String destinationUser = chatUser.getText();
           ListView<GridPane> msgList = userContainer.get(destinationUser);
           GridPane gp = new GridPane();
           ColumnConstraints c1 = new ColumnConstraints();
           c1.setPercentWidth(100);
           gp.getColumnConstraints().add(c1);
           Label newLabel = new Label(message.getText());
           message.setText("");
           newLabel.getStyleClass().add("chat-bubble-send");
           GridPane.setHalignment(newLabel, HPos.RIGHT);
           gp.addRow(0, newLabel);
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                   
                   msgList.getItems().add(gp);
                    msgList.scrollTo(msgList.getItems().size()-1);
                   msgList.setCellFactory(param -> new ListCell<GridPane>(){
                       {
                           prefWidthProperty().bind(msgList.widthProperty().subtract(2));
                            setMaxWidth(Control.USE_PREF_SIZE);
                       }

                       @Override
                       protected void updateItem(GridPane item, boolean empty) {
                           super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                           setGraphic(item);
                       }
                       
                   });     
               }
           });
        }     
    }
    @FXML
    private void listClicked(MouseEvent e){
        chatPane.setVisible(true);
        fPPane.setVisible(true);
        String user =  userList.getSelectionModel().getSelectedItem();
        ListView<GridPane> selectedUserList = userContainer.get(user);
        for(Map.Entry selectedUserList1 : userContainer.entrySet()){
         ListView<GridPane> lis =   (ListView<GridPane>) selectedUserList1.getValue();
         lis.setVisible(false);
        }
        selectedUserList.setVisible(true);
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
    @FXML
    ListView groupList;
    @FXML
    Button createGroup;
    @FXML
    private void createGroupAction(ActionEvent event) throws IOException{
        System.out.println("ChatClient.ChatRoom.MainChatController.createGroupAction()");
        ObservableList<String> avilableUserList  = FXCollections.observableArrayList();
        for(Map.Entry user:userContainer.entrySet())
            avilableUserList.add(user.getKey().toString());
        Main.showCreateGroupStage(avilableUserList);
    }
    
}
class ReadMsg extends Task<Integer>{
       Pane chatPane;
       ObservableList observableList;
        Client client = Main.cl;     
        ListView<String> userList;
        HashMap<String, ListView<GridPane>> userContainer; 
        String userName;
    public ReadMsg(ListView<String> userList , ObservableList observableList,String userName,HashMap<String, ListView<GridPane>> userContainer , Pane chatPane) {
        this.chatPane = chatPane;
        this.userList= userList;
        this.userContainer = userContainer;
        this.observableList = observableList;
        this.userName = userName;
    }
        
    @Override
    protected Integer call() throws Exception {
        while(true){
             String msg = client.read();
             System.out.println("int read thread  =  "+msg);
             if(msg.startsWith("USERLIST: ")){
               synchronized(this) {
                 observableList.clear();
                 String users[] = msg.split(" ")[1].split(",");
                 int i =0;
                 for(String str : users){
                     i++;
                     if(i==1){
                         continue;
                     }
                     System.out.println(str);
                     if(userContainer.get(str) == null){
                          System.out.println("why here ..... in  list creatin   ");
                          ListView<GridPane> newListView =  new ListView<>();
                          newListView.setPrefSize(585, 419);
                          newListView.setVisible(false);
                          Platform.runLater(() ->{
                          chatPane.getChildren().add(newListView);
                           //msgList.scrollTo(msgList.getItems().size()-1);
                      newListView.setMouseTransparent( true );
                     // newListView.setFocusTraversable( false );
                          });
                          userContainer.put(str,newListView);
                          System.out.println("why here ..... in  list creatin   ");
                      }
                     if(!str.equals(client.getUserName()) && !str.equals(null)){ 
                       System.out.println("why here ..... in user list updation   ");
                      observableList.add(str);
                     }
                 }
                 Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                         System.out.println("in runlator");
                         userList.getItems().clear();
                         userList.getItems().addAll(observableList);//To change body of generated methods, choose Tools | Templates.
                         System.out.println("in runlator after");
                     }
                 });     
               }
             }
             else if(msg.startsWith("ChatServer: ")){
                 ;
             }
            else{
                 writeSingleUser(msg);
             }
          }
        }
    synchronized private void writeSingleUser(String msg){
        synchronized(this){
                 System.out.println("ChatClien :msg   :"+msg);
                 String msgUser = msg.split(";")[1];
                 System.out.println("ChatClien :msg   :"+msg);
                 String destinationUser = msg.split(":")[0];
                 msg = msg.split(";")[0];
                 msg = msg.split(":")[1];
                 ListView<GridPane> msgList = userContainer.get(destinationUser);
                // System.out.println("ChatClien :msg after split :"+msg);
                 if(msgList!=null && msgUser.equals(client.getUserName())){
                    Label msgLabel = new Label(msg);
                    GridPane gp = new GridPane();
                    ColumnConstraints c1 = new ColumnConstraints();
                    c1.setPercentWidth(100);
                    gp.getColumnConstraints().add(c1);
                    Label newLabel = new Label(msg);
                    newLabel.getStyleClass().add("chat-bubble-recieve");
                    
                    GridPane.setHalignment(newLabel, HPos.LEFT);
                    gp.addRow(0, newLabel);
                    Platform.runLater(() -> { 
                      msgList.getItems().add(gp);
                      msgList.scrollTo(msgList.getItems().size()-1);
                      msgList.setCellFactory(param -> new ListCell<GridPane>(){
                       {
                           prefWidthProperty().bind(msgList.widthProperty().subtract(2));
                            setMaxWidth(Control.USE_PREF_SIZE);
                       }

                       @Override
                       protected void updateItem(GridPane item, boolean empty) {
                           super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                           setGraphic(item);
                       }
                       
                   });
                 });
                 }
                 else
                     System.out.println("ChatClient.ChatRoom.ReadMsg.call()  list update");
             }
    }
    }
   
    
