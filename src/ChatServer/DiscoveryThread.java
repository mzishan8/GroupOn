/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatServer;

/**
 *
 * @author Zishan
 */
import java.net.*;
public class DiscoveryThread implements Runnable{

    DatagramSocket socket;
    @Override
    public void run() {
        try{
            socket = new DatagramSocket(2001,InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);
            while(true){
                byte[] recvBuf = new byte[1500];
                DatagramPacket packet = new DatagramPacket(recvBuf,recvBuf.length);
                socket.receive(packet);
                String message = new String(packet.getData()).trim();
                if(message.equals("DISCOVER_FULFILL_SERVER_REQUEST")){
                    byte[] sendData = "DISCOVER_FULFILL_SERVER_RESPONCE".getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData , sendData.length,packet.getAddress(),packet.getPort());
                    socket.send(sendPacket);
                }
            }
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static DiscoveryThread getInstance() {
    return DiscoveryThreadHolder.INSTANCE;
  }

  private static class DiscoveryThreadHolder {

    private static final DiscoveryThread INSTANCE = new DiscoveryThread();
  }
}
