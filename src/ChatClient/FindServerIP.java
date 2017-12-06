/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zishan
 */
public class FindServerIP {
    public static String serverIP;
    String serverPort;
    public FindServerIP() throws SocketException, IOException{
        DatagramSocket dsoc = new DatagramSocket(); 
        dsoc.setBroadcast(true);
        byte[] sendData = "DISCOVER_FULFILL_SERVER_REQUEST".getBytes();
        try {
            DatagramPacket sendPackat = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888 );
            dsoc.send(sendPackat);
            System.out.println("request broadcost :");
        } catch (UnknownHostException ex) {
            Logger.getLogger(FindServerIP.class.getName()).log(Level.SEVERE, null, ex);
        }
        Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()){
            NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();
            if(networkInterface.isLoopback() || !networkInterface.isUp()){
                continue;
            }
            for(InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()){
                InetAddress broadcast = interfaceAddress.getBroadcast();
                if(broadcast == null){
                    continue;
                }
                try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast , 2001 );
                    dsoc.send(sendPacket);
                } catch (IOException ex) {
                    Logger.getLogger(FindServerIP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        byte[] recvBuf = new byte[1500];
        DatagramPacket recvPacket = new DatagramPacket(recvBuf,recvBuf.length);
        dsoc.receive(recvPacket);
        String message = new String (recvPacket.getData()).trim();
        System.out.println("Message from server     ="+message);
        if(message.equals("DISCOVER_FULFILL_SERVER_RESPONCE")){
            serverIP = recvPacket.getAddress().toString();
        }
        System.out.println("server ip   ="+serverIP.split("/")[1]);
        dsoc.close();
    } 
}
