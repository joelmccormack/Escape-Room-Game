package org.uob.a1;

public class Position
{
    public int x;
    public int y;    

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public String posToString() //returns the position as a string, mainly used to speed up debugging
    {
        return "(" + x + ", " + y + ")";
    }
}