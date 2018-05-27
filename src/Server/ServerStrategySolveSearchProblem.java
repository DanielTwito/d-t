package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {


    public ServerStrategySolveSearchProblem() {
    }

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            //input stream from a client
            ObjectInput fromClient = new ObjectInputStream(inFromClient);
            //output stream to send response back to the client
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Maze maze = null;
            Solution s = null;
            //try to read the maze object from the the client throw the input stream
            try {
                maze= (Maze)fromClient.readObject();
                //tmp directory path
                String tmpDirPath = System.getProperty("java.io.tmpdir");
                //unique file name to present a solution to a specific maze
                String fileName= tmpDirPath +
                        maze.getStartPosition() +
                        maze.getGoalPosition() +
                        maze.getRow() +
                        maze.getCol()+
                        ".maze";
                //open the solution file
                File mazeSolFile = new File(tmpDirPath, fileName);
                boolean SolExist = mazeSolFile.exists();
                // if solution to this maze already exist read him from the file
                if(SolExist){
                    ObjectInputStream sol = new ObjectInputStream(new FileInputStream(fileName));

                    try{
                        s= (Solution)sol.readObject();
                        sol.close();
                    }catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                // else solve him using BFS/DFS algorithm my choice was BFS
                // after solving save him in a new file in the temp directory
                }else {

                    ObjectOutputStream saveSol = new ObjectOutputStream(new FileOutputStream(fileName));
                    ISearchable searchableMaze = new SearchableMaze(maze);
                    ISearchingAlgorithm bfsSearchAlgo = new BestFirstSearch();
                    s = bfsSearchAlgo.solve(searchableMaze);
                    saveSol.writeObject(s);
                    saveSol.close();
                }
                //send solusion back to the client
                toClient.writeObject(s);
                fromClient.close();
                toClient.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
