package com.qued.sock;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class SocketServerExample {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9000;

    public static void main(String []args) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
       //    Scanner scanner = new Scanner(System.in);
    //    ServerProtocol protocol = new ServerProtocol();

        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        ServerPanel gamePanel=new ServerPanel();

        frame.add(gamePanel);
        frame.setVisible(true);


        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            gamePanel.setMessage1("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //multithreading server


            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            gamePanel.setMessage2("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            if(message.equalsIgnoreCase("exit")) {
                ois.close();
                oos.close();
                socket.close();
                break;
            }
       //     System.out.println("Give your answer");

            String answer = gamePanel.answer(message);
            oos.writeObject(answer);

            //close resources
            ois.close();
            oos.close();
            socket.close();


            //terminate the server if client sends exit request

        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

}
