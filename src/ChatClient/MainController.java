/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Zishan
 */
public class MainController implements Initializable {
    @FXML
    TextField user = new TextField();
    @FXML
    PasswordField pass = new PasswordField();
    @FXML
    Label warning;
    
    /**
     *
     * @param event
     */
    @FXML
    private void loginAction(ActionEvent event) throws IOException{
        System.out.println(pass.getText());     
     boolean flag=false;
        try {
            flag = Main.cl.login(user.getText(), pass.getText());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
     if(flag){
         
         Main.loadChatWindo();
     }
     else{
         pass.setText("");
         user.setText("");
         warning.setVisible(true);
     }
     //String password = Main.cl.getPassword(user.getText());
       //System.out.println(user.getText()+" "+password);
    }
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField emailID;
    @FXML
    private PasswordField rpassword;
    @FXML
    private PasswordField cunfirmPassword;
    @FXML
    private TextField mobile;
    @FXML
    private TextField userName;
   @FXML 
    private void registerAction(ActionEvent event){
        String p1;
        p1 = rpassword.getText();
        String p2 = cunfirmPassword.getText();
        String res=null;
        if(!p1.equals(p2)){
            JOptionPane.showMessageDialog(null, "Password Not Matched");
        }
       else if(p1.length()<8){
            JOptionPane.showMessageDialog(null, "Password Length should be minimum 8 characters");
        }
       else{
            try {
                Main.cl.write("NEWUSER: "+firstName.getText()+","+lastName.getText()+","+emailID.getText()+","+userName.getText()+","+p1+","+mobile.getText());
                res = Main.cl.read().toString();
                if(res.equals("USEREXIST"))
                     JOptionPane.showMessageDialog(null, "User Exist");
                else{
                     JOptionPane.showMessageDialog(null, "User Created");
                    // registerPanel.setVisible(false);
                    // loginPanel.setVisible(true);
                }
            } catch (IOException ex) {
                System.out.print(ex);
                        
            }
            //dispose();
       }
    }
    File file;
    @FXML
    Label fileName;
    @FXML
    private void chooseImageAction(ActionEvent event){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilejpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg" );
        FileChooser.ExtensionFilter extFileJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG" );
        FileChooser.ExtensionFilter extFilepng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png" );
        FileChooser.ExtensionFilter extFilePNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG" );
        chooser.getExtensionFilters().addAll(extFilejpg,extFileJPG,extFilepng,extFilePNG);
        file =chooser.showOpenDialog(null);
        fileName.setWrapText(true);
        fileName.setText(file.getPath());
        
    } 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warning.setVisible(false);
    }
   
}
