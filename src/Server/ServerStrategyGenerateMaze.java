package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {

        int rows=0,cols=0;
        MyCompressorOutputStream compressor;
        MyMazeGenerator mazeGenerator = new MyMazeGenerator();
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            OutputStream ba=new ByteArrayOutputStream();
            compressor=new MyCompressorOutputStream(ba);

            int[] mazeSize=(int[]) fromClient.readObject();
            rows=mazeSize[0];
            cols= mazeSize[1];
            compressor.write(mazeGenerator.generate(rows,cols).toByteArray());
            ByteArrayOutputStream b = (ByteArrayOutputStream) ba;
            toClient.writeObject(b.toByteArray());

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }


    }
}
