package com.qued.sock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientPanel extends JPanel implements KeyListener {

    private static int port = 9000;
    private  InetAddress host;
    private String message="";

    public  ClientPanel () throws UnknownHostException {
        host = InetAddress.getLocalHost();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        try {
            sendMessageToServer(keyEvent.getKeyChar() + "");

        } catch (Exception e) {
            message = "the server is not responding";
            repaint();
            if ((keyEvent.getKeyChar()+ "").equals("0"))    System.exit(0);

        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // <-- HERE!

        g.drawString(message,100,100 );
    }


    public   void sendMessageToServer(String userMessage) throws Exception {


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
        message = (String) ois.readObject();
        message ="Message: " + message;
        //close resources
        ois.close();
        oos.close();
        socket.close();
//        invalidate();
//        validate();
//        revalidate();

        repaint();
        Thread.sleep(100);

    }






}