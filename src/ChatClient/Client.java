package ChatClient;


import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private ObjectInputStream dis;
    private ObjectOutputStream dos;
    Client client;
    String userName;
    /**
     *
     * @param user
     * @param pass
     * @throws java.lang.ClassNotFoundException
     */
    public boolean  login(String user , String pass) throws ClassNotFoundException {
        userName=user;
        System.out.println("Login Function of Cleint class Is Called");
        client = this;
        try{
            String msg = "LOGIN: "+user+","+pass;
            dos.writeObject(msg);
            String res = dis.readObject().toString();
            return res.equals(user); // JOptionPane.showMessageDialog(null, "You are Currect User");
            // rdth.start();
            //JOptionPane.showMessageDialog(null, "You are Not Currect User");
        }catch(IOException | HeadlessException ex){
            System.out.println(ex);
        }
        return false;
    }
    public Socket getSocket(){
        return soc;
    }
    public boolean connection(String ip , int port){
            try {
                    
                    soc=new Socket(ip,port);
                    System.out.println("ChatClient.Client.connection() ... client Socket is created ");
                    dos=new ObjectOutputStream(soc.getOutputStream());
                    dos.flush();
                    dis=new ObjectInputStream(soc.getInputStream());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                return soc!=null;
        }
    public Object read() throws IOException{
       Object msg=null;
        try {
            //Platform
            msg = dis.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("message  = "+msg.toString());
      // while((msg = dis.readLine())== null);
        return msg;
    }

    public void write(Object msg) throws IOException {
        dos.writeObject(msg);
        dos.flush();
    }
    public String getUserName()
    {
        return userName;
    }
    public String getPassword(String user){
        String msg = "GETPASSWORD: "+user;
        try {
            write(msg);
            String res = read().toString();
            if(!res.equals(null))
                return res;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
