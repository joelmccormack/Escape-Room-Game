package org.uob.a1;

import java.util.Scanner; 

public class Game
{  
    public static Scanner inputDevice = new Scanner(System.in);
    public static Map map = new Map(3,4); //initialising a Map object with size 4x3, so will contain 12 rooms       
    public static Inventory inventory = new Inventory(); //initialising an Inventory which is empty to start with     
    public static Score score = new Score(10); //initialsing a score with a starting score of 10
    public static Position currentPosition = new Position(0,0); //intisialising a position for the players position starting at (0,0) which will change as the players moves
    public static Room[] rooms = new Room[12]; //creating an array of rooms, of size 12 - as there will be 12 rooms
    public static Room currentRoom; //variable which will represent the current room of the user
    
    public static void main(String args[]) 
    {        
        //creating 12 rooms and adding them all to the rooms array
        rooms[0] = (new Room("Entrance Room","This is the Entrance Room where you begin your journey from here you can enter to the Hallway and there forward to the Kitchen then Living Room, by moving east",'O',new Position(0,0)));
        map.placeRoom(rooms[0].getPosition(), rooms[0].getSymbol());
       
        currentRoom = rooms[0]; //setting the Entrance room as the current room
        
        rooms[1] = (new Room("Hallway","This is the Hallway it contains a locked door to the next level of floors, you cannot access these until you have unlocked the door, there are no other features or items in this room.\nFeature = 'door'",'H',new Position(0,1)));                
        rooms[2] = (new Room("Kitchen","This is the Kitchen, east of this is the Front Room, and there are some polls lying on the floor in here.\nItem = 'polls'",'K',new Position(0,2)));
        rooms[3] = (new Room("Front Room","This is the Front Room, you cannot move any further east of here, however there is a key in here which could come in handy.\nItem = 'key'",'F',new Position(0,3)));
        rooms[4] = (new Room("Studio","Welcome to the Studio, this studio contains a book with some valuable information.Item = 'book'",'S',new Position(1,0)));
        rooms[5] = (new Room("Library","Welcome to the Library, there is a secret door here which requires a secret code. Complete the puzzle to gain entrance to the Attic, bedroom, and Garden.\nEnter command 'interact-puzzle1' to attempt and complete, however you may be missing some valuable information",'L',new Position(1,1)));
        rooms[6] = (new Room("Toilet","This is just a Toilet",'T',new Position(1,2)));
        rooms[7] = (new Room("Cellar","Welcome to the Cellar, there is a chest in the corner of the room which may hold some valuable information.\nFeature = 'chest'",'C',new Position(1,3)));
        rooms[8] = (new Room("Attic","This is the Attic, it contains a toolbox.\nItem = 'toolbox'",'A',new Position(2,0)));
        rooms[9] = (new Room("Bedroom","This is the bedroom, theres nothing in here which you can interact with",'B',new Position(2,1)));
        rooms[10] = (new Room("Garden","Welcome to the Garden, this is where your last challenge stands to make it to the Exit Room, make sure you have with you all the items to build a ladder, and then solve the puzzle to exit.\nEnter command 'interact-puzzle2' to attempt and complete'",'G',new Position(2,2)));
        rooms[11] = (new Room("Exit Room","Welcome to the Exit Room, and welcome to freedom. Congratulations on esaping the house!\nEnter command 'exit'",'E',new Position(2,3)));

        menu();        
    }

 
    public static void menu() //original screen displayed when program is run which is the menu
    {
        String choice = "";
        while(!(choice.equals("1")))
            {
                 clear();
                 System.out.println("Welcome to Escape Room: \n\n1.Start/Resume Game\n2.Read game description and rules\n\nEnter 1 or 2: ");
                 choice = inputDevice.nextLine().toLowerCase();
                 if(choice.equals("1"))
                    {
                        gameLoop();
                    }
                 else if(choice.equals("2"))
                    {
                        clear();
                        System.out.println("Escape Room\nIn this game you have to navigate your way through the house, using the map to locate where you are, you will need to perform puzzles, search chests, add items to your inventory to use later on and progress through, once you escape the house you win.\nCommands:\n1.move-(north/south/east/west): will move to new room in the direction specified if the room is accessible and that their is a room. \n2.look: displays a description of the current room \n3.look.f-(feature): displays a description of a feature within that room \n4.look.i-(item): will display a description of the item specified if it is in the same room as you \n5.invenotry: displays your inventory as a list of items \n6.score: will display your current score \n7.map: will display a text based map \n8.interact-(item/feature/puzzle): to interact with an item or feature if possible, eg picking up an item or searching a chest \n9.help: will display a help message \n10.quit: will quit game and return you to the menu\n11.clear: will clear the screen for you\n12.exit: enter this when you have reached the Exit Room to escape");
                        System.out.println("\n\nEnter anything to return to menu");
                        inputDevice.nextLine();
                    } 
            }       
    }
    
 
    public static void gameLoop() //main game loop. is run when user selects to start the game in the menu
    {
        clear();
        boolean gameOver = false;
        System.out.println("Welcome, good luck. Type the command quit at anytime to return to menu.\nThe map is displaye below and you Start in the entrance room, with symbol O.");
        map.display();
        while(!gameOver) //while the user hasn't won the game
            {
                System.out.println("Current room = " + currentRoom.getSymbol() + "\nEnter Command (ensure if the command has extra info, to add the - between the command and the info with no spaces): ");
                String text = inputDevice.nextLine();
                String[] parts = text.split("-"); //splitting the command into 2 parts incase extra info was entered
                String command = parts[0];
                String info = "";
                if(parts.length > 1)
                {
                    info = parts[1];
                }
                switch(command.toLowerCase()) //switch case for all the commands the user can give
                    {
                        case"move":
                            move(info);                                
                            break;
                            
                        case"look":
                            System.out.println(currentRoom.getDescription());                            
                            break;
                            
                        case"look.f":
                            lookFeatures(info);                           
                            break;

                        case"look.i":
                            lookItems(info);
                            break;
                            
                        case"inventory":
                            System.out.println(inventory.displayInventory());
                            break;   
                            
                        case"score":
                            double points = score.getScore();
                            System.out.println("your score: " + points);
                            break;
                            
                        case"map":
                            map.display();
                            break;
                            
                        case"interact":
                            interact(info);
                            break;
                            
                        case"help": //different help lines depending how far through the game the player is
                            if(rooms[4].isLocked())
                            {
                                System.out.println("Look for a key to unlock the hallway door");
                            }
                            else if(rooms[8].isLocked())
                            {
                                System.out.println("look for a chest it contains some information to help you solve the library puzzle to gain access to the next rooms");
                            }
                            else
                            {
                                System.out.println("make sure you have all 3 required items to allow you to solve the puzzle to escape, good luck");
                            }
                            break;
                            
                        case"quit":
                            menu();
                            break;
                            
                        case"clear":
                            clear();
                            break;

                        case"exit":
                            if(currentRoom.getSymbol() == 'E')
                            {
                                gameOver = true;
                                System.out.println("Congratulations on escaping\nFinal score = " + score.getScore());
                            }
                            break;
                      default:
                            System.out.println("invalid input");
                            break;                                
                    }                       
            }  
    }

  
    public static void move(String direction) //the method performed when the user enters the move command, changes the position of the user depending on the direction entered, will only move if the room at the new position is unlocked and that there is actually a room there.
    {
        switch(direction.toLowerCase()) //switch case for the different directions entered
        {
            case"west":
                if(currentPosition.y != 0) 
                {
                    if(!((getRoom(new Position(currentPosition.x, currentPosition.y - 1))).isLocked()))
                    {
                        currentPosition.y--;
                        currentRoom = getRoom(currentPosition);
                        score.visitRoom();
                    }
                    else
                    {
                        System.out.println("room is locked, find a way to unlock this room");
                    }
                }
                break;
                
            case"east":
                if(currentPosition.y != 3) 
                {
                    if(!((getRoom(new Position(currentPosition.x, currentPosition.y + 1))).isLocked()))
                    {
                    currentPosition.y++;
                    currentRoom = getRoom(currentPosition);
                        score.visitRoom();
                    }
                    else
                    {
                        System.out.println("room is locked, find a way to unlock this room");
                    }                     
                }                                    
                break;
                
            case"north":
                if(currentPosition.x != 0) 
                {
                    if(!((getRoom(new Position(currentPosition.x-1, currentPosition.y))).isLocked()))
                    {
                    currentPosition.x--;
                    currentRoom = getRoom(currentPosition);
                        score.visitRoom();
                    }
                    else
                    {
                        System.out.println("room is locked, find a way to unlock this room");
                    }                        
                }                                                            
                break;
                
            case"south":
                if(currentPosition.x != 2) 
                {
                    if(!((getRoom(new Position(currentPosition.x+1, currentPosition.y))).isLocked()))
                    {
                    currentPosition.x++;      
                    currentRoom = getRoom(currentPosition);
                        score.visitRoom();
                    }
                    else
                    {
                        System.out.println("room is locked, find a way to unlock this room");
                    }                        
                }                
                break;
                
            default:
                System.out.println("invalid input");
                break;
        }
        map.placeRoom(currentRoom.getPosition(), currentRoom.getSymbol()); //adding this room to the map
    }

  
    public static void lookFeatures(String feature) //method called when user enters look.f command, displays description for a feature
    {
        switch(feature.toLowerCase()) //switch case for the different features user could be asking to look at, ensures user is in  correct room to look at each feature
        {
                case"chest":
                if(currentRoom.getSymbol() == 'C')
                    {
                        System.out.println("This is the chest from the cellar, it may contain some valueable information, if you dare to open, by using the interact-(item/feature) command");
                    }
                else
                    {
                        System.out.println("feature is not in this room");
                    }
                break;
                case"door":
                if(currentRoom.getSymbol() == 'H')
                {
                    System.out.println("This is the locked door in the hallway, open me and you will be able to access the next level of rooms and progress closer to the exit, however i require a certain item to open");
                }
                else
                {
                    System.out.println("Feature is not in this room");
                }
                break;

            default:
                System.out.println("Invalid feature");
                break;
        }
    }


    public static void lookItems(String item) //method called when user enters look.i command, displays a description of the item entered
    {
        switch(item.toLowerCase()) //switch case for different possible items being looked at, ensures user is in the correct room to look at the item.
        {
                case"key":
                if(currentRoom.getSymbol() == 'F')
                    {
                        System.out.println("This is the Key from the Front Room, you can add this to your inventory by using the interact-(item/feature) command ");
                    }
                else
                    {
                        System.out.println("Item is not in this room");
                    }
                break;
                
                case"polls":
                if(currentRoom.getSymbol() == 'K')
                {
                    System.out.println("These are some polls lying on the floor, they could come in handy later on, add to inventory by using the interact-(feature/item) command");
                }
                else
                {
                    System.out.println("Item is not in this room");
                }
                break;
                
                case"toolbox":
                if(currentRoom.getSymbol() == 'A')
                {
                    System.out.println("This is a toolbox, and will be needed to build a ladder for escaping, add to inventory by using the interact-(feature/item) command");
                    
                }
                else
                {
                    System.out.println("Item is not in this room");
                }
                break;
                
                case"book":
                if(currentRoom.getSymbol() == 'S')
                {
                    System.out.println("This is a book, it contains the information for building the ladder, will be required for escaping, add to inventory by using the interact-(feature/item) command");  
                }
                else
                {
                    System.out.println("Item is not in this room");
                }
                
            default:
                System.out.println("Invalid feature");
                break;
        }
    }
    public static void interact(String interaction) //this is a command which allows the user to interact with items, features or puzzles, for example adding items to the inventory or opening a chest or unlocking a door or to try a puzzle
    {
        System.out.println(interaction);
        switch(interaction.toLowerCase()) //switch case for different possible interactions
            {                 
                case"door":
                    if(inventory.hasItem("key") > -1 && currentRoom.getSymbol() == 'H')
                    {
                        rooms[4].unlockRoom();
                        rooms[5].unlockRoom();
                        rooms[6].unlockRoom();
                        rooms[7].unlockRoom();
                        inventory.removeItem("key");
                        System.out.println("The door has been unlocked, you can now progress to the next level of rooms");
                    }
                    break;

                case"chest":
                    if(currentRoom.getSymbol() == 'C')
                    {
                        System.out.println("You have opened the chest, here is the information you need for the first puzzle in the library:\n C E E A S P");
                    }
                    break;
                    
                    
                case"key":
                    if(inventory.hasItem("key") == -1 && currentRoom.getSymbol() == 'F')
                    {
                        inventory.addItem("key");
                        System.out.println("key has been added to inventory");
                    }
                    break;

                case"polls":
                    if(inventory.hasItem("polls") == -1 && currentRoom.getSymbol() == 'K')
                    {
                        inventory.addItem("polls");
                        System.out.println("polls has been added to inventory");
                    }
                    break;

                case"book":
                    if(inventory.hasItem("book") == -1 && currentRoom.getSymbol() == 'S')
                    {
                        inventory.addItem("book");
                        System.out.println("book has been added to inventory");
                    }
                    break;

                case"toolbox":
                    if(inventory.hasItem("toolbox") == -1 && currentRoom.getSymbol() == 'A')
                    {
                        inventory.addItem("toolbox");
                        System.out.println("toolbox has been added to inventory");
                    }
                    break;

                case"puzzle1":
                    if(currentRoom.getSymbol() == 'L')
                    {
                        puzzle1(); //the first puzzle method
                    }
                    break;

                case"puzzle2":
                    if(currentRoom.getSymbol() == 'G')
                    {
                        if(inventory.hasItem("polls") > -1 && inventory.hasItem("toolbox") > -1 && inventory.hasItem("book") > -1)
                        {
                            puzzle2(); //second puzzle method
                        }
                        else
                        {
                            System.out.println("you are missing items in your inventory, to complete this puzzle");
                        }
                    }                    
                    break;
                    
                default:
                    System.out.println("Invalid interaction");
                    break;
                    
            }
    }

    public static void puzzle1() //first puzzle, runs when is interacted with by user, it is a annagram but the letters for the annagram are in the chest in the cellar, when correct answer is entered unlocks the doors of rooms 8,9,10 in array
    {
        clear();
        System.out.println("Welcome to the first puzzle, which when solved the secret library door will be opened\nYou are required to solve an annagram, however the letters for the annagram are hidden in a chest\nEnter 'return' to leave the puzzle");
        String answer = "";
        while(!(answer.toLowerCase().equals("escape")) && !(answer.toLowerCase().equals("return")))
            {
                System.out.println("Enter resolved annagram:\n");
                answer = inputDevice.nextLine();
                if(answer.toLowerCase().equals("escape"))
                {
                    score.solvePuzzle();
                    rooms[8].unlockRoom();
                    rooms[9].unlockRoom();
                    rooms[10].unlockRoom();
                    System.out.println("Well done puzzle1 has been solved, you now have access to the Attic, Bedroom and Garden");
                }
                else if(answer.toLowerCase().equals("return"))
                {
                    clear();
                    System.out.println("Returned to game");
                }
                else
                {
                    System.out.println("Incorrect\n");
                }
            }
    }

    public static void puzzle2() //second puzzle, is called if user has the 3 required items in there inventory and is in the garden, it's 3 tasks, the first one is a riddle, the second is word association and third is a hidden word in a sentance by using the first letter of each word to make a word, the user has to asnwer all 3 without getting one wrong to complete the puzzle, if incorrect will be asked the first question again, runs until puzzle is solved or uses enters return to go back to the game 
    {
        clear();
        System.out.println("Welcome to the second puzzle which will grant you access to the exit room once completed, and therfore escaped the house\nYou will be given multiple tasks to solve, with each correct answer a ladder will be a step closer to being built with your items\nEnter 'return' to leave the puzzle");
        boolean solved = false;
        String answer = "";
        while(!solved)
            {
                   System.out.println("Answer the riddle:\nWhat has to be broken before it can be used");
                   answer = inputDevice.nextLine();
                   if(answer.toLowerCase().equals("egg"))
                   {
                       System.out.println("What is the common word that relates these 3: Swiss - Cottage - Blue");
                       answer = inputDevice.nextLine();
                       if(answer.toLowerCase().equals("cheese"))
                       {
                           System.out.println("Find the secret word in this sentance, which is key for your escape:\nLions Always Dance During Evening Rains");
                           answer = inputDevice.nextLine();
                           if(answer.toLowerCase().equals("ladder"))
                           {
                               score.solvePuzzle();
                               rooms[11].unlockRoom();
                               System.out.println("Well done you have completed the puzzle and have gained entrance to the Exit Room");
                               solved = true;
                           }
                           else if(answer.toLowerCase().equals("return"))
                           {
                            break;
                           }
                           else
                           {
                           System.out.println("Incorrect");
                           } 
                       }
                       else if(answer.toLowerCase().equals("return"))
                       {
                            break;
                       }
                       else
                       {
                           System.out.println("Incorrect");
                       }
                        
                   }
                   else if(answer.toLowerCase().equals("return"))
                   {
                        break;
                   }
                   else
                   {
                    System.out.println("Incorrect");
                   }
            }
            
    }


    public static Room getRoom(Position pos) //returns the room at a desired position
        {
            for(Room room : rooms)
            {
                if(room.getPosition().x == pos.x && room.getPosition().y == pos.y)
                {
                    return room;
                }
            }
        return null;
        } 
    
    public static void clear() //clears output terminal, for cleaner experience,reference from stack overflow: https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }    
}




