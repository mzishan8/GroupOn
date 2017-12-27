/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import static ChatClient.Main.cl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Zishan
 */
public class SplashScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label label;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Splash().start();
    }    
    class Splash extends Thread{
        @Override
        public void run(){
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
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    try {
                        Main.mainWindow();
                    } catch (IOException ex) {
                        Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    label.getScene().getWindow().hide();
                });
                
        }
       //thread close
        
        }catch(Exception e){
            e.printStackTrace();
        }
        }
    }
}
