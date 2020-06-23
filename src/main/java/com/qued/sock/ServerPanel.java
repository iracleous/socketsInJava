package com.qued.sock;

import javax.swing.*;
import java.awt.*;

public class ServerPanel extends JPanel {
    private String message1="";
    private String message2="";
    private int x=0, y=0, size=10;

    public String answer(String question)
    {

        switch (question){
            case "left" :  x--; break;
            case "right" :  x++; break;
            case "up" :  y--; break;
            case "down" : y++; break;
            default:
        }
        repaint();
        return "Yes Sir. My new position is x= "+ x+ " y= "+y;
    }


    public void setMessage1(String newMessage){
        message1 = newMessage;
        repaint();
    }
    public void setMessage2(String newMessage){
        message2 = newMessage;
        repaint();
    }

  public void setX(int nx)
  {
      x = nx;
      repaint();
  }
    public void setY(int y)
    {
        this.y = y;
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // <-- HERE!

        g.drawString(message1,100,100 );
        g.drawString(message2,100,120 );
        g.drawOval(x,y, size, size);
    }

}
