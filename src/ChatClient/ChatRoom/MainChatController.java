/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient.ChatRoom;

import ChatClient.Client;
import ChatClient.Main;
import ChatClient.ModalDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Zishan
 */
public class MainChatController implements Initializable{
     
    ObservableList observableList = FXCollections.observableArrayList();
    HashMap<String,ListView<GridPane>> userContainer;
    HashMap<String, String> groupContainer;
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
    @FXML
    Button send;
    @FXML
    TextArea message;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userContainer = new HashMap<>();
        groupContainer = new HashMap<>();
        chatPane.setVisible(false);
        fPPane.setVisible(false);
        chatPSeprator.setVisible(false);
       // Platform.runLater(() -> {
        userName.setText(Main.cl.getUserName());
        userList.setStyle("-fx-control-inner-background: gray; -fx-font-size: 14px; -fx-font-family: 'SketchFlow Print';-fx-font: bold italic 14pt \"Arial\"; ");
        groupList.setStyle("-fx-control-inner-background: gray; -fx-font-size: 14px; -fx-font-family: 'SketchFlow Print';-fx-font: bold italic 14pt \"Arial\"; ");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                   send.disableProperty().bind(
                  Bindings.createBooleanBinding( () -> 
                 message.getText().trim().isEmpty(), message.textProperty()
               )
             );
            }
        });

        //});
        new Thread (new ReadMsg(groupContainer,groupList,userList,observableList,userName.getText(),userContainer,chatPane)).start();
    }
    @FXML
    private void sendAttachment(ActionEvent event) throws IOException
    {
          //filechooser
         FileChooser fileChooser = new FileChooser();
         File file =fileChooser.showOpenDialog(null);
         fileChooser.setTitle("Attachment");
         if(file!=null)
         {
             if(groupContainer.get(chatUser.getText())!=null){
                 Main.cl.write("FILE IS SLECTED:"+file.getName()+":"+Main.cl.getUserName()+":"+"Group-"+chatUser.getText());
             }
             else{
              Main.cl.write("FILE IS SLECTED:"+file.getName()+":"+Main.cl.getUserName()+":"+chatUser.getText());
             }              
            //ObjectOutputStream oos = new ObjectOutputStream(Main.cl.getSocket().getOutputStream());
              FileInputStream fis = new FileInputStream(file);
              System.out.println("ChatClient.ChatRoom.MainChatController.sendAttachment()");
              byte[] buffer = new byte[fis.available()];
              fis.read(buffer);
              Main.cl.write(buffer);
              System.out.println("ChatClient.ChatRoom.MainChatController.sendAttachment()......");
              String destinationUser = chatUser.getText();
              ListView<GridPane> msgList = userContainer.get(destinationUser);
              GridPane gp = new GridPane();
              ColumnConstraints c1 = new ColumnConstraints();
              c1.setPercentWidth(100);
              gp.getColumnConstraints().add(c1);
              Label newLabel = new Label("Attachment");
              newLabel.setMaxWidth(400);
              newLabel.setWrapText(true);
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
              fis.close();
              
         }
         else
         {
              System.out.println("null selection");
         }
        
    }
    @FXML
    private void sendAction(ActionEvent event) throws IOException{
        if(!message.getText().trim().equals(null)){
        if(groupContainer.get(chatUser.getText())!=null)
        {
             Main.cl.write(message.getText()+";"+"Group-"+chatUser.getText());
        }
        else {
           Main.cl.write(message.getText()+";"+chatUser.getText());
        }
           String destinationUser = chatUser.getText();
           ListView<GridPane> msgList = userContainer.get(destinationUser);
           GridPane gp = new GridPane();
           ColumnConstraints c1 = new ColumnConstraints();
           c1.setPercentWidth(100);
           gp.getColumnConstraints().add(c1);
           Label newLabel = new Label(message.getText());
           //newLabel.setMaxWidth(400);
           newLabel.setWrapText(true);
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
   private void groupListClicked(MouseEvent e)
   {
         chatPane.setVisible(true);
         fPPane.setVisible(true);
         String groupName =  groupList.getSelectionModel().getSelectedItem();
         ListView<GridPane> selectedUserList = userContainer.get(groupName);
        for(Map.Entry selectedUserList1 : userContainer.entrySet()){
         ListView<GridPane> lis =   (ListView<GridPane>) selectedUserList1.getValue();
         lis.setVisible(false);
        }
        selectedUserList.setVisible(true);
        //System.out.println(user);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatUser.setText(groupName);//To change body of generated methods, choose Tools | Templates.
                
            }
        });
         
         
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
    ListView<String> groupList;
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
       HashMap<String, String> groupContainer;
       ObservableList observableList;
        Client client = Main.cl;     
        ListView<String> userList;
        HashMap<String, ListView<GridPane>> userContainer; 
        String userName;
        ListView<String> groupList;
        boolean flag = false;
        String path=null;
    public ReadMsg(HashMap<String, String> groupContainer,ListView<String> groupList,ListView<String> userList , ObservableList observableList,String userName,HashMap<String, ListView<GridPane>> userContainer , Pane chatPane) {
        this.chatPane = chatPane;
        this.groupContainer=groupContainer;
        this.groupList=groupList;
        this.userList= userList;
        this.userContainer = userContainer;
        this.observableList = observableList;
        this.userName = userName;
    }
        
    @Override
    protected Integer call() throws Exception {
        File selectedDirectory;
        String msg = null;
        while(true){ 
            synchronized(this){
             Object obj = client.read();
             if(flag){                         
                 byte[] buffer = (byte[]) obj;
                 System.out.println("buffer read");
                 try (FileOutputStream fos = new FileOutputStream(path)) {
                     System.out.println(Main.cl.read());
                     Platform.runLater(new Runnable() {
                         @Override
                         public void run() {
                             ModalDialog md =   new ModalDialog((Stage)chatPane.getScene().getWindow());//To change body of generated methods, choose Tools | Templates.
                         }
                     });
                     
                     fos.write(buffer);
                     fos.close();
                 }
                 flag = false;                                     
                 continue;
             }
             msg = obj.toString();
             System.out.println("int read thread  =  "+msg);
             if(msg.startsWith("Atch:"))
             {
                 String attach = msg.split(":")[1]+":"+msg.split(":")[0]+"@"+msg.split(":")[2]+";"+msg.split(":")[3];
                 writeSingleUser(attach);
             }
             else if(msg.startsWith("GroupCreateRequest:")){
                 msg=msg.split(":")[1];
                 createGroup(msg);
             }
             else if(msg.startsWith("USERLIST: ")){
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
                          newListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
                               GridPane gp = newListView.getSelectionModel().getSelectedItem();
                               newListView.getSelectionModel().clearSelection();
                               ObservableList observe = FXCollections.observableArrayList();
                               Label node = (Label)gp.getChildren().get(0);
                               String str = node.getText();
                               int indexof = str.indexOf(':');
                               
                               if(indexof!=-1){
                                   System.out.println(".handle() ...   : found   0000 ");
                                   str = str.split(":")[1];
                               }
                               if(str.startsWith("Atch@"))
                               {
                                str=str.split("@")[1];
                                DirectoryChooser chooser = new DirectoryChooser();
                                chooser.setTitle("Download");
//   
                                  
                                   //System.out.println("selected dir "+selectedDirectory.getAbsolutePath() );
                                   try {
                                       File selectedDirectory1 = chooser.showDialog(null);
                                       path=selectedDirectory1.getAbsolutePath()+"\\"+str;
                                       if(path !=null){
                                          flag =true;
                                          Main.cl.write("REQUEST FOR DOWNLOADFILE:"+str);
                                       }
                                      // newListView.setFocusModel(null);
                                   } catch (IOException ex) {
                                       Logger.getLogger(ReadMsg.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                               }
                              else
                              {
                                  System.out.println("not a file");
                               } //To change body of generated methods, choose Tools | Templates.
                            }
                        }); 
                          newListView.setPrefSize(585, 419);
                          newListView.setLayoutX(12);
                          newListView.setLayoutY(30);
                          newListView.setVisible(false);
              
                          Platform.runLater(() ->{
                          chatPane.getChildren().add(newListView);
                           //msgList.scrollTo(msgList.getItems().size()-1);
                     // newListView.setMouseTransparent( true );
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
    }
    synchronized private void createGroup(String msg)
    {
        String user[] = msg.split(",");
        String groupName = user[0] ;
        
        for(String str : user)
        {
            if(str.equals(groupName))
                continue;
            if(client.getUserName().equals(str)){
                  groupContainer.put(groupName,msg);
                   Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                            groupList.getItems().add(groupName);
                         }
                     });
                   ListView<GridPane> newListView =  new ListView<>();
                   newListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                               @Override
                               public void handle(MouseEvent event) {
                               GridPane gp = newListView.getSelectionModel().getSelectedItem();
                               newListView.getSelectionModel().clearSelection();
                               ObservableList observe = FXCollections.observableArrayList();
                               Label node = (Label)gp.getChildren().get(0);
                               String str = node.getText();
                               int indexof = str.indexOf(':');
                               
                               if(indexof!=-1){
                                   System.out.println(".handle() ...   : found   0000 ");
                                   str = str.split(":")[1];
                               }
                               if(str.startsWith("Atch@"))
                               {
                                str=str.split("@")[1];
                                DirectoryChooser chooser = new DirectoryChooser();
                                chooser.setTitle("Download");
//   
                                  
                                   //System.out.println("selected dir "+selectedDirectory.getAbsolutePath() );
                                   try {
                                       File selectedDirectory1 = chooser.showDialog(null);
                                       path=selectedDirectory1.getAbsolutePath()+"\\"+str;
                                       if(path !=null){
                                          flag =true;
                                          Main.cl.write("REQUEST FOR DOWNLOADFILE:"+str);
                                       }
                                      // newListView.setFocusModel(null);
                                   } catch (IOException ex) {
                                       Logger.getLogger(ReadMsg.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                               }
                              else
                              {
                                  System.out.println("not a file");
                               } //To change body of generated methods, choose Tools | Templates.
                            }
                        }); 
                          newListView.setPrefSize(585, 419);
                          newListView.setLayoutX(12);
                          newListView.setLayoutY(32);
                          newListView.setVisible(false);
                          Platform.runLater(() ->{
                          chatPane.getChildren().add(newListView);
                          });
                          userContainer.put(groupName,newListView);
            }
        }
        
    }
    synchronized private void writeSingleUser(String msg){
        synchronized(this){
                 System.out.println("ChatClien :msg   :"+msg);
                 String msgUser = msg.split(";")[1];
                 String destinationUser=null;
                 //System.out.println("ChatClien :msg   :"+msg);
                 if(msgUser.startsWith("Group-")){
                     msgUser = msgUser.split("-")[1];
   
                     writeInGroup(msg , msgUser);
                     return;
                 }
                 else{
                  destinationUser = msg.split(":")[0];
                  msg = msg.split(";")[0];
                  msg = msg.split(":")[1];
                 }
                 ListView<GridPane> msgList = userContainer.get(destinationUser);
                // System.out.println("ChatClien :msg after split :"+msg);
                 if(msgList!=null && msgUser.equals(client.getUserName())){
                    Label msgLabel = new Label(msg);
                    GridPane gp = new GridPane();
                    ColumnConstraints c1 = new ColumnConstraints();
                    c1.setPercentWidth(100);
                    gp.getColumnConstraints().add(c1);
                    Label newLabel = new Label(msg);
                    //newLabel.setMaxWidth(400);
                    newLabel.setWrapText(true);
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
    private void writeInGroup(String msg , String msgUser){
               msg =  msg.split(";")[0];
                 ListView<GridPane> msgList = userContainer.get(msgUser);
 
                // System.out.println("ChatClien :msg after split :"+msg);
                 if(msgList!=null && !msg.split(":")[0].equals(client.getUserName()) ){
                    Label msgLabel = new Label(msg);
                    GridPane gp = new GridPane();
                    ColumnConstraints c1 = new ColumnConstraints();
                    c1.setPercentWidth(100);
                    gp.getColumnConstraints().add(c1);
                    Label newLabel = new Label(msg);
                   // newLabel.setMaxWidth(400);
                    newLabel.setWrapText(true);
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
    }

    private byte[] obj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 }
   
    
