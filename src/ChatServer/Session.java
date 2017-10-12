package ChatServer;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZISHAN
 */
public class Session {
    private Socket soc;
    private String userName;
    private DataInputStream dis;
    private DataOutputStream dos;
    public Session(Socket soc){
        this.soc=soc;
        try {
            dis=new DataInputStream(soc.getInputStream());
            dos=new DataOutputStream(soc.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setUserName(String user){
        userName=user;
    }
    public void write(String msg) throws IOException{
        dos.writeUTF(msg);
        dos.flush();
    }
    public String read(){
        String msg = null;
        try {
             msg= dis.readUTF();
             System.out.println("Client message  "+msg);
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    public boolean disconnect() throws IOException{
        try {
            soc.close();
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        dos.close();
        return true;
    }
    public Socket getSocket(){
        return soc;
    }
    public String getUserName(){
        return userName;
    }
    
}
