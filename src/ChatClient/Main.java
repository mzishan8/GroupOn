/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import ChatClient.ChatRoom.CreateGroupController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("SplashScreen.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("GroupOn");
        Scene newScene = new Scene(root);
        primaryStage.setScene(newScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
    public static void mainWindow() throws IOException{
        primaryStage = new Stage();
         primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
         @Override
        public void handle(WindowEvent e) {
          
             try {
                 Main.cl.write("exit@rr");
             } catch (IOException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
          Platform.exit();
          System.exit(0);
       }
    });
       
       // StackPane root = new StackPane();
          root = FXMLLoader.load(Main.class.getResource("Main.fxml"));
          Scene scene = new Scene(root);
          //primaryStage.getIcons().add(new Image("aplication_icon.png"));
          primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("aplication_icon.png")));
          primaryStage.setTitle("GroupOn");
          primaryStage.setScene(scene);
          primaryStage.show();
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
