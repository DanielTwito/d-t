/**
 * interface to maze generation all class who want to
 * genrate maze genrator class need to implement this interface
 */
package algorithms.mazeGenerators;

public interface IMazeGenerator {

    Maze generate(int rows,int columns);
    long measureAlgorithmTimeMillis(int rows,int columns);
}
