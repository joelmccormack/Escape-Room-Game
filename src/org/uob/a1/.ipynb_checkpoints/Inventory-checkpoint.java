package org.uob.a1;

public class Inventory
{
    private String[] inventory;
    final int MAX_ITEMS = 10;
    private int itemCount;

    public Inventory()
    { 
        inventory = new String[MAX_ITEMS];
        itemCount = 0;
    }

    //adds item to the inventory array, after ensuring its not full, by looping through through the array until a empty space in the array and then adds the item
    public void addItem(String item)
    {
        if (itemCount < inventory.length)
        {
            for(int i = 0; i < inventory.length; i++)
                {
                    if(inventory[i] == null)
                    {
                        inventory[i] = item;
                        itemCount++;
                        break;
                    }
                }
        }
    }

    //returns the position of an item in the array, by looping through the inventory array until an item matches the item passed in as an argument, then returns its position as a n integer, if no item is found returns -1 instead
    public int hasItem(String item)
    {
        for(int i = 0; i < inventory.length; i++)
            {
                if(inventory[i] == item)
                {
                    return i;
                }
            }
        return -1;   
    }

    //removes a spceified item passes in as an argument, after this is removed all the following items are shuffled down to ensure there are no gaps in the array
    public void removeItem(String item)
    {
        boolean found = false; 
        if(itemCount != 0) //ensuring array isn't empty
        {
        for(int i = 0; i < inventory.length; i++)
            {
                if(inventory[i] == item && !found)
                {
                    inventory[i] = null;
                    itemCount--;     
                    found = true;
                }
                if(found && i < inventory.length -1) //if the item already removed then will shuffle down the next item to the next position
                {   
                    inventory[i] = inventory[i+1];
                }
            }
            inventory[inventory.length - 1] = null;
        }
        
    }

    // returns a string of each item in the array, between each item the a line is skipped
    public String displayInventory()
    {
        String result = "";
        for(int i = 0; i < inventory.length; i++)
            {
             if(inventory[i] != null)
               {
                  result += inventory[i] + " ";
               }
            }
        return result;
    }
                
                
}
