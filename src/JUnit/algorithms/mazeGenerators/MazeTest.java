package algorithms.mazeGenerators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void toByteArray() {
        Maze maze;
        AMazeGenerator mm = new MyMazeGenerator();
        maze= mm.generate(300,300);
        byte[] check = maze.toByteArray();
        int startx=0;
        int starty=0;
        int endx=0;
        int endy=0;
        int row=0,col=0;
        int index = 0;

        while(check[index]!=-2) {
            if(check[index] == -3)
                break;
            startx += check[index];
            index++;
        }
        while(check[index]!=-2) {
            if(check[index] == -3)
            break;
            starty += check[index];
            index++;
        }
        while(check[index]!=-2) {
            if(check[index] == -3)
                break;
            endx += check[index];
            index++;

        }
        while(check[index]!=-2) {
            if(check[index] == -3)
                break;
            endy += check[index];
            index++;

        }
        while(check[index]!=-2) {
            if(check[index] == -3)
                break;
            row += check[index];
            index++;

        }
        while(check[index]!=-2) {
            if(check[index] == -3)
                break;
            col += check[index];
            index++;

        }

        boolean maze_metadata_verification=         (startx!=maze.getStartPosition().getRowIndex()||
                                                    starty!=maze.getStartPosition().getColumnIndex()||
                                                    endx!=maze.getGoalPosition().getRowIndex()||
                                                    endy!=maze.getGoalPosition().getColumnIndex()||
                                                    row!=maze.getRow()||
                                                    col!=maze.getCol()  );
        boolean flag =true;
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j <col ; j++) {
                flag = maze.getValueAt(new Position(row,col))==check[index] && flag;
                index++;

            }
            Assertions.assertTrue(index == check.length && flag && maze_metadata_verification);
        }
    }

    @Test
    void toByteArrayConstactor(){

        byte[] byteMaze= {0,-2,0,-2,2,-2,2,-2,3,-2,3,-2,-3,0,0,1,
                                                1,0,1,
                                                1,0,0};
        Maze m= new Maze(byteMaze);
        boolean flag=true;
        flag &= m.getStartPosition().equals(new Position(0,0));
        flag &= m.getGoalPosition().equals(new Position(2,2));
        flag &= m.getRow()==3;
        flag &= m.getCol()==3;




    }
}