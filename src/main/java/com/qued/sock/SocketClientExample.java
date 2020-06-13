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


    private  static   Socket socket  ;
    private  static ObjectOutputStream oos  ;
    private static ObjectInputStream ois  ;
    private static InetAddress host  ;


    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        //get the localhost IP address, if server is running on some other IP, you need to use that
          host = InetAddress.getLocalHost();



        JFrame frame = new JFrame("Client");
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

                try {
                    sendMessageToServer(keyEvent.getKeyChar() + "");

                } catch (Exception e) {
                    e.printStackTrace();
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



    public static void   sendMessageToServer(String userMessage) throws Exception {
        //establish socket connection to server
        socket = new Socket(host.getHostName(), port);
        //write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());



        if (userMessage.equals("0") ) {
            oos.writeObject("exit");
            ois.close();
            oos.close();
            Thread.sleep(100);

            System.exit(0);
        }



        if (userMessage.equals("1") )      oos.writeObject("left");
        if (userMessage.equals("2") )      oos.writeObject("right");
        if (userMessage.equals("3") )      oos.writeObject("up");
        if (userMessage.equals("4") )      oos.writeObject("down");

        ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message: " + message);
        //close resources
        ois.close();
        oos.close();
        Thread.sleep(100);

    }






}
