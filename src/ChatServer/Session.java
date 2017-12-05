package ChatServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    private BufferedReader dis;
    private PrintWriter dos;
    public Session(Socket soc){
        this.soc=soc;
        try {
            dis=new BufferedReader(new InputStreamReader(soc.getInputStream()));
            dos=new PrintWriter(soc.getOutputStream(),true);
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setUserName(String user){
        userName=user;
    }
    public void write(String msg) throws IOException{
        dos.println(msg);
        dos.flush();
    }
    public String read(){
        String msg = null;
        try {
            msg = dis.readLine();
           // while((msg = dis.readLine()) == null );
             System.out.println("Client message   "+msg);
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
