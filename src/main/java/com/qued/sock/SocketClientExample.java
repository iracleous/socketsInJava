package com.qued.sock;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketClientExample {

    private static int port = 9000;

    private static InetAddress host;


    public static void main(String[] args)
            throws UnknownHostException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        host = InetAddress.getLocalHost();


        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

                try {
                    sendMessageToServer(keyEvent.getKeyChar() + "");

                } catch (Exception e) {
                    System.out.println("the server is not responding");
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });

    }


    public static void sendMessageToServer(String userMessage) throws Exception {


        //establish socket connection to server
        Socket socket = new Socket(host.getHostName(), port);
        //write to socket using ObjectOutputStream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


        switch (userMessage) {

            case "0":
                oos.writeObject("exit");
                oos.close();
                socket.close();
                Thread.sleep(100);
                System.exit(0);


            case "1":
                oos.writeObject("left");
                break;
            case "2":
                oos.writeObject("right");
                break;

            case "3":
                oos.writeObject("up");
                break;
            case "4":
                oos.writeObject("down");
                break;
            default:
                oos.writeObject("no action");
        }
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message: " + message);
        //close resources
        ois.close();
        oos.close();
        socket.close();
        Thread.sleep(100);

    }


}
