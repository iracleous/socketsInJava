package com.qued.sock;

public class ServerProtocol {

//Fictional car
    private int x;
    private int y;

    public String answer(String question)
    {

        switch (question){
            case "left" :  x--; break;
            case "right" :  x++; break;
            case "up" :  y--; break;
            case "down" : y++; break;
            default:
        }

        return "Yes Sir. My new position is x= "+ x+ " y= "+y;
    }


}
