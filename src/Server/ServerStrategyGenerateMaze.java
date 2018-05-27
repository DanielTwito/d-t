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
       // BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
        try {
            rows= inFromClient.read();
            cols= inFromClient.read();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            outToClient.write(mazeGenerator.generate(rows,cols).toByteArray());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
