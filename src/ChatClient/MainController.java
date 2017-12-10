/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Zishan
 */
public class MainController {
    @FXML
    TextField user = new TextField();
    @FXML
    PasswordField pass = new PasswordField();
    
    /**
     *
     * @param event
     */
    @FXML
    private void loginAction(ActionEvent event){
        System.out.println(pass.getText());
        Main.cl.login(user.getText(), pass.getText());
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
                res = Main.cl.read();
                if(res.equals("USEREXIST"))
                     JOptionPane.showMessageDialog(null, "User Exist");
                else{
                     JOptionPane.showMessageDialog(null, "User Created");
                    // registerPanel.setVisible(false);
                    // loginPanel.setVisible(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            //dispose();
       }
    }
}
