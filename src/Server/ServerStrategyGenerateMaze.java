package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {

        int rows=0,cols=0;
        MyCompressorOutputStream compressor = new MyCompressorOutputStream(outToClient);
        AMazeGenerator mazeGenerator = Configurations.getProperty("GenerateMaze_method").equals("1") ? new MyMazeGenerator() : new SimpleMazeGenerator();

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

            int[] mazeSize=(int[]) fromClient.readObject();
            rows= inFromClient.read();
            cols= inFromClient.read();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            new MyCompressorOutputStream(outToClient).write(mazeGenerator.generate(rows,cols).toByteArray());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
