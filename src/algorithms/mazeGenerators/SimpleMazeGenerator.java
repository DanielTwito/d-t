package algorithms.mazeGenerators;

import java.util.*;

public class SimpleMazeGenerator extends AMazeGenerator {


    public SimpleMazeGenerator() {

    }

    @Override
    public Maze generate(int rows, int columns) {

        maze=new Maze(rows,columns);
        fillWalls();
        Random r=new Random();
        Position startPosition=new Position(r.nextInt(rows),r.nextInt(columns));
        Position endPosition=new Position(r.nextInt(rows),r.nextInt(columns));

        int startX=startPosition.getRowIndex();
        int startY=startPosition.getColumnIndex();
        int endX=endPosition.getRowIndex();
        int endY=endPosition.getColumnIndex();

        maze.setStartP(startPosition);
        maze.setEndP(endPosition);

        while(startX<endX){
            maze.createPath(new Position(++startX,startY));
        }

        while(startX>endX){
            maze.createPath(new Position(--startX,startY));
        }

        while(startY<endY){
            maze.createPath(new Position(startX,++startY));
        }

        while(startY>endY){
            maze.createPath(new Position(startX,--startY));
        }

        for(int i=0;i<maze.getRow();i++)
            for(int j=0;j<maze.getCol();j++) {
                if(maze.getValueAt(new Position(i,j))==1){
                    int tmp=r.nextInt();
                    if(tmp%3==0 )
                        maze.createPath(new Position(i,j));
                }
            }


        return maze;

    }




}
