package com.qued.sock;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketClientExample {

    private static int port = 9000;


    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        System.out.println(host.getHostAddress());
        System.out.println(host.getHostName());

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scanner = new Scanner(System.in);

        for(int i=0; i<5;i++){
            //establish socket connection to server
            socket = new Socket(host.getHostName(), port);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());


            System.out.println("Sending request to Socket Server");
            String userMessage;
            System.out.println("Give your message");
            userMessage = scanner.nextLine();

            if(i==4) oos.writeObject("exit");
                        else oos.writeObject(userMessage);
            if (userMessage.equals("exit"))   break;
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
