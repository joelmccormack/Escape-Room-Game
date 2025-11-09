package org.uob.a1;

public class Score
{
    private int startingScore;
    private int currentScore;
    private int roomsVisited;
    private int puzzlesSolved;
    private final int PUZZLE_VALUE = 10;

    public Score(int startingScore)
    {
        this.startingScore = startingScore;
    }
    
    public void visitRoom() //increments the number of rooms visited variable
    {
        roomsVisited++;
    }
    public void solvePuzzle() //increments the number of puzzles solved variable
    {
        puzzlesSolved++;
    }
    public double getScore() //calculates and returns a score dependin on the rooms visited and puzzles solved
    {
        currentScore = (startingScore - roomsVisited)+(puzzlesSolved*PUZZLE_VALUE);
        return currentScore;
    }
}