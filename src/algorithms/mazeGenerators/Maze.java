/**
 * this class represent a maze
 */
package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class Maze {

    private int[][] maze;
    private Position startP;
    private Position endP;
    private int row;
    private int col;


    /**
     * constructor for the maze
     * set a random starting point and random ending point
     * @param rows - number of rows in the maze
     * @param columns - number of  coulmns in maze
     */
    public Maze(int rows,int columns) {
        if(rows<=0||columns<=0)
            throw new IllegalArgumentException("the row and colums of the maze must be a positive integer number");
        this.row=rows;
        this.col=columns;
        //defualt start and end position
        startP=new Position(0,0);
        endP = new Position(row-1,col-1);
        maze = new int[row][col];
    }

    public Maze(byte[] byteArr){

        int startx=0;
        int starty=0;
        int endx=0;
        int endy=0;
        int row=0,col=0;
        int index = 0;

        //<editor-fold desc="restore meta data from the byte Array">
        while(byteArr[index]!=-2) {
            if(byteArr[index] == -3)
                break;
            startx += byteArr[index];
            index++;
        }
        index++;
        while(byteArr[index]!=-2) {
            if(byteArr[index] == -3)
                break;
            starty += byteArr[index];
            index++;
        }
        index++;
        while(byteArr[index]!=-2) {
            if(byteArr[index] == -3)
                break;
            endx += byteArr[index];
            index++;

        }
        index++;
        while(byteArr[index]!=-2) {
            if(byteArr[index] == -3)
                break;
            endy += byteArr[index];
            index++;

        }
        index++;
        while(byteArr[index]!=-2) {
            if(byteArr[index] == -3)
                break;
            row += byteArr[index];
            index++;

        }
        index++;
        while(byteArr[index]!=-2) {
            if(byteArr[index] == -3)
                break;
            col += byteArr[index];
            index++;

        }
        index+=2;
        //</editor-fold>

        startP= new Position(startx,starty);
        endP = new Position(endx,endy);
        this.row = row;
        this.col = col;
        maze = new int[this.row][this.col];
        //restore maze data from the byte Array
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < col ; j++) {
                if( byteArr[index] == 1)
                    maze[i][j] = 1;
                else
                    maze[i][j] =0;
                index++;

            }
        }




    }

    public byte[] toByteArray(){
        /**
         *  add start and end point and the size at the end of the array when -2 separating the values
         *  for example: for a maze with start point of (25,128) and end point of (30,3) and size
         *  row:100 column: 100
         *  we add to the end of the byte array, after the data of walls and paths in the maze
         *  the following value:
         *  25, -2, 127, 1, -2, 30, 3, -2,100,-2,100,-2
         */
        ArrayList<Byte> x = new ArrayList<>();

        //<editor-fold desc="add start and end point and the size to byte array">

        intToByteArray(x, startP.getRowIndex());//start.row
        intToByteArray(x, startP.getColumnIndex());//start.col
        intToByteArray(x, endP.getRowIndex());//goal.row
        intToByteArray(x, endP.getColumnIndex());//goal.col

        //maze size
        intToByteArray(x,row);
        intToByteArray(x,col);



        //</editor-fold>
        x.add((byte) -3);
        //convetring maze data
        for(int i=0;i<row;i++){
            for (int j = 0; j < col ; j++) {
                x.add((byte) maze[i][j]);
            }
        }


        byte[] ans = new byte[x.size()];
        for (int i = 0; i < x.size() ; i++) {
            ans[i] = x.get(i);
        }
        return ans ;
    }

    private void intToByteArray(ArrayList<Byte> x, int value) {
        while(value >127) {
            x.add((byte) 127);
            value -= 127;
        }
        x.add((byte) value);
        x.add((byte) -2);
    }

    /**
     *
     * @return the size of the rows in the maze
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return the size of columns in the maze
     */
    public int getCol() {
        return col;
    }
    /**
     *
     * @return The start position of the maze Position Type
     */
    public Position getStartPosition(){return startP;}

    /**
     *@return The start position of the maze Position Type
     */
    public Position getGoalPosition(){return endP;}

    /**
     * set a new start position
     * @param startP given position
     */
    public void setStartP(Position startP) {
        this.startP = startP;
        createPath(startP);
    }

    /**
     * set a new Goal/End Position
     * @param endP given Position
     */
    public void setEndP(Position endP) {
        this.endP = endP;
        createPath(endP);
    }

    /**
     * printing the maze to the console
     */
    public void print(){
        int i,j;
        for(i=0;i<row;i++){
            System.out.print("[ ");
            for(j=0;j<col-1;j++){
                System.out.print(helpPrint(i,j)+", ");
            }
            System.out.print(helpPrint(i,j)+" ]");
            System.out.println();

        }


    }
    /**
     * create a path in a given cell
     * @aram p is the position to create path
     */
    public void createWall(Position p){
        //if(!p.equals(startP) && !p.equals(endP))
            maze[p.getRowIndex()][p.getColumnIndex()]=1;
    }
    /**
     * create a path in a given cell
     * @aram p is the position to create path
     */
    public void createPath(Position p){
        maze[p.getRowIndex()][p.getColumnIndex()]=0;
    }

    public int getValueAt(Position p){
        return maze[p.getRowIndex()][p.getColumnIndex()];
    }

    /**
     * chech if the given position is in bound of the maze bounds
     * @param row need to be in range [0,row]
     * @param col need to be in range [0,col]
     * @return
     */
    public boolean inBound(int row, int col) {
        return (row<this.row&& row>=0)
                && (col>=0 &&col<this.col);
    }
    /**
     * chek if the given position is the starting position of the maze
     * @param row
     * @param col
     * @return true if Start
     *         false otherwise
     */
    private boolean checkStart(int row,int col) {

        Position p = new Position(row,col);
        return  p.equals(startP);

    }

    /**
     * chek if the given position is the ending position of the maze
     * @param row
     * @param col
     * @return true if Start
     *         false otherwise
     */
    private boolean checkEnd(int row,int col) {

        Position p = new Position(row,col);
        return  p.equals(endP);

    }

    /**
     * help function to the print functiom
     * @param row
     * @param col
     * @return "S" - if the position {row,col} is Start position
     *         "E" - if the position {row,col} is End position
     *         the element maze[row][col] otherwise
     */
    private String helpPrint(int row,int col){
        String ans="";
        if(checkStart(row,col))
            ans="S";
        else if(checkEnd(row,col))
            ans="E";
        else
            ans=maze[row][col]+"";
        return ans;
    }



}
