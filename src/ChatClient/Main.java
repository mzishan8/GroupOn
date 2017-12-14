/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import ChatClient.ChatRoom.CreateGroupController;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Zishan
 */
public class Main extends Application {
   public static Client cl = null;
   public static BorderPane root;
   public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
         try{
            this.primaryStage = primaryStage;
            new FindServerIP();
            String server_ip1;
            server_ip1 = FindServerIP.serverIP.split("/")[1];
            cl=new Client();
            System.out.println("connection is creating   :"+server_ip1 );
            if(!cl.connection(server_ip1,2002)) {
            JOptionPane.showMessageDialog(null, "Conection Not Created");
            System.exit(0);
            
        } else {
            JOptionPane.showMessageDialog(null, "Conection Created");
        }
       //thread close
         primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
         @Override
        public void handle(WindowEvent e) {
          Platform.exit();
          System.exit(0);
       }
    });
       
       // StackPane root = new StackPane();
          root = FXMLLoader.load(getClass().getResource("Main.fxml"));
          Scene scene = new Scene(root);
        
          primaryStage.setTitle("GroupOn");
          primaryStage.setScene(scene);
          primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void loadChatWindo() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ChatRoom/MainChat.fxml"));
        BorderPane chat = loader.load();
        root.setCenter(chat);
    }
    public static void showCreateGroupStage(ObservableList<String> avilableUserList) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ChatRoom/CreateGroup.fxml"));
        BorderPane createGroup = loader.load();
        Stage addDailogStage = new Stage();
        addDailogStage.setTitle("Create Group");
        addDailogStage.initModality(Modality.WINDOW_MODAL);
        addDailogStage.initOwner(primaryStage);
        Scene scene = new Scene(createGroup);
        addDailogStage.setScene(scene);
        CreateGroupController controller = loader.<CreateGroupController>getController();
        controller.initData(avilableUserList);
        addDailogStage.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
