package ChatServer;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public ObjectOutputStream dos;
    public ObjectInputStream dis;
    public Session(Socket soc){
        this.soc=soc;
        try {   
            
            dos=new ObjectOutputStream(soc.getOutputStream());
            dos.flush();
            dis=new ObjectInputStream(soc.getInputStream());
            System.out.println("object stream created");
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setUserName(String user){
        userName=user;
    }
    public void write(Object msg) throws IOException{
        dos.writeObject(msg);
        dos.flush();
    }
   
    public Object read(){
        Object msg = null;
        try {
            msg = dis.readObject();
           // while((msg = dis.readLine()) == null );
             System.out.println("Client message   "+msg.toString());
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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
