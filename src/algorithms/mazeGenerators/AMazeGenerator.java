/**
 * this class represent a abstaract class to maze genarators
 * all concrete maze genarators need to inhernt from this class
 */

package algorithms.mazeGenerators;



public abstract class AMazeGenerator implements IMazeGenerator{

    protected Maze maze;

    public abstract Maze generate(int rows, int columns);

    /**
     * print maze to the console
     */
    public void print(){
        maze.print();
    }

    /**
     * return the time took that function genarate to genarate a maze
     * the time shown in ms (milli second)
     * rows,coloums is the size of the maze you wante to genarate
     * @param rows num of rows
     * @param columns num of col
     * @return lonf
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start =System.currentTimeMillis();
        generate(rows, columns);
        long end = System.currentTimeMillis();
        return end-start;
    }


    /**
     * fill all the maze in walls (part of the prim algorithm)
     */
    protected void fillWalls() {
        for(int i=0;i<maze.getRow();i++){
            for (int j=0;j<maze.getCol();j++){
                maze.createWall(new Position(i,j));
            }
        }
    }
}
