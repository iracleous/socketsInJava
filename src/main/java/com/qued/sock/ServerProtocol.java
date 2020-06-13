package com.qued.sock;

public class ServerProtocol {

//Fictional car
    private int x;
    private int y;

    public String answer(String question)
    {

        if (question.equals("left"))  x--;
        if (question.equals("right"))  x++;
        if (question.equals("up"))  y--;
        if (question.equals("down"))  y++;
        return "Yes Sir. My new position is x= "+ x+ " y= "+y;
    }


}
