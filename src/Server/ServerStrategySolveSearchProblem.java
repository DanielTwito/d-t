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
    public ServerStrategySolveSearchProblem() {
//        try {
//            File f = new File(System.getProperty("java.io.tmpdir")+"solution.txt");
//            f.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



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

//            String tmpDirPath = System.getProperty("java.io.tmpdir");
//            String path = "solution.txt";
//            //to write to the file
//            ObjectOutputStream toSolFile = null ;
//            //to read from a file
//            ObjectInputStream fromSolFile = null ;
//            // read from a client
//            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
//            //send to a client
//            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
//            toClient.flush();
//
//            Solution sol;
//            Maze maze = (Maze) fromClient.readObject();
//            HashMap<Maze, Solution> solArchive;
//            //first solution we add to the archive and write the HashMap to the file
//            if (firstSol) {
//
////                toSolFile = new ObjectOutputStream(new FileOutputStream(tmpDirPath+path));
//                toSolFile= new ObjectOutputStream(new PrintStream(tmpDirPath+path));
//                solArchive = new HashMap<>();
//                sol = solve(maze);
//                solArchive.put(maze, sol);
//                toSolFile.writeObject(solArchive);
//                firstSol=false;
//                toSolFile.close();
////                toSolFile.close();
//                // else the solution archive exist and contain former solution
//            } else {
//                fromSolFile =  new ObjectInputStream(new FileInputStream(tmpDirPath+path));
//                solArchive = (HashMap<Maze, Solution>) fromSolFile.readObject();
//                toSolFile =new ObjectOutputStream(new PrintStream(tmpDirPath+path));
//                if (solArchive.containsKey(maze))
//                    sol = solArchive.get(maze);
//                else {
//                    sol = solve(maze);
//                    solArchive.put(maze, sol);
//                    toSolFile.writeObject(solArchive);
//
//                }
//
//            }
//            toClient.writeObject(sol);
//            toClient.close();
//            fromSolFile.close();
//            toSolFile.close();
//        } catch (IOException  | ClassNotFoundException e) {
//            e.printStackTrace();
//        }



    private Solution solve(Maze maze) {
        ISearchable searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm bfsSearchAlgo = new BestFirstSearch();
        return bfsSearchAlgo.solve(searchableMaze);
    }

//        //File solFile =  new File(tmpDirPath+"solutions.bin");
//        File solFile =  new File("C:\\Users\\Daniel\\Desktop\\solutions.maze");
//        boolean firstSol = false;
//        //if solutions file does not exist create one
//        if(!solFile.exists()) {
//            try {
//                solFile.createNewFile();
//                firstSol = true;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//            // define stream to read/write the HashMAp from the file
//        try {
//            ObjectInputStream fileReader=null ;
//            ObjectOutputStream fileWriter=null;
//            if(!firstSol)
//                fileReader = new ObjectInputStream(new FileInputStream(solFile));
//            new ObjectOutputStream(new FileOutputStream(solFile));
//
//            //input stream from a client
//            ObjectInput fromClient = new ObjectInputStream(inFromClient);
//            //output stream to send response back to the client
//            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
//            toClient.flush();
//            Maze maze = null;
//            Solution s = null;
//            //try to read the maze object from the the client throw the input stream
//            maze = (Maze) fromClient.readObject();
//
//            if ( firstSol ){
//
//                s = solve(maze);
//                solutionHashMap.put(maze,s);
//                fileWriter.writeObject(solutionHashMap);
//
//            }else {
//                solutionHashMap = (HashMap<Maze, Solution>) fileReader.readObject();
//                if (solutionHashMap.containsKey(maze))
//                    s = solutionHashMap.get(maze);
//                else {
//                    s = solve(maze);
//                    solFile.delete();
//                    solFile.createNewFile();
//                    fileWriter.writeObject(solutionHashMap);
//                }
//
//            }
//            toClient.writeObject(s);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    private Solution solve(Maze maze) {
//        ISearchable searchableMaze = new SearchableMaze(maze);
//        ISearchingAlgorithm bfsSearchAlgo = new BestFirstSearch();
//        return bfsSearchAlgo.solve(searchableMaze);
//
//    }

//        try {
//            //input stream from a client
//            ObjectInput fromClient = new ObjectInputStream(inFromClient);
//            //output stream to send response back to the client
//            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
//            toClient.flush();
//            Maze maze = null;
//            Solution s = null;
//            //try to read the maze object from the the client throw the input stream
//            try {
//                maze= (Maze)fromClient.readObject();
//                //tmp directory path
//                String tmpDirPath = System.getProperty("java.io.tmpdir");
//
//                if(solutionHashMap.containsKey(maze))
//                    s = solutionHashMap.get(maze);
//
//                File mazeSolFile = new File(tmpDirPath, fileName);
//                boolean SolExist = mazeSolFile.exists();
//                // if solution to this maze already exist read him from the file
//                if(SolExist){
//                    ObjectInputStream sol = new ObjectInputStream(new FileInputStream(fileName));
//
//                    try{
//                        s= (Solution)sol.readObject();
//                        sol.close();
//                    }catch (ClassNotFoundException e){
//                        e.printStackTrace();
//                    }
//                // else solve him using BFS/DFS algorithm my choice was BFS
//                // after solving save him in a new file in the temp directory
//                }else {
//
//                    ObjectOutputStream saveSol = new ObjectOutputStream(new FileOutputStream(fileName));
//                    ISearchable searchableMaze = new SearchableMaze(maze);
//                    ISearchingAlgorithm bfsSearchAlgo = new BestFirstSearch();
//                    s = bfsSearchAlgo.solve(searchableMaze);
//                    saveSol.writeObject(s);
//                    saveSol.close();
//                }
//                //send solusion back to the client
//                toClient.writeObject(s);
//                fromClient.close();
//                toClient.close();
//
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }

}
