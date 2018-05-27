package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import algorithms.mazeGenerators.Position;


import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    private BestFirstSearch best= new BestFirstSearch();

    @Test
    void getNumberOfNodesEvaluated() {
    }

    @Test
    void getName() {
        assertEquals("Best First Search",best.getName());
    }


    @Test
    void solve() {

        assertTrue(best.solve(new SearchableMaze(new MyMazeGenerator().generate(6,6)))
                instanceof Solution );
        assertTrue(best.solve(null)instanceof Solution );

        Maze m =new Maze(3,3);
        for(int i=0;i<m.getRow();i++)
            for(int j=0;j<m.getCol();j++)
                m.createWall(new Position(i,j));

        assertTrue(best.solve(new SearchableMaze(m)) instanceof Solution);

    }
}