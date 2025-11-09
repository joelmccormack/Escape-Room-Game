package org.uob.a1;

public class Room
{
    private String name;
    private String description;
    private char symbol;
    private Position position;
    private boolean locked; //decides whether the player can move to this room

    public Room(String name, String description, char symbol, Position position)
    {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;

        //to start the game only the top row of rooms is accessible, the player will have to figure out how to unlock the next level of floors
        if(position.x == 0)
        {
            locked = false;
        }
        else
        {
            locked = true;
        }
    }

    public String getName() //returns the name of the room
    {
        return name;
    }
    public String getDescription() //returns a description of the room
    {
        return description;
    }
    public char getSymbol() //returns the symbol for the room
    {
        return symbol;
    }
    public Position getPosition() //returns the position of the room
    {
        return position;
    }
    public boolean isLocked() //returns whether the room is locked or not as a boolean
    {
        return locked;
    }
    public void unlockRoom() //assigns the room as now being unlocked and accessible
    {
        locked = false;
    }
}