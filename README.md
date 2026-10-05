# Escape Room Game

## Overview

Escape Room Game is a text-based adventure written in Java where players navigate a 12-room house, collect items, solve environmental puzzles, and work towards their escape. Starting from the Entrance Room, players must explore methodically, gathering clues and useful items hidden throughout the house. To progress, players will unlock new areas by finding the right items and solving increasingly complex puzzles. This project demonstrates object-oriented Java design, 2D array-based state management, and command parsing in an interactive game loop.

## Features

- **Room Exploration** — Navigate a 3×4 grid of 12 interconnected rooms, each with unique descriptions and interactive elements
- **Text-Based Map** — A dynamic 2D map that reveals rooms as you explore them
- **Inventory Management** — Collect and manage up to 10 items that are critical to progression
- **Locked-Room Progression** — Rooms begin locked and are only accessible after solving puzzles or using key items
- **Interactive Features** — Interact with room features (doors, chests) and pick up items through a unified command system
- **Puzzle Solving** — Two major puzzle stages with distinct mechanics that gate your progress through the house
- **Scoring System** — Earn points for exploring new rooms and solving puzzles; final score is displayed at the end
- **Command-Based Interface** — Control the game entirely through text commands with clear syntax
- **Context-Sensitive Help** — Help messages adapt based on your progress through the game

## Gameplay

You begin in the Entrance Room with access to only the top row of the house. Your objective is to explore rooms, search for useful items and information, and ultimately reach the Exit Room. Throughout your journey, you'll encounter two major puzzles that block access to different parts of the house:

**Early Progression:** Your first challenge involves finding clues elsewhere in the house before you can solve a puzzle that unlocks the middle and lower levels of rooms.

**Late Progression:** Before attempting the final puzzle in the Garden, you must have collected three essential items scattered throughout the house. This puzzle requires solving multiple tasks in sequence without mistakes.

As you explore, rooms you've visited will be marked on your map, and your score will increase. Backtracking and careful inventory management are key to success.

## Commands

The following commands are supported. Note that multi-word commands use a hyphen with no spaces (e.g., `move-north`, `look.f-door`):

| Command | Description | Example |
|---------|-------------|---------|
| `move-<direction>` | Move to an adjacent room in the specified direction (north, south, east, west). Movement fails if the destination room is locked or does not exist. | `move-east` |
| `look` | Display the full description of your current room. | `look` |
| `look.f-<feature>` | Look at a specific feature in the current room (e.g., a door or chest). | `look.f-door` |
| `look.i-<item>` | Inspect a specific item in the current room to learn more about it. | `look.i-key` |
| `inventory` | Display all items you are currently carrying. | `inventory` |
| `score` | Display your current score based on rooms visited and puzzles solved. | `score` |
| `map` | Display the current state of the map (rooms visited and unexplored areas). | `map` |
| `interact-<target>` | Interact with an item, feature, or puzzle. Examples: pick up items, open a chest, unlock a door, or attempt a puzzle. | `interact-key` or `interact-puzzle1` |
| `help` | Display context-sensitive help that adapts to your current progress in the game. | `help` |
| `clear` | Clear the terminal screen for a cleaner display. | `clear` |
| `quit` | Return to the main menu without saving progress. | `quit` |
| `exit` | Win the game (only works in the Exit Room). | `exit` |

## Technical Implementation

**Object-Oriented Design:** The game is built using six main classes that separate concerns:

- **Game** — Manages the main game loop, command parsing, and player interactions. Handles the menu system, game state, and coordinates all other classes.
- **Room** — Represents individual rooms with properties like name, description, position on the map, and locked/unlocked state. Each room knows its own state and how to lock/unlock itself.
- **Map** — Uses a 2D character array to store the game map. Unexplored rooms are represented with `'.'` and are replaced with room symbols as the player discovers them. Handles map display.
- **Position** — A simple coordinate system (x, y) representing a room's location on the 3×4 grid. Used throughout the game to identify and navigate between rooms.
- **Inventory** — An array-backed storage system for up to 10 items. Provides methods to add, remove, and search for items. Handles item shuffling when items are removed to prevent gaps.
- **Score** — Tracks the player's score using a formula based on the number of rooms visited (penalties) and puzzles solved (bonuses). Calculates and returns the current score on demand.

**Key Implementation Details:**
- The game world is a 3×4 grid of rooms represented using 2D array coordinates
- Rooms begin either locked or unlocked based on their row position (only the top row is accessible initially)
- The command parser splits user input on hyphens to separate command names from arguments
- The game loop continuously accepts user input, parses commands, and updates state until the player exits from the Exit Room
- Puzzle logic is contained within the game loop and validates both the presence of required items and correct puzzle answers
- The map is dynamically updated as the player explores, revealing room symbols on demand

## Testing

The project includes **JUnit 5 unit tests** for core game components:

- **InventoryTest** — Tests the constructor, adding items, checking for items, removing items, and displaying inventory contents
- **MapTest** — Tests map initialization and room placement on the 2D grid
- **PositionTest** — Tests coordinate creation and x/y accessors
- **RoomTest** — Tests room creation and lock/unlock functionality
- **ScoreTest** — Tests score initialization and calculation based on visited rooms and solved puzzles

These tests validate that each component behaves correctly in isolation, ensuring the building blocks of the game are reliable.

## Running the Project

### Prerequisites
- Java Development Kit (JDK) 8 or later
- Bash shell (for running `.sh` scripts)

### Compiling and Running the Game

On Linux, macOS, or Windows (with Git Bash or WSL):

```bash
./run.sh
```

This script compiles all source files in `src/org/uob/a1/` to the `out/` directory and then runs the main `Game` class. You will be presented with a menu to start or read the game rules.

Alternatively, compile and run manually:

```bash
javac -d out -Xlint:none src/org/uob/a1/*.java
java -cp out org.uob.a1.Game
```

### Running the Tests

To run all JUnit tests:

```bash
./test.sh
```

This script compiles both source and test files with the necessary JUnit libraries and executes the tests. Test output will be displayed in the console.

Alternatively, run manually:

```bash
javac -d out -Xlint:none -cp .:lib/junit-jupiter-api-5.11.1.jar:lib/hamcrest-core-3.0.jar src/org/uob/a1/*.java test/org/uob/a1/*.java
java -jar lib/junit-platform-console-standalone-1.9.0.jar --details=none --class-path ./out/ --scan-classpath
```

## Project Structure

```
escapeRoomGame/
├── src/org/uob/a1/
│   ├── Game.java           # Main game loop, command parsing, and game logic
│   ├── Room.java           # Room representation and state
│   ├── Map.java            # 2D grid-based map display
│   ├── Position.java       # Coordinate system for room locations
│   ├── Inventory.java      # Item storage and management
│   └── Score.java          # Score calculation
├── test/org/uob/a1/
│   ├── InventoryTest.java
│   ├── MapTest.java
│   ├── PositionTest.java
│   ├── RoomTest.java
│   └── ScoreTest.java
├── lib/                    # JUnit and testing libraries
├── out/                    # Compiled .class files (generated by build)
├── run.sh                  # Script to compile and run the game
├── test.sh                 # Script to compile and run tests
└── README.md              # This file
```

## Skills Demonstrated

This project showcases:
- **Java** — Object-oriented programming, arrays, and control flow
- **Object-Oriented Design** — Separation of concerns across multiple classes with clear responsibilities
- **2D Arrays** — Used to implement the game map and track explored/unexplored rooms
- **State Management** — Tracking game state including player position, inventory, room access, and score
- **Command Parsing** — Splitting and interpreting user input to drive game behavior
- **Problem Solving** — Implementing game logic, puzzle mechanics, and progression systems
- **Unit Testing with JUnit** — Writing and running automated tests for core components
- **Game Loop Architecture** — Structuring an interactive application with a continuous input/output loop

## License

[Add your preferred license here]
