package ChatClient;


import java.awt.HeadlessException;
import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZISHAN
 */
public class Client {
    Socket soc=null;
    DataOutputStream dos=null;
    DataInputStream dis=null;
    /**
     *
     * @param user
     * @param pass
     */
    public void  login(String user , String pass) {
        System.out.println("Login Function of Cleint class Is Called");
        try{
            String msg = "LOGIN: "+user+","+pass;
            dos.writeUTF(msg);
            System.out.println("Message Sent to server");
            String res = dis.readUTF();
            if(res.equals(user)){
                JOptionPane.showMessageDialog(null, "You are Currect User");
               // rdth.start();
            }
            else{
                JOptionPane.showMessageDialog(null, "You are Not Currect User");
            }
        }catch(IOException | HeadlessException ex){
            System.out.println(ex);
        }
    }
    public boolean connection(String ip , int port){
            try {
                    soc=new Socket(ip,port);
                    dos = new DataOutputStream(soc.getOutputStream());

                    dis = new DataInputStream(soc.getInputStream());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                return soc!=null;
        }
    public String read() throws IOException{
        return dis.readUTF();
    }

    public void write(String string) throws IOException {
        dos.writeUTF(string);
    }
}
