package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private  boolean firstSol = true;
    private String tmp = System.getProperty("java.io.tmpdir");
    public ServerStrategySolveSearchProblem() {}



    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {

            // read from a client
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            //send to a client
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Solution sol;
            Maze maze = (Maze) fromClient.readObject();
            int mazeId = maze.hashCode();
            File solFile = new File(tmp + mazeId + ".sol");
            sol = getSOl(solFile, maze);
            toClient.writeObject(sol);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Solution getSOl(File solFile, Maze maze) {
        Solution s = null;
        try {
            if (solFile.exists()) {
                ObjectInputStream readSol = new ObjectInputStream(new FileInputStream(solFile));
                s = (Solution) readSol.readObject();
                readSol.close();

            } else {
                ObjectOutputStream tosolFile = new ObjectOutputStream(new FileOutputStream(solFile));
                s = solve(maze);
                tosolFile.writeObject(s);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return s;

    }

    /**
     * solve the maze using algorithm that define in configuration file
     * @param maze given maze
     * @return
     */
    private Solution solve(Maze maze) {
        ISearchable searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm searchAlgo=new BreadthFirstSearch();
        String algo = Configurations.getProperty("searching_algo");
        if(algo.equals("BFS"))
            searchAlgo = new BreadthFirstSearch();
        else if (algo.equals("BST"))
            searchAlgo = new BestFirstSearch();
        else if (algo.equals("DFS"))
            searchAlgo = new DepthFirstSearch();

        return searchAlgo.solve(searchableMaze);
    }



}
