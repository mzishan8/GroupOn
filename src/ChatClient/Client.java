package ChatClient;


import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    private BufferedReader dis;
    private PrintWriter dos;
    Client client;
    String userName;
    /**
     *
     * @param user
     * @param pass
     */
    public boolean  login(String user , String pass) {
        userName=user;
        System.out.println("Login Function of Cleint class Is Called");
        client = this;
        try{
            String msg = "LOGIN: "+user+","+pass;
            dos.println(msg);
            String res = dis.readLine();
            if(res.equals(user)){
                JOptionPane.showMessageDialog(null, "You are Currect User");
              return true;
               // rdth.start();
            }
            else{
                JOptionPane.showMessageDialog(null, "You are Not Currect User");
                return false;
            }
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
                    dis=new BufferedReader(new InputStreamReader(soc.getInputStream()));
                    dos=new PrintWriter(soc.getOutputStream(),true);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                return soc!=null;
        }
    public String read() throws IOException{
       String msg=dis.readLine();
      // while((msg = dis.readLine())== null);
        return msg;
    }

    public void write(String msg) throws IOException {
        dos.println(msg);
    }
    public String getUserName()
    {
        return userName;
    }
    public String getPassword(String user){
        String msg = "GETPASSWORD: "+user;
        try {
            write(msg);
            String res = read();
            if(!res.equals(null))
                return res;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
