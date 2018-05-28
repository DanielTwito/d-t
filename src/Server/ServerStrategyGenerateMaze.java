package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {

        int rows=0,cols=0;
        MyCompressorOutputStream compressor = new MyCompressorOutputStream(outToClient);
        MyMazeGenerator mazeGenerator = new MyMazeGenerator();
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
