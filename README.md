# GroupOn

Chat Messenger in JAVAfx

It contain database connectivity in oracle11g
     database Tables
     Table 1 contains Registration Details and have Primary key which is userID
     Table 2 contains Login Table contain userId and Password

Features

single user Chatting + file transfer 
Group chatting + file Transfer 
Creating new Groups
ONLY for online User


Connectivity

UDP for findin the server in the LAN
TCP/IP connection for Chatting and File sending

Client:
Brodcasting the msg to find the server in LAN
Stream used for reading and writing
ObjectOutputStream 
ObjectInputStream 

Server:
Accepts the Broadcast Request for connection Establishment
Responcd to that perticular client


     
