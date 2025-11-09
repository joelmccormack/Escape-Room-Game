package org.uob.a1;

public class Map
{
   
    private int width;
    private int height;
    private char[][] map;
    final private char EMPTY = '.'; 

    public Map(int height, int width)
    {
        this.height = height;
        this.width = width;
        map = new char[height][width];
        for(int i = 0; i < height; i++) //filling in the 2d array with '.' as this is used to represent an unexplored room, using a nested for loop
            {
                for(int j = 0; j < width; j++)
                    {
                        map[i][j] = EMPTY;
                    }
            }
    }

    public void placeRoom(Position pos, char symbol) //replaces the '.' with the rooms symbol at its position on the map
    {
        map[pos.x][pos.y] = symbol;                  
    }

    public void display()//displays the 2d array to the user, by using a nessted for loop, prinitng out the character at each position in the 2d array, after each row a line is skipped to be displayed in a 2d manner
    {
        for(int i = 0; i < height; i++)
            {
                System.out.println("");
                for(int j = 0; j < width; j++)
                    {
                        System.out.print(map[i][j]);
                    }
            }
        System.out.println("");
    }
}