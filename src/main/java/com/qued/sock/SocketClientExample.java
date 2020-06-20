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


        ClientPanel gamePanel=new ClientPanel();

        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);

        frame.add(gamePanel);

        frame.setVisible(true);


    }



}
