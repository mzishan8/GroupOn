/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Zishan
 */
public class Main extends Application {
   public static Client cl = null;
   public static BorderPane root;
    @Override
    public void start(Stage primaryStage) {
         try{
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
