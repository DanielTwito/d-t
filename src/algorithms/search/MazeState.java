package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MazeState extends AState {

    Position locationOnMaze;

    //string state form: "Row: x-position Column: y-position"
    public MazeState(String state,int cost) {

        super(state,cost);

        String [] s=state.split(" ");
        locationOnMaze=new Position(Integer.parseInt(s[1]),Integer.parseInt(s[3]));
    }

    public MazeState(String state) {

        super(state);
        String [] s=state.split(" ");
        locationOnMaze=new Position(Integer.parseInt(s[1]),Integer.parseInt(s[3]));
    }
/*
    @Override
    public ArrayList<AState> getAllPossibleNextStates() {

        ArrayList<AState> nextStates=new ArrayList<>();
        int x=locationOnMaze.getRowIndex();
        int y=locationOnMaze.getColumnIndex();
        //south
        if(myMaze.inBound(x+1,y)){
            if(myMaze.getValueAt(new Position(x+1,y))==0) {
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y), myMaze,cost+1));
                if(myMaze.inBound(x+1,y+1) && myMaze.getValueAt(new Position(x+1,y+1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y+1), myMaze,cost+1));
                if(myMaze.inBound(x+1,y-1) && myMaze.getValueAt(new Position(x+1,y-1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y-1), myMaze,cost+1));
            }
        }
        //east
        if(myMaze.inBound(x,y+1)){
            if(myMaze.getValueAt(new Position(x,y+1))==0) {
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x, y + 1), myMaze,cost+1));
            }
        }
        //north
        if(myMaze.inBound(x-1,y)){
            if(myMaze.getValueAt(new Position(x-1,y))==0) {
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y), myMaze,cost+1));
                if(myMaze.inBound(x-1,y+1) && myMaze.getValueAt(new Position(x-1,y+1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y+1), myMaze,cost+1));
                if(myMaze.inBound(x-1,y-1) && myMaze.getValueAt(new Position(x-1,y-1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y-1), myMaze,cost+1));
            }
        }
        //west
        if(myMaze.inBound(x,y-1)){
            if(myMaze.getValueAt(new Position(x,y-1))==0) {
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x, y - 1), myMaze,cost+1));
            }
        }

        return nextStates;

    }
    */

    @Override
    public int getCost() {
        return cost;
    }


}
