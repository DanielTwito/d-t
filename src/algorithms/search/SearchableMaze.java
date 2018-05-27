package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    Maze myMaze;
    public SearchableMaze(Maze maze) {
        this.myMaze=maze;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {

        ArrayList<AState> nextStates=new ArrayList<>();
        MazeState mazeState=(MazeState)state;
        int x=mazeState.locationOnMaze.getRowIndex();
        int y=mazeState.locationOnMaze.getColumnIndex();
        //south
        if(myMaze.inBound(x+1,y)){
            if(myMaze.getValueAt(new Position(x+1,y))==0) {
                if(myMaze.inBound(x+1,y+1) && myMaze.getValueAt(new Position(x+1,y+1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y+1),mazeState.cost+15));
                if(myMaze.inBound(x+1,y-1) && myMaze.getValueAt(new Position(x+1,y-1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y-1),mazeState.cost+15));
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y),mazeState.cost+10));

            }
        }
        //east
        if(myMaze.inBound(x,y+1)){
            if(myMaze.getValueAt(new Position(x,y+1))==0) {
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x, y + 1),mazeState.cost+10));
                if(myMaze.inBound(x+1,y+1) && myMaze.getValueAt(new Position(x+1,y+1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y+1),mazeState.cost+15));
                if(myMaze.inBound(x-1,y+1) && myMaze.getValueAt(new Position(x-1,y+1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y+1),mazeState.cost+15));
            }
        }
        //north
        if(myMaze.inBound(x-1,y)){
            if(myMaze.getValueAt(new Position(x-1,y))==0) {
                if(myMaze.inBound(x-1,y+1) && myMaze.getValueAt(new Position(x-1,y+1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y+1),mazeState.cost+15));
                if(myMaze.inBound(x-1,y-1) && myMaze.getValueAt(new Position(x-1,y-1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y-1),mazeState.cost+15));
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y),mazeState.cost+10));

            }
        }
        //west
        if(myMaze.inBound(x,y-1)){
            if(myMaze.getValueAt(new Position(x,y-1))==0) {
                nextStates.add(new MazeState(String.format("Row: %d Column: %d", x, y - 1),mazeState.cost+10));
                if(myMaze.inBound(x-1,y-1) && myMaze.getValueAt(new Position(x-1,y-1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x - 1, y-1),mazeState.cost+15));
                if(myMaze.inBound(x+1,y-1) && myMaze.getValueAt(new Position(x+1,y-1))==0)
                    nextStates.add(new MazeState(String.format("Row: %d Column: %d", x + 1, y-1),mazeState.cost+15));
            }
        }

        return nextStates;

    }

    @Override
    public AState getStartState() {
        int x=myMaze.getStartPosition().getRowIndex();
        int y=myMaze.getStartPosition().getColumnIndex();
        return new MazeState(String.format("Row: %d Column: %d", x, y),0);
    }

    @Override
    public AState getGoalState() {

        int x=myMaze.getGoalPosition().getRowIndex();
        int y=myMaze.getGoalPosition().getColumnIndex();
        return new MazeState(String.format("Row: %d Column: %d", x, y));
    }
}
