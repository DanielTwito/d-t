/**
 * this class is a maze generator use the prim algorithm
 */

package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    //bollean 2d array the tell us if cell alreday visited
    private boolean visit[][];
    // set that contain all the cells that not part from the maze but thet neighbors of cell who part of the maze
    private HashSet<Position> frontier;

    public MyMazeGenerator() {



    }

    /**
     * row and columns represent the wanted size of the maze
     * @param rows
     * @param columns
     * @return
     */
    @Override
    public Maze generate(int rows, int columns) {
        maze = new Maze(rows,columns);
        visit=new boolean[maze.getRow()][maze.getCol()];
        frontier = new HashSet<>();
        PrimMaze();
        Random r= new Random();

        int i=maze.getRow()-maze.getStartPosition().getRowIndex()-1;
        int j=maze.getCol()-maze.getStartPosition().getColumnIndex()-1;
        if(i==maze.getStartPosition().getRowIndex())
            i=maze.getRow()-1;
        Position p=new Position(i, j);

        for(;i<maze.getRow();i++,j=0)
            for(; j<maze.getCol() ;j++) {
                p = new Position(i, j);
                if (maze.getValueAt(p) == 0) {
                    i = maze.getRow();
                    break;
                }
            }

        maze.setEndP(p);
        return maze;
    }

    /**
     * implemtaion of prim algorithm
     */
    private void PrimMaze() {

        Random r=new Random();
        HashSet<Position> frontierNeighbors;
        fillWalls();
        Position p = new Position(r.nextInt(maze.getRow()),r.nextInt(maze.getCol()));
        maze.setStartP(p);
        visit[p.getRowIndex()][p.getColumnIndex()]=true;
        addNeighbor(frontier, p);

        while(!frontier.isEmpty()){
            p=Randomfrontier();
            frontierNeighbors=new HashSet<>();
            addNeighbor(frontierNeighbors,p);
            if(checkBlackNeibors(frontierNeighbors)) {
                maze.createPath(p);
                checkIfExsist(frontierNeighbors);
                frontier.addAll(frontierNeighbors);
            }

            visit[p.getRowIndex()][p.getColumnIndex()]=true;
        }

    }

    /**
     *
     * @return Position of a random fronteir neighbor
     */
    private Position Randomfrontier() {

        Random r =new Random();
        int loc= r.nextInt(frontier.size());
        Iterator<Position> it = frontier.iterator();
        for(int i=0;i<loc-1;i++)
            it.next();
        Position p=it.next();
        frontier.remove(p);
        return p;
    }

    /**
     * check if one of the frontier neighbors is alradey in fronteir if he does we delete him and add it ass visit cell
     * @param frontierNeibors
     */
    private void checkIfExsist(HashSet<Position> frontierNeibors) {
        HashSet<Position> tmp = new HashSet<>(frontierNeibors);

        for (Position po : tmp)
        {
            if(frontier.contains(po)) {
                frontier.remove(po);
                frontierNeibors.remove(po);
                visit[po.getRowIndex()][po.getColumnIndex()] = true;
            }

        }
    }

    /**
     * check if one o the fronteir neighbors is a wall to make sure we cant reach thir from another place
     * @param frontierNeibors
     * @return boolean true if one of the fronteir neighbors is a wall
     */
    private boolean checkBlackNeibors(HashSet<Position> frontierNeibors) {

        for (Position po : frontierNeibors)
        {
            if(maze.getValueAt(po)==1 && !visit[po.getRowIndex()][po.getColumnIndex()] )
                return true;

        }
        return false;
    }

    /**
     * add valid neighbor to the set
     * @param neighbor
     * @param p
     */
    private void addNeighbor(HashSet<Position> neighbor,Position p) {
        int x=p.getRowIndex();
        int y=p.getColumnIndex();

        if(maze.inBound(x+1,y))
            if( maze.getValueAt(new Position(x+1,y)) ==1 && !visit[x+1][y])
                neighbor.add(new Position(x+1,y));

        if(maze.inBound(x-1,y))
            if( maze.getValueAt(new Position(x-1,y)) ==1 && !visit[x-1][y])
                neighbor.add(new Position(x-1,y));

        if(maze.inBound(x,y+1))
            if( maze.getValueAt(new Position(x,y+1)) ==1 && !visit[x][y+1])
                neighbor.add(new Position(x,y+1));

        if(maze.inBound(x,y-1))
            if( maze.getValueAt(new Position(x,y-1)) ==1 && !visit[x][y-1])
                neighbor.add(new Position(x,y-1));



    }


}
