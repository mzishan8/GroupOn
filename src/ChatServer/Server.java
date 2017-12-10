package ChatServer;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZISHAN
 */
public class Server {
    private Socket client;
    private ServerSocket server;
    private ArrayList<Session> clientList;
    JdbcOdbc db;
    public Server(){
        try{
             server = new ServerSocket(2002);
             Thread discoveryThread = new Thread(DiscoveryThread.getInstance());
             discoveryThread.start();
             clientList = new ArrayList<>();
        }catch(Exception ex){
            System.err.println(ex);
        }
    }
    public void run() throws IOException{
        System.out.println("Server is Running ...");
        while(true){
            client = server.accept();
            db = new JdbcOdbc();
            new Thread(new ClientHandler(client,clientList,db)).start();
        }
    }
    public static void main(String args[]) throws IOException{
        Server ser = new Server();
        ser.run();
    }
}
class ClientHandler implements Runnable{
    private Socket soc;
    private Session client;
    private ArrayList<Session> clientList;
    JdbcOdbc db;
    ClientHandler(Socket soc , ArrayList<Session> clientList, JdbcOdbc db){
        client = new Session(soc);
        this.clientList = clientList;
        this.db=db;
        this.soc=soc;
        System.out.println("client Handler thread Is created");
    }
    @Override
    public void run() {
        String clientMsg = null;
        boolean accept = false;
        String clientIP = soc.getRemoteSocketAddress().toString();
        System.out.println("Client IP  ="+clientIP);
        do{
            clientMsg = client.read();
            if(clientMsg.startsWith("QUIT")){
                System.out.println("Client Logout Without Login");
                try {
                    if(client.disconnect())
                        return;
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(clientMsg.startsWith("GETPASSWORD: ")){
                try {
                    client.write( db.getPassword(clientMsg.split(" ")[1]));
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(clientMsg.startsWith("LOGIN: ")){
                String msg = clientMsg.split(" ")[1];
                String user = msg.split(",")[0];
                String pass = msg.split(",")[1];
                System.out.println("user "+user+"  "+"pass"+pass);
                try {
                    accept = authenticate(user , pass);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(clientMsg.startsWith("CHECKUSER: ")){
                String user = clientMsg.split(" ")[1];
               try{
                if(db.isUserExist(user))
                    client.write(user);
                else
                    client.write("*********");
               }catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(clientMsg.startsWith("NEWUSER: ")){
                try {
                    createUser(clientMsg);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }while(!accept);
      while(true){
          String msg = client.read();
          
            try {
                if(msg==null)
                    break;
                broadcast(client.getUserName()+" : "+msg);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
          System.out.println(msg);
      }
        try {
            exit();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void exit()throws IOException
    {
        String exitMsg="ChatServer: User "+client.getUserName();
        exitMsg+=" has left the chat.";
        broadcast(exitMsg);
        client.disconnect();
        clientList.remove(client);
        updateClientList();
        System.out.println("Log: Client socket closed, removed from client list");
    }
    public synchronized boolean authenticate(String user , String pass) throws IOException{
        boolean accept = false;
        try {
            System.out.println("Authenticate method Is Called");
               if(db.authentiction(user, pass)){
                   accept = true;
                   clientList.add(client);
                   client.setUserName(user);
                   client.write(user);
                   updateClientList();
                   System.out.println(client.getUserName()+"  Client Loged In ");
                   broadcast("ChatServer: User "+client.getUserName()+" Logged In");
                }
               else
                   client.write("*********");
            } catch (SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accept;
    }
   
    public synchronized void updateClientList() throws IOException{
        String userList = "USERLIST: ";
        for (Session clientList1 : clientList) {
            userList += "," + clientList1.getUserName();
        }
        broadcast(userList);
    }
    public synchronized void broadcast(String msg) throws IOException{
        for(int i = 0 ; i < clientList.size() ; i++){
                clientList.get(i).write(msg);
        }
        System.out.println("Message BroadCast"+msg);
    }
    public synchronized void createUser(String msg) throws IOException{
        String detail[] =msg.split(" ")[1].split(",");
        if(db.isUserExist(detail[0]))
            client.write("USEREXIST");
        else
            if(db.createUser(msg))
                client.write("USERCREATED");
    }
}
